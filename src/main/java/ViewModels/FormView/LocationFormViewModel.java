package ViewModels.FormView;

import Common.Exceptions.DataValidationException;
import ViewModels.FormView.DTO.LocationReadDTO;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface LocationFormViewModel {
    Map<Long, String> getOrganizationsList();
    LocationReadDTO getData(Long id);
    void putData(LocationReadDTO item) throws DataValidationException;

}
