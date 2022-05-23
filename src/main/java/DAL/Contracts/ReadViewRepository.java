package DAL.Contracts;

import DAL.Utils.Filter.Contracts.FilterDef;
import DAL.Utils.Filter.Contracts.FilterExpression;

import java.util.List;

public interface ReadViewRepository<T> {
    List<FilterDef> getFilterDefs();
    List<T> getAll(FilterExpression filterExpression);
}
