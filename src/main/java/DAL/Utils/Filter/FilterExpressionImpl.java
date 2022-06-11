package DAL.Utils.Filter;

import DAL.Utils.Filter.Contracts.FilterExpression;
import DAL.Utils.Filter.Contracts.FilterTokenDef;
import DAL.Utils.Filter.Enums.PredicateType;

public class FilterExpressionImpl implements FilterExpression {
    private FilterTokenDef field;
    private PredicateType predicate;
    private Object value;

    public FilterExpressionImpl(FilterTokenDef field, PredicateType predicate, Object value) {
        this.field = field;
        this.predicate = predicate;
        this.value = value;
    }

    @Override
    public FilterTokenDef getFilterObjectDef() {
        return this.field;
    }

    @Override
    public PredicateType getPredicate() {
        return this.predicate;
    }

    @Override
    public Object getFilterValue() {
        return this.value;
    }
}
