package ViewModels.TableViewModels.Location;

import ViewModels.Contracts.TableViewModel;
import ViewModels.TableViewModels.Location.DTO.LocationTableRowDTO;

import javax.ejb.Local;
import java.util.Map;

@Local
public interface LocationTableViewModel extends TableViewModel<LocationTableRowDTO> {
    Map<Long, String> getOrganizationsList();
    Map<String, String> getCitiesList();
}
