package ViewModels.TableViews.DTO;

import DAL.Annotations.*;

@SourceEntity(entityName = "OrgType")
public class OrgTypeTableRowDTO {
    @Filterable
    @UserCaption(caption = "Тип")
    @SourceField(fieldSource = "OrgType", fieldName = "type_id")
    @CtorParam(position = 1)
    private final String type_id;

    @Filterable
    @UserCaption(caption = "Описание")
    @SourceField(fieldSource = "OrgType", fieldName = "description")
    @CtorParam(position = 2)
    private final String description;

    public OrgTypeTableRowDTO(String type_id, String description) {
        this.type_id = type_id;
        this.description = description;
    }

    public String getType_id() {
        return type_id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "OrgTypeTableRowDTO{" +
                "type_id='" + type_id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
