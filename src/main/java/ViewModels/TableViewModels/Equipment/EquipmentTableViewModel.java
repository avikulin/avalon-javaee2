package ViewModels.TableViewModels.Equipment;

import ViewModels.Contracts.TableViewModel;
import ViewModels.TableViewModels.Equipment.DTO.EquipmentTableRowDTO;

import javax.ejb.Local;
import java.util.Map;

@Local
public interface EquipmentTableViewModel extends TableViewModel<EquipmentTableRowDTO> {
    Map<String, String> getModelsList();
    Map<Long, String> getVendorsList();
    Map<Long, String> getOrganizationsList();
    Map<Long, String> getLocationsList();
    Map<String, String> getOsiLayers();
}
