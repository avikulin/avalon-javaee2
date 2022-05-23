package DAL.Utils.Filter;

import DAL.Utils.Filter.Enums.PredicateType;
import DAL.Utils.Filter.Templates.Predicate;

public class LtePredicate<T> extends Predicate<T> {
    public LtePredicate(Class<?> entity, String field, T rValue) {
        super(entity, field, PredicateType.LTE, null, rValue);
    }

    @Override
    public String toString() {
        return String.format("(%s.%s <= %s)",
                this.getEntity().getSimpleName(),
                this.getField(),
                this.getRValue().toString());
    }
}
