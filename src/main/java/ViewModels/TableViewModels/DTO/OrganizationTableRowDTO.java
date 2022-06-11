package ViewModels.TableViewModels.DTO;

import Common.Annotations.UserCaption;
import DAL.Annotations.*;
import DAL.DataEntities.Dictionaries.Country;

@SourceEntity(entityName = "Organization")
public class OrganizationTableRowDTO {
    @UserCaption(value = "Идентификатор")
    @SourceField(fieldSource = "Organization", fieldName = "id")
    @CtorParam(position = 1)
    private final Long id;

    @Filterable
    @UserCaption(value = "Наименование организации")
    @SourceField(fieldSource = "Organization", fieldName = "fullName")
    @CtorParam(position = 2)
    private final String fullName;

    @Filterable
    @UserCaption(value = "ИНН")
    @SourceField(fieldSource = "Organization", fieldName = "innCode")
    @CtorParam(position = 3)
    private String innCode;

    @Filterable
    @UserCaption(value = "КПП")
    @SourceField(fieldSource = "Organization", fieldName = "kppCode")
    @CtorParam(position = 4)
    private String kppCode;

    @Filterable
    @UserCaption(value = "ОГРН")
    @SourceField(fieldSource = "Organization", fieldName = "ogrnCode")
    @CtorParam(position = 5)
    private String ogrnCode;

    @UserCaption(value = "Веб-сайт")
    @SourceField(fieldSource = "Organization", fieldName = "webSiteUrl")
    @CtorParam(position = 6)
    private String webSiteUrl;

    @Filterable
    @UserCaption(value = "Страна")
    @SourceField(fieldSource = "Country", fieldName = "fullName", fieldProjectionPath = "countryOfRegistration.fullName")
    @CtorParam(position = 7)
    private String countryOfRegistration;

    public OrganizationTableRowDTO(Long id, String fullName, String innCode, String kppCode, String ogrnCode, String webSiteUrl, String countryOfRegistration) {
        this.id = id;
        this.fullName = fullName;
        this.innCode = innCode;
        this.kppCode = kppCode;
        this.ogrnCode = ogrnCode;
        this.webSiteUrl = webSiteUrl;
        this.countryOfRegistration = countryOfRegistration;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getInnCode() {
        return innCode;
    }

    public String getKppCode() {
        return kppCode;
    }

    public String getOgrnCode() {
        return ogrnCode;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public String getCountryOfRegistration() {
        return countryOfRegistration;
    }

    @Override
    public String toString() {
        return "OrganizationTableRowDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", innCode='" + innCode + '\'' +
                ", kppCode='" + kppCode + '\'' +
                ", ogrnCode='" + ogrnCode + '\'' +
                ", webSiteUrl='" + webSiteUrl + '\'' +
                ", countryOfRegistration=" + countryOfRegistration +
                '}';
    }
}
