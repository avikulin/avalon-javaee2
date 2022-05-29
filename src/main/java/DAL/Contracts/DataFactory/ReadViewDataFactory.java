package DAL.Contracts.DataFactory;

import DAL.Contracts.Repository.ReadViewRepository;

import javax.ejb.Local;

@Local
public interface ReadViewDataFactory<T> {
    ReadViewRepository<T> getReadViewRepo(Class<T> clazz);
}
