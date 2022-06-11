package ViewModels.FormViewModels.Equpment.DTO;

import Common.Annotations.UserCaption;
import Views.Annotations.WebDtoFieldGetter;
import Views.Annotations.WebDtoFieldSetter;
import Views.Annotations.WebFormModelDTO;
import Views.Enums.Validator;
import Views.Templates.BaseWebFormDTO;

@WebFormModelDTO
public class EquipmentFormDTO extends BaseWebFormDTO {
    private static final String EQUIPMENT_ID = "equipmentId";
    private static final String EQUIPMENT_CODE = "equipmentCode";
    private static final String EQUIPMENT_DESC = "equipmentDesc";
    private static final String MODEL_ID = "modelId";
    private static final String LOCATION_ID = "locationId";
    private static final String IP_ADDRESS_ID = "ipAddress";

    private String id;
    private String code;
    private String description;
    private String model;
    private String location;
    private String ipAddress;

    @WebDtoFieldGetter(fieldName = EQUIPMENT_ID)
    public String getId() {
        return id;
    }

    @UserCaption("ИД")
    @WebDtoFieldSetter(fieldName = EQUIPMENT_ID)
    public void setId(String id) {
        this.id = id;
    }

    @WebDtoFieldGetter(fieldName = EQUIPMENT_CODE)
    public String getCode() {
        return code;
    }

    @UserCaption("Код ЕО")
    @WebDtoFieldSetter(fieldName = EQUIPMENT_CODE, rules = {Validator.LANG_ENG}, mayBeEmpty = false)
    public void setCode(String code) {
        this.code = code;
    }

    @WebDtoFieldGetter(fieldName = EQUIPMENT_DESC)
    public String getDescription() {
        return description;
    }

    @UserCaption("Описание назначения")
    @WebDtoFieldSetter(fieldName = EQUIPMENT_DESC, maxLength = 250)
    public void setDescription(String description) {
        this.description = description;
    }

    @WebDtoFieldGetter(fieldName = MODEL_ID)
    public String getModel() {
        return model;
    }

    @UserCaption("Модель")
    @WebDtoFieldSetter(fieldName = MODEL_ID, mayBeEmpty = false)
    public void setModel(String model) {
        this.model = model;
    }

    @WebDtoFieldGetter(fieldName = LOCATION_ID)
    public String getLocation() {
        return location;
    }

    @UserCaption("Местоположение")
    @WebDtoFieldSetter(fieldName = LOCATION_ID, mayBeEmpty = false)
    public void setLocation(String location) {
        this.location = location;
    }

    @WebDtoFieldGetter(fieldName = IP_ADDRESS_ID)
    public String getIpAddress() {
        return ipAddress;
    }

    @UserCaption("IP-адрес")
    @WebDtoFieldSetter(fieldName = IP_ADDRESS_ID, mayBeEmpty = false, rules = {Validator.IP_ADDR})
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
