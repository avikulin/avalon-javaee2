package ViewModels.DTO;

import DAL.Annotations.SourceEntity;
import DAL.Annotations.SourceField;
import DAL.Annotations.UserCaption;
import DAL.Annotations.Filterable;

@SourceEntity(entityName = "Model")
public class ModelTableRow {
    @Filterable()
    @SourceField(fieldSource = "Model", fieldName = "modelCode", fieldProjectionPath = "modelCode")
    @UserCaption(caption = "Код")
    private String code;

    @Filterable()
    @UserCaption(caption = "Описание")
    @SourceField(fieldSource = "Model", fieldName = "modelDescription", fieldProjectionPath = "modelDescription")
    private String description;

    @Filterable()
    @UserCaption(caption = "Производитель")
    @SourceField(fieldSource = "Vendor", fieldName = "name", fieldProjectionPath = "vendor.name")
    private String vendor;

    @Filterable()
    @UserCaption(caption = "Страна")
    @SourceField(fieldSource = "Country", fieldName = "shortName", fieldProjectionPath = "vendor.countryOfOrigin.shortName")
    private String country;

    @Filterable()
    @UserCaption(caption = "Уровень OSI")
    @SourceField(fieldSource = "OsiLayer", fieldName = "layerNum", fieldProjectionPath = "layerNum")
    private String layer;

    public ModelTableRow(String code, String description, String vendor, String country, String layer) {
        this.code = code;
        this.description = description;
        this.vendor = vendor;
        this.country = country;
        this.layer = layer;
    }
}
