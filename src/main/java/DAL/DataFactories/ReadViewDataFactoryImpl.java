package DAL.DataFactories;

import DAL.Contracts.DataFactory.ReadViewDataFactory;
import DAL.Contracts.Repository.ReadViewRepository;
import DAL.Repositories.BaseReadViewRepository;
import ViewModels.TableViewModels.DTO.ModelTableRowDTO;
import ViewModels.TableViewModels.DTO.OrganizationTableRowDTO;
import ViewModels.TableViewModels.DTO.VendorTableRowDTO;
import ViewModels.TableViewModels.Equipment.DTO.EquipmentTableRowDTO;
import ViewModels.TableViewModels.Location.DTO.CitiesListItemDTO;
import ViewModels.TableViewModels.Location.DTO.LocationTableRowDTO;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class ReadViewDataFactoryImpl implements ReadViewDataFactory {
    private ReadViewRepository<VendorTableRowDTO> vendorsReadViewRepo;
    private ReadViewRepository<ModelTableRowDTO> modelsReadViewRepo;
    private ReadViewRepository<OrganizationTableRowDTO> organizationsReadViewRepo;
    private ReadViewRepository<LocationTableRowDTO> locationsReadViewRepo;
    private ReadViewRepository<CitiesListItemDTO> citiesReadViewRepo;
    private ReadViewRepository<EquipmentTableRowDTO> equipmentUnitsRepo;

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    private void init(){
        this.vendorsReadViewRepo = new BaseReadViewRepository<>(entityManager, VendorTableRowDTO.class);
        this.modelsReadViewRepo = new BaseReadViewRepository<>(entityManager, ModelTableRowDTO.class);
        this.organizationsReadViewRepo = new BaseReadViewRepository<>(entityManager, OrganizationTableRowDTO.class);
        this.locationsReadViewRepo = new BaseReadViewRepository<>(entityManager, LocationTableRowDTO.class);
        this.citiesReadViewRepo = new BaseReadViewRepository<>(entityManager, CitiesListItemDTO.class);
        this.equipmentUnitsRepo = new BaseReadViewRepository<>(entityManager, EquipmentTableRowDTO.class);
    }

    @Override
    public ReadViewRepository<VendorTableRowDTO> getVendorsReadViewRepo() {
        return this.vendorsReadViewRepo;
    }

    @Override
    public ReadViewRepository<ModelTableRowDTO> getModelsReadViewRepo() {
        return this.modelsReadViewRepo;
    }

    @Override
    public ReadViewRepository<OrganizationTableRowDTO> getOrganizationsReadViewRepo() {
        return this.organizationsReadViewRepo;
    }

    @Override
    public ReadViewRepository<LocationTableRowDTO> getLocationsReadViewRepo() {
        return this.locationsReadViewRepo;
    }

    @Override
    public ReadViewRepository<CitiesListItemDTO> getCitiesReadViewRepo() {
        return this.citiesReadViewRepo;
    }

    @Override
    public ReadViewRepository<EquipmentTableRowDTO> getEquipmentUnitsRepo() {
        return this.equipmentUnitsRepo;
    }
}
