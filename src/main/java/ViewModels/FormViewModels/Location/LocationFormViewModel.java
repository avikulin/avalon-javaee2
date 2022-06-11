package ViewModels.FormViewModels.Location;

import ViewModels.Contracts.FormViewModel;
import ViewModels.FormViewModels.Location.DTO.LocationFormDTO;

import javax.ejb.Local;
import java.util.Map;

@Local
public interface LocationFormViewModel extends FormViewModel<LocationFormDTO, Long> {
    Map<Long, String> getOrganizationsList();
}
