package ViewModels.FormViewModels.Location.DTO;

import Common.Annotations.UserCaption;
import Views.Annotations.WebDtoFieldGetter;
import Views.Annotations.WebDtoFieldSetter;
import Views.Annotations.WebFormModelDTO;
import Views.Enums.Validator;
import Views.Templates.BaseWebFormDTO;

@WebFormModelDTO
public class LocationFormDTO extends BaseWebFormDTO {
    public static final String LOCATION_ID = "locationId";
    public static final String LOCATION_NAME = "locationName";
    public static final String ORGANIZATION_ID = "organizationId";
    public static final String CITY_ADDRESS = "cityAddress";
    public static final String STREET_ADDRESS = "streetAddress";
    public static final String HOUSE_NUMBER_ADDRESS = "houseNumberAddress";
    public static final String BUILDING_ADDRESS = "buildingNumberAddress";
    public static final String APARTMENT_ADDRESS = "apartmentNumberAddress";
    public static final String ADDITIONAL_INFO = "additionalInfoAddress";

    private String id;
    private String locationName;
    private String organizationId;
    private String cityAddress;
    private String streetAddress;
    private String houseNumberAddress;
    private String buildingAddress;
    private String apartmentAddress;
    private String additionalAddress;

    public LocationFormDTO() {
        super();
    }

    public LocationFormDTO(String id,
                           String locationName,
                           String organizationId,
                           String cityAddress,
                           String streetAddress,
                           String houseNumberAddress,
                           String buildingAddress,
                           String apartmentAddress,
                           String additionalAddress) {
        super();
        setId(id);
        setLocationName(locationName);
        setOrganizationId(organizationId);
        setCityAddress(cityAddress);
        setStreetAddress(streetAddress);
        setHouseNumberAddress(houseNumberAddress);
        setBuildingAddress(buildingAddress);
        setApartmentAddress(apartmentAddress);
        setAdditionalAddress(additionalAddress);
    }

    @WebDtoFieldGetter(fieldName = LOCATION_ID)
    public String getId() {
        return id;
    }

    @WebDtoFieldGetter(fieldName = LOCATION_NAME)
    public String getLocationName() {
        return locationName;
    }

    @WebDtoFieldGetter(fieldName = ORGANIZATION_ID)
    public String getOrganizationId() {
        return organizationId;
    }

    @WebDtoFieldGetter(fieldName = CITY_ADDRESS)
    public String getCityAddress() {
        return cityAddress;
    }

    @WebDtoFieldGetter(fieldName = STREET_ADDRESS)
    public String getStreetAddress() {
        return streetAddress;
    }

    @WebDtoFieldGetter(fieldName = HOUSE_NUMBER_ADDRESS)
    public String getHouseNumberAddress() {
        return houseNumberAddress;
    }

    @WebDtoFieldGetter(fieldName = BUILDING_ADDRESS)
    public String getBuildingAddress() {
        return buildingAddress;
    }

    @WebDtoFieldGetter(fieldName = APARTMENT_ADDRESS)
    public String getApartmentAddress() {
        return apartmentAddress;
    }

    @WebDtoFieldGetter(fieldName = ADDITIONAL_INFO)
    public String getAdditionalAddress() {
        return additionalAddress;
    }

    @UserCaption("Идентификатор")
    @WebDtoFieldSetter(fieldName = LOCATION_ID, rules = {Validator.INTEGER_VALUE_TYPE})
    private void setId(String value){
        this.id = value;
    }

    @UserCaption("Название местоположения")
    @WebDtoFieldSetter(fieldName = LOCATION_NAME, mayBeEmpty = false, rules = {Validator.LANG_RU, Validator.ONLY_LETTERS})
    private void setLocationName(String value){
        this.locationName = value;
    }

    @UserCaption("Организация")
    @WebDtoFieldSetter(fieldName = ORGANIZATION_ID, mayBeEmpty = false)
    private void setOrganizationId(String value){
        this.organizationId = value;
    }

    @UserCaption("Населенный пункт")
    @WebDtoFieldSetter(fieldName = CITY_ADDRESS, mayBeEmpty = false, rules = {Validator.LANG_RU, Validator.ONLY_LETTERS})
    private void setCityAddress(String value){
        this.cityAddress = value;
    }

    @UserCaption("Название улицы")
    @WebDtoFieldSetter(fieldName = STREET_ADDRESS, mayBeEmpty = false, rules = {Validator.LANG_RU, Validator.ONLY_LETTERS})
    private void setStreetAddress(String value){
        this.streetAddress = value;
    }

    @UserCaption("Номер дома")
    @WebDtoFieldSetter(fieldName = HOUSE_NUMBER_ADDRESS, mayBeEmpty = false, rules = {Validator.INTEGER_VALUE_TYPE})
    private void setHouseNumberAddress(String value){
        this.houseNumberAddress = value;
    }

    @UserCaption("Номер строения (корпуса)")
    @WebDtoFieldSetter(fieldName = BUILDING_ADDRESS, mayBeEmpty = false, rules = {Validator.INTEGER_VALUE_TYPE})
    private void setBuildingAddress(String value){
        this.buildingAddress = value;
    }

    @UserCaption("Номер квартиры")
    @WebDtoFieldSetter(fieldName = APARTMENT_ADDRESS, mayBeEmpty = false, rules = {Validator.INTEGER_VALUE_TYPE})
    private void setApartmentAddress(String value){
        this.apartmentAddress = value;
    }

    @UserCaption("Дополнительная информация")
    @WebDtoFieldSetter(fieldName = ADDITIONAL_INFO)
    private void setAdditionalAddress(String value){
        this.additionalAddress = value;
    }
}
