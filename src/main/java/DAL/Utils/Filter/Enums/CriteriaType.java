package DAL.Utils.Filter.Enums;
import Common.IpAddress;

import java.sql.Date;
import java.util.Arrays;

public enum CriteriaType {
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
        if (type.equals(Integer.class) || type.equals(Double.class)){
            return CriteriaType.VALUE_PREDICATE;
        }

        if (type.equals(Date.class)){
            return CriteriaType.DATE_PREDICATE;
        }

        if(type.equals(String.class) || type.equals(IpAddress.class)){
            return CriteriaType.STRING_PATTERN;
        }
        throw new IllegalArgumentException("Unsupported type passed: "+type.getName());
    }
}
