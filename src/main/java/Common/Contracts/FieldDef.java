package Common.Contracts;

public interface FieldDef {
    String getEntity();
    String getEntityAlias();
    String getName();
    String getCaption();
    Class<?> getClassRef();
}
