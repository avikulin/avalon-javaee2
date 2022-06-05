package DAL.Contracts.DataFactory;

import DAL.Contracts.Repository.ReadViewRepository;
import ViewModels.TableViews.DTO.ModelTableRowDTO;
import ViewModels.TableViews.DTO.OrganizationTableRowDTO;

import javax.ejb.Local;

@Local
public interface ReadViewDataFactory {
    ReadViewRepository<ModelTableRowDTO> getModelReadViewRepo();
    ReadViewRepository<OrganizationTableRowDTO> getOrgReadViewRepo();
}
