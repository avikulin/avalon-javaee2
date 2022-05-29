package DAL.Contracts.Repository;

import java.util.List;

public interface CrudRepository<T, K> {
    List<T> getAll();
    T getById(K id);
    void create(T value);
    T update (T value);
    void delete(K key);
    void flush();
}
