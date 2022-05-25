package DAL.Contracts.Repository;

public interface CrudRepository<T, K> {
    T getById(K id);
    void create(T value);
    T update (T value);
    void delete(K key);
    void flush();
}
