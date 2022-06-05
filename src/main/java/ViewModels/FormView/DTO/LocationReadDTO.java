package ViewModels.FormView.DTO;

import Common.Annotations.UserCaption;
import Views.Annotations.WebFieldGetter;
import Views.Annotations.WebFieldSetter;
import Views.Annotations.WebFormModel;
import Views.Enums.Validator;
import Views.Templates.BaseWebForm;

@WebFormModel
public class LocationReadDTO extends BaseWebForm {
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

    public LocationReadDTO() {
        super();
    }

    public LocationReadDTO(String id,
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

    @WebFieldGetter(fieldName = LOCATION_ID)
    public String getId() {
        return id;
    }

    @WebFieldGetter(fieldName = LOCATION_NAME)
    public String getLocationName() {
        return locationName;
    }

    @WebFieldGetter(fieldName = ORGANIZATION_ID)
    public String getOrganizationId() {
        return organizationId;
    }

    @WebFieldGetter(fieldName = CITY_ADDRESS)
    public String getCityAddress() {
        return cityAddress;
    }

    @WebFieldGetter(fieldName = STREET_ADDRESS)
    public String getStreetAddress() {
        return streetAddress;
    }

    @WebFieldGetter(fieldName = HOUSE_NUMBER_ADDRESS)
    public String getHouseNumberAddress() {
        return houseNumberAddress;
    }

    @WebFieldGetter(fieldName = BUILDING_ADDRESS)
    public String getBuildingAddress() {
        return buildingAddress;
    }

    @WebFieldGetter(fieldName = APARTMENT_ADDRESS)
    public String getApartmentAddress() {
        return apartmentAddress;
    }

    @WebFieldGetter(fieldName = ADDITIONAL_INFO)
    public String getAdditionalAddress() {
        return additionalAddress;
    }

    @UserCaption("Идентификатор")
    @WebFieldSetter(fieldName = LOCATION_ID, mayBeEmpty = true, rules = {Validator.INTEGER_VALUE_TYPE})
    private void setId(String value){
        this.id = value;
    }

    @UserCaption("Название местоположения")
    @WebFieldSetter(fieldName = LOCATION_NAME, mayBeEmpty = false, rules = {Validator.LANG_RU, Validator.ONLY_LETTERS})
    private void setLocationName(String value){
        this.locationName = value;
    }

    @UserCaption("Организация")
    @WebFieldSetter(fieldName = ORGANIZATION_ID, mayBeEmpty = false, rules = {})
    private void setOrganizationId(String value){
        this.organizationId = value;
    }

    @UserCaption("Населенный пункт")
    @WebFieldSetter(fieldName = CITY_ADDRESS, mayBeEmpty = false, rules = {Validator.LANG_RU, Validator.ONLY_LETTERS})
    private void setCityAddress(String value){
        this.cityAddress = value;
    }

    @UserCaption("Название улицы")
    @WebFieldSetter(fieldName = STREET_ADDRESS, mayBeEmpty = false, rules = {Validator.LANG_RU, Validator.ONLY_LETTERS})
    private void setStreetAddress(String value){
        this.streetAddress = value;
    }

    @UserCaption("Номер дома")
    @WebFieldSetter(fieldName = HOUSE_NUMBER_ADDRESS, mayBeEmpty = false, rules = {Validator.INTEGER_VALUE_TYPE})
    private void setHouseNumberAddress(String value){
        this.houseNumberAddress = value;
    }

    @UserCaption("Номер строения (корпуса)")
    @WebFieldSetter(fieldName = BUILDING_ADDRESS, mayBeEmpty = false, rules = {Validator.INTEGER_VALUE_TYPE})
    private void setBuildingAddress(String value){
        this.buildingAddress = value;
    }

    @UserCaption("Номер квартиры")
    @WebFieldSetter(fieldName = APARTMENT_ADDRESS, mayBeEmpty = false, rules = {Validator.INTEGER_VALUE_TYPE})
    private void setApartmentAddress(String value){
        this.apartmentAddress = value;
    }

    @UserCaption("Дополнительная информация")
    @WebFieldSetter(fieldName = ADDITIONAL_INFO, mayBeEmpty = true, rules = {})
    private void setAdditionalAddress(String value){
        this.additionalAddress = value;
    }
}
