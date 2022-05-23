package DAL.Utils.Filter;

import DAL.Utils.Filter.Enums.PredicateType;
import DAL.Utils.Filter.Templates.Predicate;

public class BtwPredicate<T> extends Predicate<T> {
    public BtwPredicate(Class<?> entity, String field, T lValue, T rValue) {
        super(entity, field, PredicateType.BTW, lValue, rValue);
    }

    @Override
    public String toString() {
        return String.format("(%s) AND (%s))",
                new GtePredicate<T>(this.getEntity(), this.getField(), this.getRValue()),
                new LtePredicate<T>(this.getEntity(), this.getField(), this.getRValue()));
    }
}
