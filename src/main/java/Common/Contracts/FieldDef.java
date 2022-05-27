package Common.Contracts;

public interface FieldDef {
    String getEntity();
    String getEntityAlias();
    String getName();
    String getProjectionPath();
    String getCaption();
    Class<?> getClassRef();
}
