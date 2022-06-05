package DAL.Repositories;

import DAL.Contracts.Repository.ListRepository;
import DAL.Contracts.Repository.ReadViewRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BaseListViewRepository<T,K> implements ListRepository<T,K> {
    private static final String DEFAULT_DELIMITER = " | ";

    private final ReadViewRepository<T> readViewRepository;
    private final Function<T, K> keyMapFunc;
    private final List<Function<T, String>> valueMapFunc;

    public BaseListViewRepository(ReadViewRepository<T> readViewRepository,
                                  Function<T, K> keyMapFunc,
                                  List<Function<T, String>> valueMapFunc)  {
        Objects.requireNonNull(readViewRepository, "Base read-view repository must be not null");
        Objects.requireNonNull(keyMapFunc, "Functor for extracting keys must be set");
        Objects.requireNonNull(valueMapFunc, "Functor for extracting values must be set");
        this.readViewRepository = readViewRepository;
        this.keyMapFunc = keyMapFunc;
        this.valueMapFunc = valueMapFunc;
    }

    public BaseListViewRepository(ReadViewRepository<T> readViewRepository,
                                  Function<T, K> keyMapFunc,
                                  Function<T, String> valueMapFunc) {
        this(readViewRepository, keyMapFunc, Collections.singletonList(valueMapFunc));
    }

    @Override
    public Map<K, String> getItems() {
        List<T> dataItems = this.readViewRepository.getAll();
        return dataItems
                .stream()
                .collect(Collectors.toMap(keyMapFunc, v->{
                                                            StringJoiner res = new StringJoiner(DEFAULT_DELIMITER);
                                                            for (Function<T, String> func : this.valueMapFunc){
                                                                res.add(func.apply(v));
                                                            }
                                                            return res.toString();
                                                         }
                                         )
                );
    }
}
