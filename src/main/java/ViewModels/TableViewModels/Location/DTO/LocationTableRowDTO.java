package ViewModels.TableViewModels.Location.DTO;

import Common.Annotations.UserCaption;
import DAL.Annotations.CtorParam;
import DAL.Annotations.Filterable;
import DAL.Annotations.SourceEntity;
import DAL.Annotations.SourceField;

@SourceEntity(entityName = "Location")
public class LocationTableRowDTO {
    @UserCaption(value = "Идентификатор")
    @SourceField(fieldSource = "Location", fieldName = "id")
    @CtorParam(position = 1)
    private final Long id;

    @UserCaption(value = "Название")
    @SourceField(fieldSource = "Location", fieldName = "locName")
    @CtorParam(position = 2)
    private final String name;

    @Filterable
    @UserCaption(value = "Организация")
    @SourceField(fieldSource = "Organization", fieldName = "organizationId", fieldProjectionPath = "organization.id")
    @CtorParam(position = 3)
    private final Long organizationId;

    @UserCaption(value = "Организация")
    @SourceField(fieldSource = "Organization", fieldName = "organizationName", fieldProjectionPath = "organization.fullName")
    @CtorParam(position = 4)
    private final String organizationName;

    @Filterable
    @UserCaption(value = "Город")
    @SourceField(fieldSource = "Location", fieldName = "locCity")
    @CtorParam(position = 5)
    private final String locCity;

    @Filterable
    @UserCaption(value = "Улица")
    @SourceField(fieldSource = "Location", fieldName = "locStreet")
    @CtorParam(position = 6)
    private final String locStreet;

    @Filterable
    @UserCaption(value = "Дом")
    @SourceField(fieldSource = "Location", fieldName = "locHouseNumber")
    @CtorParam(position = 7)
    private final int locHouseNumber;

    @Filterable
    @UserCaption(value = "Строение")
    @SourceField(fieldSource = "Location", fieldName = "locBuilding")
    @CtorParam(position = 8)
    private final int locBuilding;

    @Filterable
    @UserCaption(value = "Квартира")
    @SourceField(fieldSource = "Location", fieldName = "locApartmentNumber")
    @CtorParam(position = 9)
    private final int locApartmentNumber;

    public LocationTableRowDTO(Long id, String name, Long organizationId, String organizationName, String locCity,
                               String locStreet, int locHouseNumber, int locBuilding, int locApartmentNumber) {
        this.id = id;
        this.name = name;
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.locCity = locCity;
        this.locStreet = locStreet;
        this.locHouseNumber = locHouseNumber;
        this.locBuilding = locBuilding;
        this.locApartmentNumber = locApartmentNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getLocCity() {
        return locCity;
    }

    public String getLocStreet() {
        return locStreet;
    }

    public int getLocHouseNumber() {
        return locHouseNumber;
    }

    public int getLocBuilding() {
        return locBuilding;
    }

    public int getLocApartmentNumber() {
        return locApartmentNumber;
    }

    @Override
    public String toString() {
        return "LocationTableRowDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", organization='" + organizationName + '\'' +
                ", locCity='" + locCity + '\'' +
                ", locStreet='" + locStreet + '\'' +
                ", locHouseNumber=" + locHouseNumber +
                ", locBuilding=" + locBuilding +
                ", locApartmentNumber=" + locApartmentNumber +
                '}';
    }
}
