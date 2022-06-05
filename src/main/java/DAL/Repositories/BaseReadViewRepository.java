package DAL.Repositories;

import Common.Annotations.UserCaption;
import Common.Contracts.FieldDef;
import Common.FieldDefImpl;
import Common.IpAddress;
import DAL.Annotations.*;
import DAL.Contracts.Repository.ReadViewRepository;
import DAL.Utils.Filter.Contracts.FilterDef;
import DAL.Utils.Filter.Enums.CriteriaType;
import DAL.Utils.Filter.Enums.PredicateType;
import DAL.Utils.Filter.FilterDefImpl;

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
    private final Map<Integer, FieldDef> fieldMap;
    private List<String> projectionExpressionTokens;

    private final Map<String, FieldDef> filterableFields;
    private final Deque<Object> filterParams;
    private final Deque<String> filterTokens;
    private String query;

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
    }

    private void prepareJpql(){
        SourceEntity[] entityAttr = clazz.getDeclaredAnnotationsByType(SourceEntity.class);
        if (entityAttr.length == 0){
            throw new IllegalStateException("Illegal class annotations detected");
        }

        String rootEntityName = entityAttr[0].entityName();

        Constructor[] ctors = clazz.getDeclaredConstructors();
        if (ctors.length == 0){
            throw new IllegalStateException("Illegal default constructor structure detected");
        }

        Constructor<T> ctor = ctors[0];

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
            String alias = ROOT_ENTITY_ALIAS;
            String field = source.fieldName();
            String path = source.fieldProjectionPath().isEmpty() ? source.fieldName() : source.fieldProjectionPath();

            FieldDef fd = new FieldDefImpl(entity, alias, field, path, caption, f.getType());
            if (ctorParamsAttr.length > 0) {
                int pos = ctorParamsAttr[0].position();
                this.fieldMap.put(pos, fd);
            }

            if (filterableAttr.length > 0){
                filterableFields.put(f.getName(), fd);
            }
        }

        this.projectionExpressionTokens = this.fieldMap.entrySet()
                                                    .stream()
                                                        .map(e -> ROOT_ENTITY_ALIAS
                                                                    +"."+e.getValue().getProjectionPath())
                                                                    .collect(Collectors.toList());
        this.query = "select new "
                .concat(ctor.getName())
                .concat("(")
                .concat(String.join(",",this.projectionExpressionTokens))
                .concat(")");

        this.query = this.query
                .concat(" from ")
                .concat(rootEntityName)
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
    public List<FilterDef> getFilterDefs() {
        return this.filterableFields.values()
                    .stream()
                        .map(fd -> new FilterDefImpl(fd.getEntity(),
                                                     fd.getName(),
                                                     fd.getCaption(),
                                                     CriteriaType.getByType(fd.getClass())
                            )).collect(Collectors.toList());
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

    public String getFilterableFields() {
        return filterableFields.values().stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public void addFilter(String fieldName, PredicateType predicate, Object value){
        Objects.requireNonNull(fieldName, "Field name passed must be not null");
        Objects.requireNonNull(predicate, "Predicate passed must be not null");
        Objects.requireNonNull(value, "Criteria value passed must be not null");

        FieldDef field = this.filterableFields.get(fieldName);
        Objects.requireNonNull(field, "Field name passed is not filterable");

        /*if (!field.getClassRef().equals(value.getClass())){
            throw new IllegalArgumentException("Criteria type must be ".concat(field.getClassRef().getSimpleName()));
        }*/

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
                String.format("%s.%s LIKE ?%d",
                        ROOT_ENTITY_ALIAS,
                        field.getProjectionPath(),
                        stackPos)
        );
        this.filterParams.push("%"+pattern+"%");
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
