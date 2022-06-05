package ViewModels.TableViews.DTO;

import Common.Annotations.UserCaption;
import DAL.Annotations.*;

@SourceEntity(entityName = "Country")
public class CountryTableRowDTO {
    @UserCaption(value = "Идентификатор")
    @SourceField(fieldSource = "", fieldName = "id")
    @CtorParam(position = 1)
    private final String id;

    @Filterable
    @UserCaption(value = "Код")
    @SourceField(fieldSource = "Country", fieldName = "code")
    @CtorParam(position = 2)
    private final String code;

    @Filterable
    @UserCaption(value = "Название")
    @SourceField(fieldSource = "Country", fieldName = "shortName")
    @CtorParam(position = 3)
    private final String shortName;

    @Filterable
    @UserCaption(value = "Полное наименование")
    @SourceField(fieldSource = "Country", fieldName = "fullName")
    @CtorParam(position = 4)
    private final String fullName;

    @Filterable
    @UserCaption(value = "Регион")
    @SourceField(fieldSource = "Country", fieldName = "region")
    @CtorParam(position = 5)
    private final String region;

    public CountryTableRowDTO(String id, String code, String shortName, String fullName, String region) {
        this.id = id;
        this.code = code;
        this.shortName = shortName;
        this.fullName = fullName;
        this.region = region;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "CountryTableRowDTO{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
