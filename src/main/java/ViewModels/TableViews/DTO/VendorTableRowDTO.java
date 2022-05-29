package ViewModels.TableViews.DTO;

import DAL.Annotations.*;

@SourceEntity(entityName = "Vendor")
public class VendorTableRowDTO {
    @Filterable
    @SourceField(fieldSource = "Vendor", fieldName = "id")
    @CtorParam(position = 1)
    private final Long id;

    @Filterable
    @UserCaption(caption = "Наименование")
    @SourceField(fieldSource = "Vendor", fieldName = "name")
    @CtorParam(position = 2)
    private final String name;

    @Filterable
    @UserCaption(caption = "Страна")
    @SourceField(fieldSource = "Vendor", fieldName = "countryOfOrigin", fieldProjectionPath = "countryOfOrigin.fullName")
    @CtorParam(position = 3)
    private final String countryOfOrigin;

    @UserCaption(caption = "Веб-сайт")
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
