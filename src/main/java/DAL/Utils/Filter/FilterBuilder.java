package DAL.Utils.Filter;

import Common.Exceptions.DataValidationException;
import DAL.Utils.Filter.Contracts.FilterExpression;

import java.util.List;
import java.util.Map;

public interface FilterBuilder {
    void setScalarCriteriaOrder(List<String> scalarFieldNames) throws DataValidationException;
    FilterExpression[] build(Map<String, String> refValues, String filterStr) throws DataValidationException;
}
