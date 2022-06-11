package ViewModels.TableViewModels.Location;

import DAL.Contracts.DataFactory.ListDataFactory;
import DAL.Contracts.DataFactory.ReadViewDataFactory;
import DAL.Contracts.Repository.ListRepository;
import DAL.Contracts.Repository.ReadViewRepository;
import DAL.Utils.Filter.Contracts.FilterExpression;
import DAL.Utils.Filter.Contracts.FilterTokenDef;
import ViewModels.TableViewModels.DTO.OrganizationTableRowDTO;
import ViewModels.TableViewModels.Location.DTO.CitiesListItemDTO;
import ViewModels.TableViewModels.Location.DTO.LocationTableRowDTO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;

@Stateless
public class LocationTableViewModelImpl implements LocationTableViewModel {
    @EJB
    ListDataFactory listDataFactory;

    @EJB
    private ReadViewDataFactory readViewDataFactory;

    private ListRepository<OrganizationTableRowDTO, Long> orgListRepo;
    private ListRepository<CitiesListItemDTO, String> cityListRepo;
    private ReadViewRepository<LocationTableRowDTO> locTableRepo;

    @PostConstruct
    private void init(){
        this.orgListRepo = listDataFactory.getOrganizationsListRepo(readViewDataFactory.getOrganizationsReadViewRepo());
        this.cityListRepo = listDataFactory.getCityListRepo(readViewDataFactory.getCitiesReadViewRepo());
        this.locTableRepo = readViewDataFactory.getLocationsReadViewRepo();
    }

    @Override
    public List<FilterTokenDef> getFilterDef() {
        return this.locTableRepo.getFilterDefs();
    }

    @Override
    public List<LocationTableRowDTO> getData(FilterExpression[] filterExpr) {
        locTableRepo.applyFilter(filterExpr);
        return locTableRepo.getAll();
    }

    @Override
    public Map<Long, String> getOrganizationsList() {
        return orgListRepo.getItems();
    }

    @Override
    public Map<String, String> getCitiesList() {
        return cityListRepo.getItems();
    }
}
