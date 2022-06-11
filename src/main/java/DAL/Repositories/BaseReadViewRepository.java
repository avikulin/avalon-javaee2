package DAL.Repositories;

import Common.Annotations.UserCaption;
import Common.Contracts.FieldDef;
import Common.Classes.FieldDefImpl;
import Common.Classes.IpAddress;
import DAL.Annotations.*;
import DAL.Contracts.Repository.ReadViewRepository;
import DAL.Utils.Filter.Contracts.FilterExpression;
import DAL.Utils.Filter.Contracts.FilterTokenDef;
import DAL.Utils.Filter.Enums.CriteriaType;
import DAL.Utils.Filter.Enums.PredicateType;
import DAL.Utils.Filter.FilterTokenDefImpl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class BaseReadViewRepository<T> implements ReadViewRepository<T> {
    private static final String ROOT_ENTITY_ALIAS = "e1";

    private final EntityManager entityManager;
    private final Class<T> clazz;
    private Constructor<T> ctor;
    private String  rootEntityName;
    private final Map<Integer, FieldDef> fieldMap;
    private List<String> projectionExpressionTokens;
    private final Map<String, FieldDef> filterableFields;
    private final Deque<Object> filterParams;
    private final Deque<String> filterTokens;
    private String query;
    private boolean useDistinct;

    public BaseReadViewRepository(EntityManager entityManager, Class<T> clazz) {
        Objects.requireNonNull(entityManager, "EntityManager object reference must be not null");
        Objects.requireNonNull(clazz, "Base class reference must be not null");
        this.entityManager = entityManager;
        this.clazz = clazz;
        this.projectionExpressionTokens = new ArrayList<>();
        this.filterableFields = new HashMap<>();
        this.fieldMap = new TreeMap<>();
        this.filterParams = new ArrayDeque<>();
        this.filterTokens = new ArrayDeque<>();
        this.useDistinct = false;

        collectMetaData();
    }

    private void collectMetaData(){
        SourceEntity[] entityAttr = clazz.getDeclaredAnnotationsByType(SourceEntity.class);
        if (entityAttr.length == 0){
            throw new IllegalStateException("Illegal class annotations detected");
        }

        this.rootEntityName = entityAttr[0].entityName();

        Constructor[] ctors = clazz.getDeclaredConstructors();
        if (ctors.length == 0){
            throw new IllegalStateException("Illegal default constructor structure detected");
        }

        this.ctor = ctors[0];

        Field[] fields = clazz.getDeclaredFields();
        for(Field f: fields){
            UserCaption[] captionAttr = f.getAnnotationsByType(UserCaption.class);
            Filterable[] filterableAttr = f.getAnnotationsByType(Filterable.class);
            SourceField[] sourceAttr = f.getAnnotationsByType(SourceField.class);
            CtorParam[] ctorParamsAttr = f.getAnnotationsByType(CtorParam.class);

            if (captionAttr.length == 0 || sourceAttr.length == 0){
                throw new IllegalStateException("Illegal field annotations detected");
            }

            String caption = captionAttr[0].value();
            SourceField source = sourceAttr[0];
            String entity = source.fieldSource();
            String field = source.fieldName();
            String path = source.fieldProjectionPath().isEmpty() ? source.fieldName() : source.fieldProjectionPath();

            FieldDef fd = new FieldDefImpl(entity, ROOT_ENTITY_ALIAS, field, path, caption, f.getType());
            if (ctorParamsAttr.length > 0) {
                int pos = ctorParamsAttr[0].position();
                this.fieldMap.put(pos, fd);
            }

            if (filterableAttr.length > 0){
                filterableFields.put(f.getName(), fd);
            }
        }
    }

    private void prepareJpql(){

        this.projectionExpressionTokens = this.fieldMap.entrySet()
                                                    .stream()
                                                        .map(e -> ROOT_ENTITY_ALIAS
                                                                    +"."+e.getValue().getProjectionPath())
                                                                    .collect(Collectors.toList());
        String sqlOperator = (this.useDistinct) ? "select distinct new " : "select new ";
        this.query = sqlOperator
                .concat(this.ctor.getName())
                .concat("(")
                .concat(String.join(",",this.projectionExpressionTokens))
                .concat(")");

        this.query = this.query
                .concat(" from ")
                .concat(this.rootEntityName)
                .concat(" ")
                .concat(ROOT_ENTITY_ALIAS);

        StringJoiner whereExpr = new StringJoiner(" AND ");
        while (!this.filterTokens.isEmpty()) {
            whereExpr.add(this.filterTokens.pop());
        }

        if (whereExpr.length() > 0) {
            this.query = this.query.concat(" WHERE ").concat(whereExpr.toString());
        }
    }

    @Override
    public List<FilterTokenDef> getFilterDefs() {
        return this.filterableFields.entrySet()
                    .stream()
                        .map(entry -> {
                            String fieldName = entry.getKey();
                            FieldDef fieldDef = entry.getValue();
                            return new FilterTokenDefImpl(fieldDef.getEntity(),
                                                          fieldName,
                                                          fieldDef.getClassRef(),
                                                          fieldDef.getCaption(),
                                                          (fieldDef.getEntity().equals(this.rootEntityName))?
                                                                CriteriaType.getByType(fieldDef.getClassRef())
                                                                : CriteriaType.ENTITY_REF);
                        }).collect(Collectors.toList());
    }

    @Override
    public List<T> getAll() {
        prepareJpql();
        TypedQuery<T> queryDef = entityManager.createQuery(this.query, this.clazz);
        while (!this.filterParams.isEmpty()){
            int pos = this.filterParams.size();
            Object val = this.filterParams.pop();
            queryDef.setParameter(pos, val);
        }

        return queryDef.getResultList();
    }

    @Override
    public void applyFilter(FilterExpression[] filterExpr) {
        if (filterExpr == null || filterExpr.length == 0) return;
        for (FilterExpression expr : filterExpr){
            String fieldName = expr.getFilterObjectDef().getFieldName();
            CriteriaType criteria = expr.getFilterObjectDef().getCriteriaType();
            PredicateType predicate = expr.getPredicate();
            Object filterValue = expr.getFilterValue();

            if (criteria == CriteriaType.STRING_PATTERN || predicate == null){
                this.addFilter(fieldName, (String)filterValue);
            } else {
                this.addFilter(fieldName, predicate, filterValue);
            }
        }
    }

    public String getFilterableFields() {
        return filterableFields.values().stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public void addFilter(String fieldName, PredicateType predicate, Object value){
        Objects.requireNonNull(fieldName, "Field name passed must be not null");
        Objects.requireNonNull(predicate, "Predicate passed must be not null");
        Objects.requireNonNull(value, "Criteria value passed must be not null");

        FieldDef field = this.filterableFields.get(fieldName);
        Objects.requireNonNull(field, "Field name passed is not filterable");

        if (!predicate.equals(PredicateType.EQ) &&
                (field.getClassRef().equals(String.class) || field.getClassRef().equals(IpAddress.class))){
            throw new IllegalStateException(
                    String.format("Predicate \"%s\"is not applicable to field type %s",
                                   predicate.getOperator(),
                                   field.getClassRef().getSimpleName()));
        }
        int stackPos = this.filterParams.size() + 1; // длина стэка, начиная с единицы

        this.filterTokens.push(
                String.format("%s.%s %s ?%d",
                        ROOT_ENTITY_ALIAS,
                        field.getProjectionPath(),
                        predicate.getOperator(),
                        stackPos)
        );
        this.filterParams.push(value);
    }

    public void addFilter(String fieldName, String pattern){
        Objects.requireNonNull(fieldName, "Field name passed must be not null");
        Objects.requireNonNull(pattern, "Field name passed must be not null");

        FieldDef field = this.filterableFields.get(fieldName);
        Objects.requireNonNull(field, "Field name passed is not filterable");

        if (!field.getClassRef().equals(String.class) && !field.getClassRef().equals(IpAddress.class)){
            throw new IllegalStateException(
                    String.format("Pattern predicate is not applicable to field type %s",
                                   field.getClassRef().getSimpleName()));
        }
        int stackPos = this.filterParams.size() + 1; // длина стэка, начиная с единицы
        this.filterTokens.push(
                String.format("lower(%s.%s) LIKE ?%d",
                        ROOT_ENTITY_ALIAS,
                        field.getProjectionPath(),
                        stackPos)
        );
        this.filterParams.push("%"+pattern.toLowerCase()+"%");
    }

    @Override
    public void useDistinct(boolean mode) {
        this.useDistinct = mode;
    }

    @Override
    public void clearFilters() {
        this.filterParams.clear();
        this.filterTokens.clear();
    }

    public String getQuery() {
        return this.query;
    }
}
