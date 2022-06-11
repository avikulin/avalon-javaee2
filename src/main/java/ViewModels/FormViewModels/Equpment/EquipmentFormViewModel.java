package ViewModels.FormViewModels.Equpment;

import ViewModels.Contracts.FormViewModel;
import ViewModels.FormViewModels.Equpment.DTO.EquipmentFormDTO;

import javax.ejb.Local;
import java.util.Map;

@Local
public interface EquipmentFormViewModel extends FormViewModel<EquipmentFormDTO, Long> {
    Map<String, String> getModelsList();
    Map<Long, String> getLocationsList();
}
