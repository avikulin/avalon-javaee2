package DAL.Repositories;

import DAL.Contracts.Repository.EnumRepository;
import Common.Contracts.SelfDescriptable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BaseEnumRepository<T extends Enum<T> & SelfDescriptable> implements EnumRepository<T> {
    private Class<T> clazz;

    public BaseEnumRepository(Class<T> clazz) {
        Objects.requireNonNull(clazz, "Enum object reference must be not null");
        this.clazz = clazz;
    }

    @Override
    public List<String> getAll() {
        List<T> res = new ArrayList<>();
        T[] items = clazz.getEnumConstants();
        return Arrays.stream(items).map(x-> x.getDescription()).collect(Collectors.toList());
    }
}
