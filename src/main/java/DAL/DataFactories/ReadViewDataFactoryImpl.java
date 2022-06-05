package DAL.DataFactories;

import DAL.Contracts.DataFactory.ReadViewDataFactory;
import DAL.Contracts.Repository.ReadViewRepository;
import DAL.Repositories.BaseReadViewRepository;
import ViewModels.TableViews.DTO.ModelTableRowDTO;
import ViewModels.TableViews.DTO.OrganizationTableRowDTO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ReadViewDataFactoryImpl implements ReadViewDataFactory {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ReadViewRepository<ModelTableRowDTO> getModelReadViewRepo() {
        return new BaseReadViewRepository<>(entityManager, ModelTableRowDTO.class);
    }

    @Override
    public ReadViewRepository<OrganizationTableRowDTO> getOrgReadViewRepo() {
        return new BaseReadViewRepository<>(entityManager, OrganizationTableRowDTO.class);
    }


}
