package ViewModels.TableViews.DTO;

import DAL.Annotations.*;

import java.util.StringJoiner;

@SourceEntity(entityName = "Location")
public class LocationTableRow {
    @SourceField(fieldSource = "Location", fieldName = "id")
    @CtorParam(position = 1)
    private final Long id;

    @Filterable
    @UserCaption(caption = "Название")
    @SourceField(fieldSource = "Location", fieldName = "locName")
    @CtorParam(position = 2)
    private final String name;

    @Filterable
    @UserCaption(caption = "Организация")
    @SourceField(fieldSource = "Organization", fieldName = "organization", fieldProjectionPath = "organization.fullName")
    @CtorParam(position = 3)
    private final String organization;

    @Filterable
    @UserCaption(caption = "Город")
    @SourceField(fieldSource = "Location", fieldName = "locCity")
    @CtorParam(position = 4)
    private final String locCity;

    @Filterable
    @UserCaption(caption = "Улица")
    @SourceField(fieldSource = "Location", fieldName = "locStreet")
    @CtorParam(position = 5)
    private final String locStreet;

    @Filterable
    @UserCaption(caption = "Дом")
    @SourceField(fieldSource = "Location", fieldName = "locHouseNumber")
    @CtorParam(position = 6)
    private final int locHouseNumber;

    @Filterable
    @UserCaption(caption = "Строение")
    @SourceField(fieldSource = "Location", fieldName = "locBuilding")
    @CtorParam(position = 7)
    private final int locBuilding;

    @Filterable
    @UserCaption(caption = "Квартира")
    @SourceField(fieldSource = "Location", fieldName = "locApartmentNumber")
    @CtorParam(position = 8)
    private final int locApartmentNumber;

    public LocationTableRow(Long id, String name, String organization, String locCity,
                            String locStreet, int locHouseNumber, int locBuilding, int locApartmentNumber) {
        this.id = id;
        this.name = name;
        this.organization = organization;
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

    public String getOrganization() {
        return organization;
    }

    public String getFullAddress() {
        StringJoiner res = new StringJoiner(", ");
        res.add(this.locCity);
        res.add(this.locStreet);
        res.add("д. " + this.locHouseNumber);
        res.add("корп. " + this.locBuilding);
        res.add("кв. " + this.locApartmentNumber);
        return res.toString();
    }
}
