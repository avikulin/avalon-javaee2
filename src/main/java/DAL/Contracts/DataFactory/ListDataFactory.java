package DAL.Contracts.DataFactory;

import DAL.Contracts.Repository.ListRepository;
import DAL.Contracts.Repository.ReadViewRepository;
import ViewModels.TableViewModels.DTO.*;
import ViewModels.TableViewModels.Location.DTO.CitiesListItemDTO;
import ViewModels.TableViewModels.Location.DTO.LocationTableRowDTO;

import javax.ejb.Local;

@Local
public interface ListDataFactory {
    ListRepository<OrganizationTableRowDTO, Long> getOrganizationsListRepo(ReadViewRepository<OrganizationTableRowDTO> repository);

    ListRepository<LocationTableRowDTO, Long> getLocationsListRepo(ReadViewRepository<LocationTableRowDTO> repository);

    ListRepository<OrgTypeTableRowDTO, String> getOrgTypeListRepo(ReadViewRepository<OrgTypeTableRowDTO> repository);

    ListRepository<CountryTableRowDTO, String> getCountryListRepo(ReadViewRepository<CountryTableRowDTO> repository);

    ListRepository<ModelTableRowDTO, String> getModelListRepo(ReadViewRepository<ModelTableRowDTO> repository);

    ListRepository<VendorTableRowDTO, Long> getVendorListRepo(ReadViewRepository<VendorTableRowDTO> repository);

    ListRepository<SexTableRowDTO, String> getSexListRepo(ReadViewRepository<SexTableRowDTO> repository);

    ListRepository<CitiesListItemDTO, String> getCityListRepo(ReadViewRepository<CitiesListItemDTO> repository);
}
