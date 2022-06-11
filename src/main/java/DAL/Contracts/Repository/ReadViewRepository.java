package DAL.Contracts.Repository;

import DAL.Utils.Filter.Contracts.FilterExpression;
import DAL.Utils.Filter.Contracts.FilterTokenDef;
import DAL.Utils.Filter.Enums.PredicateType;

import java.util.List;

public interface ReadViewRepository<T> {
    List<FilterTokenDef> getFilterDefs();
    List<T> getAll();
    void applyFilter(FilterExpression[] filterExpr);
    void addFilter(String fieldName, PredicateType predicate, Object value);
    void addFilter(String fieldName, String pattern);
    void useDistinct(boolean mode);
    void clearFilters();
    String getQuery();
}
