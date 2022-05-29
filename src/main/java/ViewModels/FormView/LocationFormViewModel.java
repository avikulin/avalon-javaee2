package ViewModels.FormView;

import ViewModels.FormView.DTO.LocationReadDTO;

import javax.ejb.Local;
import java.util.Map;

@Local
public interface LocationFormViewModel {
    Map<Long, String> getOrganizationsList();
    LocationReadDTO getData(Long id);
    void putData(LocationReadDTO data);
}
