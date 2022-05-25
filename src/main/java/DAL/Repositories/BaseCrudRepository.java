package DAL.Repositories;

import DAL.Contracts.Repository.CrudRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.Objects;

public class BaseCrudRepository<T,K> implements CrudRepository<T,K> {
    private EntityManager entityManager;
    private final EntityTransaction tx;
    private Class<T> clazz ;


    public BaseCrudRepository(Class<T> entityClass, EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
        this.clazz =entityClass;
        this.tx = entityManager.getTransaction();
    }

    @Override
    public T getById(K key) {
        Objects.requireNonNull(key, "Key must be not null");
        return (T)entityManager.find(clazz, key);
    }

    @Override
    public void create(T value) {
        Objects.requireNonNull(value, "Entity value must be not null");
        tx.begin();
        entityManager.persist(value);
        tx.commit();
    }

    @Override
    public T update(T value) {
        Objects.requireNonNull(value, "Entity value must be not null");
        tx.begin();
        T res = entityManager.merge(value);
        tx.commit();
        return res;
    }

    @Override
    public void delete(K key) {
        Objects.requireNonNull(key, "Key must be not null");
        tx.begin();
        T entity = getById(key);
        entityManager.remove(entity);
        tx.commit();
    }

    @Override
    public void flush() {
        entityManager.flush();
    }
}
