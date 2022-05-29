package DAL.Contracts.DataFactory;

import DAL.Contracts.Repository.ListRepository;
import DAL.Contracts.Repository.ReadViewRepository;
import ViewModels.TableViews.DTO.*;

import javax.ejb.Local;

@Local
public interface ListDataFactory {
    ListRepository<OrganizationTableRowDTO, Long> getOrganizationsListRepo(ReadViewRepository<OrganizationTableRowDTO> repository);

    ListRepository<OrgTypeTableRowDTO, String> getOrgTypeListRepo(ReadViewRepository<OrgTypeTableRowDTO> repository);

    ListRepository<CountryTableRowDTO, String> getCountryListRepo(ReadViewRepository<CountryTableRowDTO> repository);

    ListRepository<ModelTableRowDTO, String> getModelListRepo(ReadViewRepository<ModelTableRowDTO> repository);

    ListRepository<VendorTableRowDTO, Long> getVendorListRepo(ReadViewRepository<VendorTableRowDTO> repository);

    ListRepository<SexTableRowDTO, String> getSexListRepo(ReadViewRepository<SexTableRowDTO> repository);
}
