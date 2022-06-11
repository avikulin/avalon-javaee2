package ViewModels.TableViewModels.Equipment;

import DAL.Contracts.DataFactory.EnumDataFactory;
import DAL.Contracts.DataFactory.ListDataFactory;
import DAL.Contracts.DataFactory.ReadViewDataFactory;
import DAL.Contracts.Repository.EnumRepository;
import DAL.Contracts.Repository.ListRepository;
import DAL.Contracts.Repository.ReadViewRepository;
import DAL.DataEntities.Enums.OsiLayer;
import DAL.Utils.Filter.Contracts.FilterExpression;
import DAL.Utils.Filter.Contracts.FilterTokenDef;
import ViewModels.TableViewModels.DTO.ModelTableRowDTO;
import ViewModels.TableViewModels.DTO.OrganizationTableRowDTO;
import ViewModels.TableViewModels.DTO.VendorTableRowDTO;
import ViewModels.TableViewModels.Equipment.DTO.EquipmentTableRowDTO;
import ViewModels.TableViewModels.Location.DTO.LocationTableRowDTO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;

@Stateless
public class EquipmentTableViewModelImpl implements EquipmentTableViewModel{
    @EJB
    ListDataFactory listDataFactory;

    @EJB
    ReadViewDataFactory readViewDataFactory;

    @EJB
    EnumDataFactory enumDataFactory;

    private ListRepository<VendorTableRowDTO, Long> vendorsListRepo;
    private ListRepository<ModelTableRowDTO, String> modelsListRepo;
    private ListRepository<OrganizationTableRowDTO, Long> organizationsListRepo;
    private ListRepository<LocationTableRowDTO, Long> locationsListRepo;
    private EnumRepository<OsiLayer> osiLayerEnumRepo;

    private ReadViewRepository<EquipmentTableRowDTO> equipmentUnitsReadViewRepo;

    @PostConstruct
    private void init(){
        ReadViewRepository<VendorTableRowDTO> vendorsReadViewRepo = readViewDataFactory.getVendorsReadViewRepo();
        this.vendorsListRepo = listDataFactory.getVendorListRepo(vendorsReadViewRepo);
        ReadViewRepository<ModelTableRowDTO> modelsReadViewRepo = readViewDataFactory.getModelsReadViewRepo();
        this.modelsListRepo = listDataFactory.getModelListRepo(modelsReadViewRepo);
        ReadViewRepository<OrganizationTableRowDTO> organizationsReadViewRepo = readViewDataFactory.getOrganizationsReadViewRepo();
        this.organizationsListRepo = listDataFactory.getOrganizationsListRepo(organizationsReadViewRepo);
        ReadViewRepository<LocationTableRowDTO> locationsReadViewRepo = readViewDataFactory.getLocationsReadViewRepo();
        this.locationsListRepo = listDataFactory.getLocationsListRepo(locationsReadViewRepo);
        this.osiLayerEnumRepo = enumDataFactory.getOsiLayerRepo();
        this.equipmentUnitsReadViewRepo = readViewDataFactory.getEquipmentUnitsRepo();
    }

    @Override
    public Map<String, String> getModelsList() {
        return this.modelsListRepo.getItems();
    }

    @Override
    public Map<Long, String> getVendorsList() {
        return this.vendorsListRepo.getItems();
    }

    @Override
    public Map<Long, String> getOrganizationsList() {
        return this.organizationsListRepo.getItems();
    }

    @Override
    public Map<Long, String> getLocationsList() {
        return this.locationsListRepo.getItems();
    }

    @Override
    public Map<String, String> getOsiLayers() {
        return this.osiLayerEnumRepo.getAll();
    }

    @Override
    public List<FilterTokenDef> getFilterDef() {
        return this.equipmentUnitsReadViewRepo.getFilterDefs();
    }

    @Override
    public List<EquipmentTableRowDTO> getData(FilterExpression[] filterExpr) {
        this.equipmentUnitsReadViewRepo.applyFilter(filterExpr);
        return this.equipmentUnitsReadViewRepo.getAll();
    }
}
