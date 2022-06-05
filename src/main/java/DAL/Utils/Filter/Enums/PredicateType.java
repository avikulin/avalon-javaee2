package DAL.Utils.Filter.Enums;

public enum PredicateType {
    EQ("="),  // Equals to
    GTE(">="), // Greater than OR equal to
    LTE("<="); // Less than OR equal to

    private String operator;

    PredicateType(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
