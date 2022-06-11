package ViewModels.TableViewModels.Equipment.DTO;

import Common.Annotations.UserCaption;
import Common.Classes.IpAddress;
import DAL.Annotations.*;
import DAL.DataEntities.Enums.OsiLayer;

@SourceEntity(entityName = "Equipment")
public class EquipmentTableRowDTO {
    @UserCaption(value = "Идентификатор")
    @SourceField(fieldSource = "Equipment", fieldName = "id")
    @CtorParam(position = 1)
    private final Long unitId;

    @Filterable
    @UserCaption(value = "Код ЕО")
    @SourceField(fieldSource = "Equipment", fieldName = "code")
    @CtorParam(position = 2)
    private final String unitCode;

    @Filterable
    @UserCaption(value = "Модель")
    @SourceField(fieldSource = "Model", fieldName = "modelCode", fieldProjectionPath = "model.id")
    @CtorParam(position = 3)
    private final String modelId;

    @UserCaption(value = "Модель")
    @SourceField(fieldSource = "Model", fieldName = "modelCode", fieldProjectionPath = "model.modelCode")
    @CtorParam(position = 4)
    private final String modelCode;

    @Filterable
    @UserCaption(value = "Описание")
    @SourceField(fieldSource = "Equipment", fieldName = "description")
    @CtorParam(position = 5)
    private final String description;

    @Filterable
    @UserCaption(value = "Производитель")
    @SourceField(fieldSource = "Vendor", fieldName = "name", fieldProjectionPath = "model.vendor.id")
    @CtorParam(position = 6)
    private final Long vendorId;

    @UserCaption(value = "Производитель")
    @SourceField(fieldSource = "Vendor", fieldName = "name", fieldProjectionPath = "model.vendor.name")
    @CtorParam(position = 7)
    private final String vendorName;

    @Filterable
    @UserCaption(value = "Уровень OSI")
    @SourceField(fieldSource = "Model", fieldName = "layerNum", fieldProjectionPath = "model.layerNum")
    @CtorParam(position = 8)
    private final OsiLayer layer;

    @Filterable
    @UserCaption(value = "IP-адрес")
    @SourceField(fieldSource = "Equipment", fieldName = "ipAddress.ipAddressValue")
    @CtorParam(position = 9)
    private final String networkAddress;

    @Filterable
    @UserCaption(value = "Организация")
    @SourceField(fieldSource = "Organization", fieldName = "name", fieldProjectionPath = "location.organization.id")
    @CtorParam(position = 10)
    private final Long organizationId;

    @UserCaption(value = "Организация")
    @SourceField(fieldSource = "Organization", fieldName = "name", fieldProjectionPath = "location.organization.fullName")
    @CtorParam(position = 11)
    private final String organization;

    @Filterable
    @UserCaption(value = "Местоположение")
    @SourceField(fieldSource = "Location", fieldName = "locName", fieldProjectionPath = "location.id")
    @CtorParam(position = 12)
    private final Long locationId;

    @Filterable
    @UserCaption(value = "Местоположение")
    @SourceField(fieldSource = "Location", fieldName = "locName", fieldProjectionPath = "location.locName")
    @CtorParam(position = 13)
    private final String locationName;

    public EquipmentTableRowDTO(Long unitId, String unitCode, String modelId, String modelCode, String description,
                                Long vendorId, String vendorName, OsiLayer layer, String networkAddress,
                                Long organizationId, String organization, Long locationId, String locationName) {
        this.unitId = unitId;
        this.unitCode = unitCode;
        this.modelId = modelId;
        this.modelCode = modelCode;
        this.description = description;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.layer = layer;
        this.networkAddress = networkAddress;
        this.organizationId = organizationId;
        this.organization = organization;
        this.locationId = locationId;
        this.locationName = locationName;
    }

    public Long getUnitId() {
        return unitId;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public String getModelId() {
        return modelId;
    }

    public String getModelCode() {
        return modelCode;
    }

    public String getDescription() {
        return description;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getLayer() {
        return layer.getDescription();
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public String getOrganization() {
        return organization;
    }

    public Long getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    @Override
    public String toString() {
        return "EquipmentTableRowDTO{" +
                "unitId=" + unitId +
                ", unitCode='" + unitCode + '\'' +
                ", modelId='" + modelId + '\'' +
                ", modelCode='" + modelCode + '\'' +
                ", description='" + description + '\'' +
                ", vendorId=" + vendorId +
                ", vendorName='" + vendorName + '\'' +
                ", layer=" + layer +
                ", networkAddress=" + networkAddress +
                ", organizationId=" + organizationId +
                ", organization='" + organization + '\'' +
                ", locationId='" + locationId + '\'' +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
