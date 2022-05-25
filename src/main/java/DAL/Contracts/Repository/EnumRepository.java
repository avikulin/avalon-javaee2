package DAL.Contracts.Repository;

import java.util.List;

public interface EnumRepository<T extends Enum<T>> {
    List<String> getAll();
}
