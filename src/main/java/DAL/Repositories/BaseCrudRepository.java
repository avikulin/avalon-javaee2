package DAL.Repositories;

import DAL.Contracts.Repository.CrudRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

public class BaseCrudRepository<T,K> implements CrudRepository<T,K> {
    private Class<T> clazz ;
    private EntityManager entityManager;
    public BaseCrudRepository(Class<T> entityClass, EntityManager entityManager) {
        this.clazz =entityClass;
        this.entityManager = entityManager;
    }

    @Override
    public List<T> getAll() {
        return entityManager
                .createQuery(String.format("select e0 from %s e0", this.clazz.getName()), this.clazz)
                .getResultList();
    }

    @Override
    public T getById(K key) {
        Objects.requireNonNull(key, "Key must be not null");
        return (T)entityManager.find(clazz, key);
    }

    @Override
    public void create(T value) {
        Objects.requireNonNull(value, "Entity value must be not null");
        entityManager.persist(value);
    }

    @Override
    public T update(T value) {
        Objects.requireNonNull(value, "Entity value must be not null");
        T res = entityManager.merge(value);
        return res;
    }

    @Override
    public void delete(K key) {
        Objects.requireNonNull(key, "Key must be not null");
        T entity = getById(key);
        entityManager.remove(entity);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }
}
