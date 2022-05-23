package DAL.Templates;

import DAL.Contracts.EnumRepository;
import Common.Contracts.SelfDescriptable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BaseEnumRepository<T extends Enum<T> & SelfDescriptable> implements EnumRepository<T> {
    private Class<T> t;
    @Override
    public List<String> getAll() {
        List<T> res = new ArrayList<>();
        T[] items = t.getEnumConstants();
        return Arrays.stream(items).map(SelfDescriptable::getDescription).collect(Collectors.toList());
    }
}
