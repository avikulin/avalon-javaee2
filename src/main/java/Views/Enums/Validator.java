package Views.Enums;

import ApiUtils.Helpers.DataValidator;

import java.util.function.Predicate;

public enum Validator {
    ONLY_LETTERS(DataValidator::isLettersOnly,"Допускаются только буквенные символы"),
    ONLY_DIGITS(DataValidator::isDigitsOnly, "Допускаются только цифровые символы"),
    LANG_RU (DataValidator::isRussianText, "Допускаются только буквы русского алфавита, дефис и цифры"),
    LANG_ENG (DataValidator::isRussianText, "Допускаются только буквы русского алфавита, дефис и цифры"),
    IP_ADDR (DataValidator::isIpAddress, "Неправильный формат IP-адреса"),
    EMAIL_ADDR (DataValidator::isEmail, "Неправильный форма адреса электронной почты"),
    WEB_SITE_ADDR(DataValidator::isWebAddress, "Неправильный формат URL-адреса"),
    INTEGER_VALUE_TYPE (DataValidator::isLong, "Допаскается ввод только целого числа"),
    DOUBLE_VALUE_TYPE (DataValidator::isDouble, "Допаскается ввод только дробного числа"),
    DATE_VALUE_TYPE (DataValidator::isDate, "Допаскается ввод только строки с датой (дд-ММ-ГГГГ)");

    private final Predicate<String> func;
    private final String errMsg;

    Validator(Predicate<String> func, String errMsg) {
        this.func = func;
        this.errMsg = errMsg;
    }

    public boolean check(String s){
        return this.func.test(s);
    }

    public String getErrMsg(){
        return this.errMsg;
    }
}
