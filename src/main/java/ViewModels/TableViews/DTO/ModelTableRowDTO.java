package ViewModels.TableViews.DTO;

import DAL.Annotations.*;

@SourceEntity(entityName = "Model")
public class ModelTableRowDTO {
    @Filterable()
    @SourceField(fieldSource = "Model", fieldName = "modelCode")
    @UserCaption(caption = "Код")
    @CtorParam(position = 1)
    private final String code;

    @UserCaption(caption = "Описание")
    @SourceField(fieldSource = "Model", fieldName = "modelDescription")
    @CtorParam(position = 2)
    private final String description;

    @Filterable()
    @UserCaption(caption = "Производитель")
    @SourceField(fieldSource = "Vendor", fieldName = "name", fieldProjectionPath = "vendor.name")
    @CtorParam(position = 3)
    private final String vendor;

    @Filterable()
    @UserCaption(caption = "Страна")
    @SourceField(fieldSource = "Country", fieldName = "shortName", fieldProjectionPath = "vendor.countryOfOrigin.shortName")
    @CtorParam(position = 4)
    private final String country;

    @UserCaption(caption = "Уровень OSI")
    @SourceField(fieldSource = "Model", fieldName = "layerNum")
    @CtorParam(position = 5)
    private final String layer;

    @Filterable
    @UserCaption(caption = "Порты 100Мб/с")
    @SourceField(fieldSource = "Model", fieldName = "num100MbpsPorts")
    @CtorParam(position = 6)
    private final int num100MbpsPorts;

    @Filterable
    @UserCaption(caption = "Порты 10Гб/с")
    @SourceField(fieldSource = "Model", fieldName = "num10GbpsPorts")
    @CtorParam(position = 7)
    private final int num10GbpsPorts;

    @Filterable
    @UserCaption(caption = "Порты PoE")
    @SourceField(fieldSource = "Model", fieldName = "numPoEPorts")
    @CtorParam(position = 8)
    private final int numPoEPorts;

    public ModelTableRowDTO(String code, String description, String vendor, String country,
                            String layer, int num100MbpsPorts, int num10GbpsPorts, int numPoEPorts) {
        this.code = code;
        this.description = description;
        this.vendor = vendor;
        this.country = country;
        this.layer = layer;
        this.num100MbpsPorts = num100MbpsPorts;
        this.num10GbpsPorts = num10GbpsPorts;
        this.numPoEPorts = numPoEPorts;
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

    public String getLayer() {
        return layer;
    }

    public int getNum100MbpsPorts() {
        return num100MbpsPorts;
    }

    public int getNum10GbpsPorts() {
        return num10GbpsPorts;
    }

    public int getNumPoEPorts() {
        return numPoEPorts;
    }

    @Override
    public String toString() {
        return "ModelTableRowDTO{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", country='" + country + '\'' +
                ", layer='" + layer + '\'' +
                ", num100MbpsPorts=" + num100MbpsPorts +
                ", num10GbpsPorts=" + num10GbpsPorts +
                ", numPoEPorts=" + numPoEPorts +
                '}';
    }
}
