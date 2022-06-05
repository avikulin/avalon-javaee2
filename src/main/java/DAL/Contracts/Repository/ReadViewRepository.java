package DAL.Contracts.Repository;

import DAL.Utils.Filter.Contracts.FilterDef;
import DAL.Utils.Filter.Enums.PredicateType;

import java.util.List;

public interface ReadViewRepository<T> {
    List<FilterDef> getFilterDefs();
    List<T> getAll();
    void addFilter(String fieldName, PredicateType predicate, Object value);
    void addFilter(String fieldName, String pattern);
    void clearFilters();
    String getQuery();
}
