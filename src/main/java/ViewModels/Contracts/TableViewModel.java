package ViewModels.Contracts;

import DAL.Utils.Filter.Contracts.FilterExpression;
import DAL.Utils.Filter.Contracts.FilterTokenDef;

import java.util.List;

public interface TableViewModel<T> {
    List<FilterTokenDef> getFilterDef();
    List<T> getData(FilterExpression[] filterExpr);
}
