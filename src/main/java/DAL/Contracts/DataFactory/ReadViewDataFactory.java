package DAL.Contracts.DataFactory;

import DAL.Contracts.Repository.ReadViewRepository;
import ViewModels.TableViewModels.DTO.ModelTableRowDTO;
import ViewModels.TableViewModels.DTO.OrganizationTableRowDTO;
import ViewModels.TableViewModels.DTO.VendorTableRowDTO;
import ViewModels.TableViewModels.Equipment.DTO.EquipmentTableRowDTO;
import ViewModels.TableViewModels.Location.DTO.CitiesListItemDTO;
import ViewModels.TableViewModels.Location.DTO.LocationTableRowDTO;

import javax.ejb.Local;

@Local
public interface ReadViewDataFactory {
    ReadViewRepository<VendorTableRowDTO> getVendorsReadViewRepo();
    ReadViewRepository<ModelTableRowDTO> getModelsReadViewRepo();
    ReadViewRepository<OrganizationTableRowDTO> getOrganizationsReadViewRepo();
    ReadViewRepository<LocationTableRowDTO> getLocationsReadViewRepo();
    ReadViewRepository<CitiesListItemDTO> getCitiesReadViewRepo();
    ReadViewRepository<EquipmentTableRowDTO> getEquipmentUnitsRepo();
}
