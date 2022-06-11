package ViewModels.TableViewModels.Location.DTO;

import Common.Annotations.UserCaption;
import DAL.Annotations.CtorParam;
import DAL.Annotations.SourceEntity;
import DAL.Annotations.SourceField;

@SourceEntity(entityName = "Location")
public class CitiesListItemDTO {
    @UserCaption(value = "Город")
    @SourceField(fieldSource = "Location", fieldName = "locCity")
    @CtorParam(position = 1)
    private final String locCity;

    public CitiesListItemDTO(String locCity) {
        this.locCity = locCity;
    }

    public String getLocCity() {
        return locCity;
    }
}
