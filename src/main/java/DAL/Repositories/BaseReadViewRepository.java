package DAL.Repositories;

import Common.Contracts.FieldDef;
import Common.FieldDefImpl;
import DAL.Annotations.Filterable;
import DAL.Annotations.SourceEntity;
import DAL.Annotations.SourceField;
import DAL.Annotations.UserCaption;
import DAL.Contracts.Repository.ReadViewRepository;
import DAL.Utils.Filter.Contracts.FilterDef;
import DAL.Utils.Filter.Contracts.FilterExpression;

import javax.persistence.EntityManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

public class BaseReadViewRepository<T> implements ReadViewRepository<T> {
    private final EntityManager entityManager;
    private final Class<T> clazz;
    private String rootEntityName;
    private final Map<String, FieldDef> fieldMap;
    private final List<String> projectionExpressionTokens;
    private List<String> fromExpressionTokens;
    private final List<String> filterableFields;
    private String query;
    public BaseReadViewRepository(EntityManager entityManager, Class<T> clazz) {
        Objects.requireNonNull(entityManager, "EntityManager object reference must be not null");
        Objects.requireNonNull(clazz, "Base class reference must be not null");
        this.entityManager = entityManager;
        this.clazz = clazz;
        this.projectionExpressionTokens = new ArrayList<>();
        this.fromExpressionTokens = new ArrayList<>();
        this.filterableFields = new ArrayList<>();
        this.fieldMap = new HashMap<>();
        prepareJpql();
    }

    private void prepareJpql(){
        SourceEntity[] entityAttr = clazz.getDeclaredAnnotationsByType(SourceEntity.class);
        if (entityAttr.length == 0){
            throw new IllegalStateException("Illegal class annotations detected");
        }

        this.rootEntityName = entityAttr[0].entityName();

        Constructor[] ctors = clazz.getDeclaredConstructors();
        if (ctors.length == 0){
            throw new IllegalStateException("Illegal default constructor structure detected");
        }

        Constructor constructor = ctors[0];
        for(Parameter p : constructor.getParameters()){
            this.projectionExpressionTokens.add(p.getName());
        }

        Field[] fields = clazz.getDeclaredFields();
        for(Field f: fields){
            UserCaption[] captionAttr = f.getAnnotationsByType(UserCaption.class);
            Filterable[] filterableAttr = f.getAnnotationsByType(Filterable.class);
            SourceField[] sourceAttr = f.getAnnotationsByType(SourceField.class);

            if (captionAttr.length == 0 || sourceAttr.length == 0){
                throw new IllegalStateException("Illegal field annotations detected");
            }

            String caption = captionAttr[0].caption();
            SourceField source = sourceAttr[0];
            String entity = source.fieldSource();
            String field = source.fieldProjectionPath();
            String path = source.fieldProjectionPath();
            filterableFields.add(new FieldDefImpl(entity, field, caption, f.getType()))

            if (filterableAttr.length > 0){
                ;
            }
        }



        this.fromExpressionTokens = this.entityMap.entrySet()
                                                    .stream()
                                                        .map(e->e.getKey()+" "+e.getValue())
                                                            .collect(Collectors.toList());
        this.query = "select ".concat(String.join(",",this.projectionExpressionTokens));
        this.query = this.query.concat(" from ").concat(String.join(",", this.fromExpressionTokens));
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
        return null;
    }

    @Override
    public List<T> getAll(FilterExpression filterExpression) {
        return null;
    }

    public String getFilterableFields() {
        return filterableFields.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public String getQuery() {
        return query;
    }
}
