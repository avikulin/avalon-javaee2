package DAL.DataFactories;

import DAL.Contracts.DataFactory.ReadViewDataFactory;
import DAL.Contracts.Repository.ReadViewRepository;
import DAL.Repositories.BaseReadViewRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ReadViewDataFactoryImpl<T> implements ReadViewDataFactory<T> {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public ReadViewRepository<T> getReadViewRepo(Class<T> clazz) {
        return new BaseReadViewRepository<>(entityManager, clazz);
    }
}
