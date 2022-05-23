package DAL.Templates;

import DAL.Contracts.CrudRepository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.Objects;

@Stateless
public class BaseCrudRepository<T,K> implements CrudRepository<T,K> {
    @PersistenceContext(name = "lab1", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    private Class<T> clazz ;

    public BaseCrudRepository(){};

    public BaseCrudRepository(Class<T> entityClass) {
        super();
        this.clazz =entityClass;
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
        return entityManager.merge(value);
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
