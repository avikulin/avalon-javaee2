package ViewModels.TableViewModels.DTO;

import Common.Annotations.UserCaption;
import DAL.Annotations.*;
import DAL.DataEntities.Enums.OsiLayer;

@SourceEntity(entityName = "Model")
public class ModelTableRowDTO {
    @SourceField(fieldSource = "Model", fieldName = "id")
    @UserCaption(value = "Код")
    @CtorParam(position = 1)
    private final String id;

    @Filterable()
    @SourceField(fieldSource = "Model", fieldName = "modelCode")
    @UserCaption(value = "Код")
    @CtorParam(position = 2)
    private final String code;

    @UserCaption(value = "Описание")
    @SourceField(fieldSource = "Model", fieldName = "modelDescription")
    @CtorParam(position = 3)
    private final String description;

    @Filterable()
    @UserCaption(value = "Производитель")
    @SourceField(fieldSource = "Vendor", fieldName = "name", fieldProjectionPath = "vendor.name")
    @CtorParam(position = 4)
    private final String vendor;

    @Filterable()
    @UserCaption(value = "Страна")
    @SourceField(fieldSource = "Country", fieldName = "shortName", fieldProjectionPath = "vendor.countryOfOrigin.shortName")
    @CtorParam(position = 5)
    private final String country;

    @UserCaption(value = "Уровень OSI")
    @SourceField(fieldSource = "Model", fieldName = "layerNum")
    @CtorParam(position = 6)
    private final OsiLayer layer;

    @Filterable
    @UserCaption(value = "Порты 100Мб/с")
    @SourceField(fieldSource = "Model", fieldName = "num100MbpsPorts")
    @CtorParam(position = 7)
    private final int num100MbpsPorts;

    @Filterable
    @UserCaption(value = "Порты 10Гб/с")
    @SourceField(fieldSource = "Model", fieldName = "num10GbpsPorts")
    @CtorParam(position = 8)
    private final int num10GbpsPorts;

    @Filterable
    @UserCaption(value = "Порты PoE")
    @SourceField(fieldSource = "Model", fieldName = "numPoEPorts")
    @CtorParam(position = 9)
    private final int numPoEPorts;

    public ModelTableRowDTO(String id, String code, String description, String vendor, String country, OsiLayer layer, int num100MbpsPorts, int num10GbpsPorts, int numPoEPorts) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.vendor = vendor;
        this.country = country;
        this.layer = layer;
        this.num100MbpsPorts = num100MbpsPorts;
        this.num10GbpsPorts = num10GbpsPorts;
        this.numPoEPorts = numPoEPorts;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public String getCountry() {
        return country;
    }

    public OsiLayer getLayer() {
        return layer;
    }

    public Integer getNum100MbpsPorts() {
        return num100MbpsPorts;
    }

    public Integer getNum10GbpsPorts() {
        return num10GbpsPorts;
    }

    public Integer getNumPoEPorts() {
        return numPoEPorts;
    }

    @Override
    public String toString() {
        return "ModelTableRowDTO{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", country='" + country + '\'' +
                ", layer=" + layer +
                ", num100MbpsPorts=" + num100MbpsPorts +
                ", num10GbpsPorts=" + num10GbpsPorts +
                ", numPoEPorts=" + numPoEPorts +
                '}';
    }
}
