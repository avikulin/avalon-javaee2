package DAL.Contracts.Repository;

import java.util.Map;

public interface EnumRepository<T extends Enum<T>> {
    Map<String, String> getAll();
}
