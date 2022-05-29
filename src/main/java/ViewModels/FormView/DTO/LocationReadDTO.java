package ViewModels.FormView.DTO;

public class LocationReadDTO {
    private final Long id;
    private final String locationName;
    private final String organizationId;
    private final String cityAddress;
    private final String streetAddress;
    private final String houseNumberAddress;
    private final String buildingAddress;
    private final String apartmentAddress;
    private final String additionalAddress;

    public LocationReadDTO(Long id,
                           String locationName,
                           String organizationId,
                           String cityAddress,
                           String streetAddress,
                           String houseNumberAddress,
                           String buildingAddress,
                           String apartmentAddress,
                           String additionalAddress) {
        this.id = id;
        this.locationName = locationName;
        this.organizationId = organizationId;
        this.cityAddress = cityAddress;
        this.streetAddress = streetAddress;
        this.houseNumberAddress = houseNumberAddress;
        this.buildingAddress = buildingAddress;
        this.apartmentAddress = apartmentAddress;
        this.additionalAddress = additionalAddress;
    }

    public Long getId() {
        return id;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getHouseNumberAddress() {
        return houseNumberAddress;
    }

    public String getBuildingAddress() {
        return buildingAddress;
    }

    public String getApartmentAddress() {
        return apartmentAddress;
    }

    public String getAdditionalAddress() {
        return additionalAddress;
    }
}
