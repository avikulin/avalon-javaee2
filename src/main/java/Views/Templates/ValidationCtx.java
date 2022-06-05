package Views.Templates;

import Views.Enums.Validator;

public class ValidationCtx {
    private final Validator[] rules;
    private final int minLength;
    private final int maxLength;
    private final boolean mayBeEmpty;

    public ValidationCtx(Validator[] matchPatterns, int minLength, int maxLength, boolean mayBeEmpty) {
        this.rules = matchPatterns;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.mayBeEmpty = mayBeEmpty;
    }

    public Validator[] getRules() {
        return rules;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public boolean isMayBeEmpty() {
        return mayBeEmpty;
    }
}
