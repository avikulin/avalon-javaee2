package Common;

import Common.Contracts.FieldDef;

import java.util.Objects;

public class FieldDefImpl implements FieldDef {
    private final String entityName;
    private final String entityAlias;
    private final String fieldName;
    private final String caption;
    private final Class<?> classRef;

    public FieldDefImpl(String entityName, String entityAlias, String fieldName, String caption, Class<?> classRef) {
        Objects.requireNonNull(entityName, "Entity name object reference must be not null");
        Objects.requireNonNull(entityAlias, "Entity alias object reference must be not null");
        Objects.requireNonNull(fieldName, "Field name object reference must be not null");
        Objects.requireNonNull(caption, "Caption object reference must be not null");
        Objects.requireNonNull(classRef, "Class reference must be not null");

        this.entityName = entityName;
        this.entityAlias = entityAlias;
        this.fieldName = fieldName;
        this.caption = caption;
        this.classRef = classRef;
    }

    @Override
    public String getEntity() {
        return this.entityName;
    }

    @Override
    public String getEntityAlias() {
        return this.entityAlias;
    }

    @Override
    public String getName() {
        return this.fieldName;
    }

    @Override
    public String getCaption() {
        return this.caption;
    }

    @Override
    public Class<?> getClassRef() {
        return this.classRef;
    }

    @Override
    public String toString() {
        return "FieldDefImpl{" +
                "entityName='" + entityName + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", caption='" + caption + '\'' +
                ", classRef=" + classRef +
                '}';
    }
}
