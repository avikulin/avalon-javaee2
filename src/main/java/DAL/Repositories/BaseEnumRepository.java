package DAL.Repositories;

import Common.Contracts.SelfDescriptable;
import DAL.Contracts.Repository.EnumRepository;

import java.util.*;
import java.util.stream.Collectors;

public class BaseEnumRepository<T extends Enum<T> & SelfDescriptable> implements EnumRepository<T> {
    private Class<T> clazz;

    public BaseEnumRepository(Class<T> clazz) {
        Objects.requireNonNull(clazz, "Enum object reference must be not null");
        this.clazz = clazz;
    }

    @Override
    public Map<String, String> getAll() {
        List<T> res = new ArrayList<>();
        T[] items = clazz.getEnumConstants();
        return Arrays
                .stream(items)
                .collect(
                        Collectors
                                .toMap(x->x.toString(), x-> x.getDescription())
                );
    }
}
