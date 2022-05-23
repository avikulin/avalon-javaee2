package DAL.Utils.Filter.Templates;

import DAL.Utils.Filter.Contracts.FilterCriteria;
import DAL.Utils.Filter.Enums.CriteriaType;
import DAL.Utils.Filter.Enums.PredicateType;

public abstract class Predicate<T> implements FilterCriteria {
    private T type;
    private final PredicateType predicate;
    private final Class<?> entity;
    private final String field;
    private final T lValue;
    private final T rValue;

    public Predicate(Class<?> entity, String field, PredicateType predicate, T lValue, T rValue) {
        if (predicate == null){
            throw new IllegalArgumentException("Predicate type must be not null");
        }

        this.predicate = predicate;
        this.entity = entity;
        this.field = field;
        this.lValue = lValue;
        this.rValue = rValue;
    }

    @Override
    public CriteriaType getType() {
        return CriteriaType.getByType(type.getClass());
    }

    @Override
    public Class<?> getEntity() {
        return null;
    }

    @Override
    public String getField() {
        return null;
    }

    @Override
    public PredicateType getPredicate() {
        return predicate;
    }

    @Override
    public T getLValue() {
        return lValue;
    }

    @Override
    public T getRValue() {
        return rValue;
    }
}
