package ViewModels.FormView;

import DAL.Contracts.DataFactory.CrudDataFactory;
import DAL.Contracts.Repository.CrudRepository;
import DAL.DataEntities.Registers.Location;
import DAL.DataEntities.Registers.Organization;
import ViewModels.FormView.DTO.LocationReadDTO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class LocationFormViewModelImpl implements LocationFormViewModel {
    @EJB
    CrudDataFactory dataFactory;

    private CrudRepository<Location, Long> locRepo;
    private CrudRepository<Organization, Long> orgRepo;

    @PostConstruct
    private void init(){
        locRepo = dataFactory.getLocationRepo();
        orgRepo = dataFactory.getOrganizationRepo();
    }

    @Override
    public Map<Long, String> getOrganizationsList() {
        orgRepo = dataFactory.getOrganizationRepo();
        return orgRepo
                .getAll()
                .stream()
                .collect(Collectors.toMap(Organization::getId, Organization::getName));
    }

    @Override
    public LocationReadDTO getData(Long id) {
        Location loc = locRepo.getById(id);
        return new LocationReadDTO(id,
                                   loc.getLocName(),
                                   loc.getOrganization().getId().toString(),
                                   loc.getLocationCity(),
                                   loc.getLocationStreet(),
                                   Integer.toString(loc.getHouseNumber()),
                                   Integer.toString(loc.getBuilding()),
                                   Integer.toString(loc.getApartmentNumber()),
                                   loc.getInfo());
    }

    @Override
    public void putData(LocationReadDTO data) {

    }
}
