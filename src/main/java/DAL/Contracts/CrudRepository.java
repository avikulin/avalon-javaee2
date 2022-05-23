package DAL.Contracts;

import javax.ejb.Local;

@Local
public interface CrudRepository<T, K> {
    T getById(K id);
    void create(T value);
    T update (T value);
    void delete(K key);
    void flush();
}
