package DAL.DataFactories;

import DAL.Contracts.DataFactory.ListDataFactory;
import DAL.Contracts.Repository.ListRepository;
import DAL.Contracts.Repository.ReadViewRepository;
import DAL.Repositories.BaseListViewRepository;
import ViewModels.TableViews.DTO.*;

import javax.ejb.Stateless;
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
                                            ModelTableRowDTO::getCode,
                                            Arrays.asList(ModelTableRowDTO::getCode,
                                                          ModelTableRowDTO::getLayer,
                                                          ModelTableRowDTO::getVendor));
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
}
