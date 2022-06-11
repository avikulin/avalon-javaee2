package DAL.DataFactories;

import DAL.Contracts.DataFactory.ListDataFactory;
import DAL.Contracts.Repository.ListRepository;
import DAL.Contracts.Repository.ReadViewRepository;
import DAL.DataEntities.Registers.Location;
import DAL.Repositories.BaseListViewRepository;
import ViewModels.TableViewModels.DTO.*;
import ViewModels.TableViewModels.Location.DTO.CitiesListItemDTO;
import ViewModels.TableViewModels.Location.DTO.LocationTableRowDTO;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;

@Stateless
public class ListDataFactoryImpl implements ListDataFactory {

    @Override
    public ListRepository<OrganizationTableRowDTO, Long> getOrganizationsListRepo(
            ReadViewRepository<OrganizationTableRowDTO> repository) {
        return new BaseListViewRepository<>(repository,
                                            OrganizationTableRowDTO::getId,
                                            OrganizationTableRowDTO::getFullName);
    }

    @Override
    public ListRepository<LocationTableRowDTO, Long> getLocationsListRepo(
            ReadViewRepository<LocationTableRowDTO> repository) {
        return new BaseListViewRepository<LocationTableRowDTO, Long>(repository,
                LocationTableRowDTO::getId,
                Arrays.asList(
                        LocationTableRowDTO::getOrganizationName,
                        LocationTableRowDTO::getName,
                        LocationTableRowDTO::getLocCity
                ));
    }

    @Override
    public ListRepository<OrgTypeTableRowDTO, String> getOrgTypeListRepo(
            ReadViewRepository<OrgTypeTableRowDTO> repository) {
        return new BaseListViewRepository<>(repository,
                                            OrgTypeTableRowDTO::getType_id,
                                            OrgTypeTableRowDTO::getDescription);
    }

    @Override
    public ListRepository<CountryTableRowDTO, String> getCountryListRepo(
            ReadViewRepository<CountryTableRowDTO> repository) {
        return new BaseListViewRepository<>(repository,
                                            CountryTableRowDTO::getId,
                                            Arrays.asList(CountryTableRowDTO::getCode,
                                                          CountryTableRowDTO::getFullName,
                                                          CountryTableRowDTO::getRegion));
    }

    @Override
    public ListRepository<ModelTableRowDTO, String> getModelListRepo(ReadViewRepository<ModelTableRowDTO> repository) {
        return new BaseListViewRepository<>(repository,
                                            ModelTableRowDTO::getId,
                                            Arrays.asList(ModelTableRowDTO::getCode,
                                                          ModelTableRowDTO::getVendor,
                                                          e-> e.getLayer().getDescription()));
    }

    @Override
    public ListRepository<VendorTableRowDTO, Long> getVendorListRepo(ReadViewRepository<VendorTableRowDTO> repository) {
        return new BaseListViewRepository<>(repository,
                                            VendorTableRowDTO::getId,
                                            Arrays.asList(
                                                            VendorTableRowDTO::getName,
                                                            VendorTableRowDTO::getCountryOfOrigin));
    }

    @Override
    public ListRepository<SexTableRowDTO, String> getSexListRepo(ReadViewRepository<SexTableRowDTO> repository) {
        return new BaseListViewRepository<>(repository,
                                            SexTableRowDTO::getId,
                                            SexTableRowDTO::getName);
    }

    @Override
    public ListRepository<CitiesListItemDTO, String> getCityListRepo(ReadViewRepository<CitiesListItemDTO> repository) {
        return new BaseListViewRepository<CitiesListItemDTO, String>(repository,
                                                                     CitiesListItemDTO::getLocCity,
                                                                     CitiesListItemDTO::getLocCity);
    }
}
