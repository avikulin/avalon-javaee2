package ViewModels.FormView;

import ViewModels.FormView.DTO.LocationReadDTO;

import java.util.Map;

public class TestBean implements LocationFormViewModel{
    @Override
    public Map<Long, String> getOrganizationsList() {
        return null;
    }

    @Override
    public LocationReadDTO getData(Long id) {
        return null;
    }

    @Override
    public void putData(LocationReadDTO data) {

    }
}
