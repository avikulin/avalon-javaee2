package DAL.Utils.Filter.Contracts;

import DAL.Utils.Filter.Enums.PredicateType;

public interface FilterExpression {
    FilterTokenDef getFilterObjectDef();
    PredicateType getPredicate();
    Object getFilterValue();
}
