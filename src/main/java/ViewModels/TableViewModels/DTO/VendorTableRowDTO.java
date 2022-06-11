package ViewModels.TableViewModels.DTO;

import Common.Annotations.UserCaption;
import DAL.Annotations.*;

@SourceEntity(entityName = "Vendor")
public class VendorTableRowDTO {
    @Filterable
    @UserCaption(value = "Идентификатор")
    @SourceField(fieldSource = "Vendor", fieldName = "id")
    @CtorParam(position = 1)
    private final Long id;

    @Filterable
    @UserCaption(value = "Наименование")
    @SourceField(fieldSource = "Vendor", fieldName = "name")
    @CtorParam(position = 2)
    private final String name;

    @Filterable
    @UserCaption(value = "Страна")
    @SourceField(fieldSource = "Vendor", fieldName = "countryOfOrigin", fieldProjectionPath = "countryOfOrigin.fullName")
    @CtorParam(position = 3)
    private final String countryOfOrigin;

    @UserCaption(value = "Веб-сайт")
    @SourceField(fieldSource = "Vendor", fieldName = "webSite")
    @CtorParam(position = 4)
    private final String webSite;

    public VendorTableRowDTO(Long id, String name, String countryOfOrigin, String webSite) {
        this.id = id;
        this.name = name;
        this.countryOfOrigin = countryOfOrigin;
        this.webSite = webSite;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public String getWebSite() {
        return webSite;
    }

    @Override
    public String toString() {
        return "VendorTableRowDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryOfOrigin=" + countryOfOrigin +
                ", webSite='" + webSite + '\'' +
                '}';
    }
}
