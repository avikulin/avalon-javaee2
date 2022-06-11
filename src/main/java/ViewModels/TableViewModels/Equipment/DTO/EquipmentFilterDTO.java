package ViewModels.TableViewModels.Equipment.DTO;

import Common.Annotations.UserCaption;
import Views.Annotations.WebDtoFieldGetter;
import Views.Annotations.WebDtoFieldSetter;
import Views.Annotations.WebFormModelDTO;
import Views.Templates.BaseWebFormDTO;

@WebFormModelDTO
public class EquipmentFilterDTO  extends BaseWebFormDTO {

    private static final String VENDOR_ID = "vendorId";
    private static final String MODEL_ID = "modelId";
    public static final String ORGANIZATION_ID = "organizationId";
    private static final String LOCATION_ID = "locationId";
    public static final String OSI_LAYER_ID = "layer";
    public static final String FILTER_STRING = "searchString";

    String vendorId;
    String modelId;
    String locationId;
    String organizationId;
    String osiLayerId;
    String filterString;

    @WebDtoFieldGetter(fieldName = VENDOR_ID)
    public String getVendorId() {
        return vendorId;
    }

    @UserCaption("Производитель")
    @WebDtoFieldSetter(fieldName = VENDOR_ID)
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    @WebDtoFieldGetter(fieldName = MODEL_ID)
    public String getModelId() {
        return modelId;
    }

    @UserCaption("Модель")
    @WebDtoFieldSetter(fieldName = MODEL_ID)
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    @WebDtoFieldGetter(fieldName = LOCATION_ID)
    public String getLocationId() {
        return locationId;
    }

    @UserCaption("Местоположение")
    @WebDtoFieldSetter(fieldName = LOCATION_ID)
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @WebDtoFieldGetter(fieldName = ORGANIZATION_ID)
    public String getOrganizationId() {
        return organizationId;
    }

    @UserCaption("Организация")
    @WebDtoFieldSetter(fieldName = ORGANIZATION_ID)
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @WebDtoFieldGetter(fieldName = OSI_LAYER_ID)
    public String getOsiLayerId() {
        return osiLayerId;
    }

    @UserCaption("Уровень OSI")
    @WebDtoFieldSetter(fieldName = OSI_LAYER_ID)
    public void setOsiLayerId(String osiLayerId) {
        this.osiLayerId = osiLayerId;
    }

    @WebDtoFieldGetter(fieldName = FILTER_STRING)
    public String getFilterString() {
        return filterString;
    }

    @UserCaption("Строка поиска")
    @WebDtoFieldSetter(fieldName = FILTER_STRING)
    public void setFilterString(String filterString) {
        this.filterString = filterString;
    }
}
