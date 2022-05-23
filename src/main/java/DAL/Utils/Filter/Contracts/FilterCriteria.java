package DAL.Utils.Filter.Contracts;

import DAL.Utils.Filter.Enums.CriteriaType;
import DAL.Utils.Filter.Enums.PredicateType;

public interface FilterCriteria {
    CriteriaType getType();
    Class<?> getEntity();
    String getField();
    PredicateType getPredicate();
    Object getLValue();
    Object getRValue();
}
