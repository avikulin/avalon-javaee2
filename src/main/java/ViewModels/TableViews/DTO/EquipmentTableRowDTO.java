package ViewModels.TableViews.DTO;

import Common.Annotations.UserCaption;
import Common.IpAddress;
import DAL.Annotations.*;
import DAL.DataEntities.Enums.OsiLayer;

@SourceEntity(entityName = "Equipment")
public class EquipmentTableRowDTO {
    @Filterable
    @UserCaption(value = "Идентификатор")
    @SourceField(fieldSource = "Equipment", fieldName = "id")
    @CtorParam(position = 1)
    private final String unitId;

    @Filterable
    @UserCaption(value = "Модель")
    @SourceField(fieldSource = "Model", fieldName = "modelCode", fieldProjectionPath = "model.modelCode")
    @CtorParam(position = 2)
    private final String modelCode;

    @Filterable
    @UserCaption(value = "Производитель")
    @SourceField(fieldSource = "Vendor", fieldName = "name", fieldProjectionPath = "model.vendor.name")
    @CtorParam(position = 3)
    private final String vendorName;

    @Filterable
    @UserCaption(value = "Уровень OSI")
    @SourceField(fieldSource = "Model", fieldName = "layerNum", fieldProjectionPath = "model.layerNum")
    @CtorParam(position = 4)
    private final OsiLayer layer;

    @Filterable
    @UserCaption(value = "IP-адрес")
    @SourceField(fieldSource = "Equipment", fieldName = "ipAddress")
    @CtorParam(position = 5)
    private final IpAddress networkAddress;

    @Filterable
    @UserCaption(value = "Организация")
    @SourceField(fieldSource = "Organization", fieldName = "name", fieldProjectionPath = "location.organization.fullName")
    @CtorParam(position = 6)
    private final String organization;

    @Filterable
    @UserCaption(value = "Расположения")
    @SourceField(fieldSource = "Location", fieldName = "locName", fieldProjectionPath = "location.locName")
    @CtorParam(position = 7)
    private final String locationName;

    public EquipmentTableRowDTO(String unitId, String modelCode, String vendorName, OsiLayer layer,
                                IpAddress networkAddress, String organization, String locationName) {
        this.unitId = unitId;
        this.modelCode = modelCode;
        this.vendorName = vendorName;
        this.layer = layer;
        this.networkAddress = networkAddress;
        this.organization = organization;
        this.locationName = locationName;
    }

    public String getUnitId() {
        return unitId;
    }

    public String getModelCode() {
        return modelCode;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getLayer() {
        return layer.getDescription();
    }

    public String getNetworkAddress() {
        return networkAddress.toString();
    }

    public String getOrganization() {
        return organization;
    }

    public String getLocationName() {
        return locationName;
    }

    @Override
    public String toString() {
        return "EquipmentTableRowDTO{" +
                "unitId='" + unitId + '\'' +
                ", modelCode='" + modelCode + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", layer=" + layer +
                ", networkAddress=" + networkAddress +
                ", organization='" + organization + '\'' +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
