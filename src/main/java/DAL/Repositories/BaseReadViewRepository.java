package DAL.Repositories;

import Common.Contracts.FieldDef;
import Common.FieldDefImpl;
import DAL.Annotations.*;
import DAL.Contracts.Repository.ReadViewRepository;
import DAL.Utils.Filter.Contracts.FilterDef;
import DAL.Utils.Filter.Contracts.FilterExpression;
import DAL.Utils.Filter.Enums.CriteriaType;
import DAL.Utils.Filter.FilterDefImpl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

public class BaseReadViewRepository<T> implements ReadViewRepository<T> {
    private final EntityManager entityManager;
    private final Class<T> clazz;
    private final Map<Integer, FieldDef> fieldMap;
    private final Map<String, String> entityMap;
    private List<String> projectionExpressionTokens;
    private List<String> fromExpressionTokens;
    private final List<FieldDef> filterableFields;
    private String query;
    public BaseReadViewRepository(EntityManager entityManager, Class<T> clazz) {
        Objects.requireNonNull(entityManager, "EntityManager object reference must be not null");
        Objects.requireNonNull(clazz, "Base class reference must be not null");
        this.entityManager = entityManager;
        this.clazz = clazz;
        this.projectionExpressionTokens = new ArrayList<>();
        this.fromExpressionTokens = new ArrayList<>();
        this.filterableFields = new ArrayList<>();
        this.fieldMap = new TreeMap<>();
        this.entityMap = new HashMap<>();
        prepareJpql();
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

            String caption = captionAttr[0].caption();
            SourceField source = sourceAttr[0];
            String entity = source.fieldSource();
            String alias = registerEntity(entity);
            String field = source.fieldProjectionPath();
            String path = source.fieldProjectionPath();

            FieldDef fd = new FieldDefImpl(entity, alias, field, path, caption, f.getType());
            if (ctorParamsAttr.length > 0) {
                int pos = ctorParamsAttr[0].position();
                this.fieldMap.put(pos, fd);
            }

            if (filterableAttr.length > 0){
                filterableFields.add(fd);
            }
        }



        this.fromExpressionTokens = this.entityMap.entrySet()
                                                    .stream()
                                                        .map(e->e.getKey()+" "+e.getValue())
                                                            .collect(Collectors.toList());
        String rootAlias = this.entityMap.get(rootEntityName);
        this.projectionExpressionTokens = this.fieldMap.entrySet()
                                                    .stream()
                                                        .map(e -> rootAlias+"."+e.getValue().getProjectionPath())
                                                                    .collect(Collectors.toList());
        this.query = "select new "
                .concat(ctor.getName())
                .concat("(")
                .concat(String.join(",",this.projectionExpressionTokens))
                .concat(")");
        this.query = this.query.concat(" from ").concat(String.join(", ", this.fromExpressionTokens));
    }

    private String registerEntity(String entity){
        String value = entityMap.get(entity);
        if (value == null){
            int count = entityMap.size() + 1;
            value = "e" + count;
            this.entityMap.put(entity, value);
        }
        return value;
    }

    @Override
    public List<FilterDef> getFilterDefs() {
        return this.filterableFields
                    .stream()
                        .map(f-> new FilterDefImpl(f.getEntity(),
                                                   f.getName(),
                                                   f.getCaption(),
                                                   CriteriaType.getByType(f.getClass())
                                                  ))
                        .collect(Collectors.toList());
    }

    @Override
    public List<T> getAll(FilterExpression filterExpression) {
        TypedQuery<T> queryDef = entityManager.createQuery(this.query, this.clazz);
        return queryDef.getResultList();
    }

    public String getFilterableFields() {
        return filterableFields.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public String getQuery() {
        return this.query;
    }
}
