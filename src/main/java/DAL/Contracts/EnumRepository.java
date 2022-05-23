package DAL.Contracts;

import java.util.List;

public interface EnumRepository<T extends Enum<T>> {
    List<String> getAll();
}
