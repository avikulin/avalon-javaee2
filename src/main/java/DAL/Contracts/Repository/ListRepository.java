package DAL.Contracts.Repository;

import java.util.Map;

public interface ListRepository<T, K> {
    Map<K ,String> getItems();
}
