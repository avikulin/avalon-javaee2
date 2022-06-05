package ViewModels.TableViews.DTO;

import Common.Annotations.UserCaption;
import DAL.Annotations.*;

@SourceEntity(entityName = "Sex")
public class SexTableRowDTO {
    @UserCaption(value = "Идентификатор")
    @SourceField(fieldSource = "Sex", fieldName = "id")
    @CtorParam(position = 1)
    private final String id;

    @Filterable
    @UserCaption(value = "Название")
    @SourceField(fieldSource = "Sex", fieldName = "name")
    @CtorParam(position = 2)
    private final String name;

    public SexTableRowDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SexTableRowDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
