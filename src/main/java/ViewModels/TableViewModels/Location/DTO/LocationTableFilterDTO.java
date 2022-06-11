package ViewModels.TableViewModels.Location.DTO;

import Common.Annotations.UserCaption;
import Common.Exceptions.DataValidationException;
import Views.Annotations.WebDtoFieldGetter;
import Views.Annotations.WebDtoFieldSetter;
import Views.Annotations.WebFormModelDTO;
import Views.Templates.BaseWebFormDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebFormModelDTO
public class LocationTableFilterDTO extends BaseWebFormDTO {
    public static final String ORGANIZATION_ID = "organizationId";
    public static final String CITY_NAME = "cityName";
    public static final String FILTER_STRING = "searchString";

    String organizationId;
    String cityName;
    String filterString;

    public LocationTableFilterDTO() {
        super();
    }

    @WebDtoFieldGetter(fieldName = ORGANIZATION_ID)
    public String getOrganizationId() {
        return organizationId;
    }

    @UserCaption("Организация")
    @WebDtoFieldSetter(fieldName = ORGANIZATION_ID, rules = {})
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @WebDtoFieldGetter(fieldName = CITY_NAME)
    public String getCityName() {
        return cityName;
    }

    @UserCaption("Населенный пункт")
    @WebDtoFieldSetter(fieldName = CITY_NAME, rules = {})
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @WebDtoFieldGetter(fieldName = FILTER_STRING)
    public String getFilterString() {
        return filterString;
    }

    @UserCaption("Строка поиска")
    @WebDtoFieldSetter(fieldName = FILTER_STRING, rules = {})
    public void setFilterString(String filterString) {
        this.filterString = filterString;
    }

    @Override
    public void fillFrom(HttpServletRequest request) throws DataValidationException {
        super.fillFrom(request);
    }

    @Override
    public void transferTo(HttpServletRequest request) {
        super.transferTo(request);
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }

    @Override
    public List<String> getErrorsList() {
        return super.getErrorsList();
    }
}
