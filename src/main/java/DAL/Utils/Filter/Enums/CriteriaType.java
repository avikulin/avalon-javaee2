package DAL.Utils.Filter.Enums;
import Common.Classes.IpAddress;
import DAL.Contracts.EntityType.DataEntity;

import java.sql.Date;
import java.util.Arrays;

public enum CriteriaType {
    ENTITY_REF(new Class<?>[]{DataEntity.class}),
    STRING_PATTERN(new Class<?>[]{String.class}),
    VALUE_PREDICATE(new Class<?>[]{Integer.class, Double.class}),
    DATE_PREDICATE(new Class<?>[]{Date.class});

    private final Class<?>[] supportedOperands;

    CriteriaType(Class<?>[] types) {
        this.supportedOperands = types;
    }

    public Class<?>[] getSupportedTypes(){
        return Arrays.copyOf(this.supportedOperands, this.supportedOperands.length);
    }
    public static CriteriaType getByType(Class<?> type){
        if (type.equals(Integer.class) ||
            type.equals(int.class) ||
            type.equals(Long.class) ||
            type.equals(long.class) ||
            type.equals(Double.class) ||
            type.equals(double.class)) {
            return CriteriaType.VALUE_PREDICATE;
        }

        if (type.equals(Date.class)){
            return CriteriaType.DATE_PREDICATE;
        }

        if(type.equals(String.class) || type.equals(IpAddress.class)){
            return CriteriaType.STRING_PATTERN;
        }

        if (type.isAssignableFrom(DataEntity.class)){
            return CriteriaType.ENTITY_REF;
        }
        throw new IllegalArgumentException("Unsupported type passed: "+type.getName());
    }
}
