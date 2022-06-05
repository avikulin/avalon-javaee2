package ApiUtils.Helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class DataValidator {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String PATTERN_ENGLISH_TEXT = "^[0-9A-Za-z,\\-\\s]*$";
    private static final String PATTERN_RUSSIAN_TEXT = "^[0-9А-яа-я,\\-\\s]*$";
    private static final String PATTERN_ONLY_LETTERS = "^[а-яА-Яa-zA-Z\\-\\s]*$";
    private static final String PATTERN_ONLY_DIGITS = "^[0-9]*$";
    private static final String PATTERN_PHONE_AREA_CODE = "^[0-9]{3}$";
    private static final String PATTERN_PHONE_NUMBER_WITH_CODE ="^([+]?[\\s0-9]+)?(\\d{3}|[(]?[0-9]+[)])?([-]?[\\s]?[0-9])+$";
    private static final String PATTERN_EMAIL_ADDR = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PATTERN_IP_ADDR = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    private static final String PATTERN_WEB_ADDR = "/((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)/";
    private static final String PATTERN_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[A-Za-z0-9\\S]{6,12}$";

    private static final Pattern englishText = Pattern.compile(PATTERN_ENGLISH_TEXT);
    private static final Pattern russianText = Pattern.compile(PATTERN_RUSSIAN_TEXT);
    private static final Pattern onlyLetters = Pattern.compile(PATTERN_ONLY_LETTERS);
    private static final Pattern onlyDigits = Pattern.compile(PATTERN_ONLY_DIGITS);
    private static final Pattern phoneAreaCode = Pattern.compile(PATTERN_PHONE_AREA_CODE);
    private static final Pattern phoneNumberWithCode = Pattern.compile(PATTERN_PHONE_NUMBER_WITH_CODE);
    private static final Pattern emailAddress = Pattern.compile(PATTERN_EMAIL_ADDR);
    private static final Pattern ipAddress = Pattern.compile(PATTERN_IP_ADDR);
    private static final Pattern webAddress = Pattern.compile(PATTERN_WEB_ADDR);
    private static final Pattern password = Pattern.compile(PATTERN_PASSWORD);


    public static boolean isRussianText(String s){
        if (isEmpty(s)) return true;
        return russianText.matcher(s).matches();
    }

    public static boolean isEnglishText(String s) {
        if (isEmpty(s)) return true;
        return englishText.matcher(s).matches();
    }

    public static boolean isLettersOnly(String s) {
        if (isEmpty(s)) return true;
        return onlyLetters.matcher(s).matches();
    }

    public static boolean isDigitsOnly(String s) {
        if (isEmpty(s)) return true;
        return onlyDigits.matcher(s).matches();
    }

    public static boolean isPhoneNumber(String s) {
        if (isEmpty(s)) return true;
        return phoneNumberWithCode.matcher(s).matches();
    }

    public static boolean isPhoneAreaCode(String s) {
        if (isEmpty(s)) return true;
        return phoneAreaCode.matcher(s).matches();
    }

    public static boolean isPassword(String s) {
        if (isEmpty(s)) return true;
        return password.matcher(s).matches();
    }

    public static boolean isIpAddress(String s) {
        if (isEmpty(s)) return true;
        return ipAddress.matcher(s).matches();
    }

    public static boolean isEmail(String s) {
        if (isEmpty(s)) return true;
        return emailAddress.matcher(s).matches();
    }

    public static boolean isWebAddress(String s) {
        if (isEmpty(s)) return true;
        return webAddress.matcher(s).matches();
    }

    public static boolean isEmpty(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static boolean isInteger(String s) {
        if (isNotEmpty(s)) {
            try {
                int i = Integer.parseInt(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDouble(String s) {
        if (isNotEmpty(s)) {
            try {
                double i = Double.parseDouble(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLong(String s) {
        if (isNotEmpty(s)) {
            try {
                long i = Long.parseLong(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDate(String s) {
        if (isNotEmpty(s)) {
            try {
                LocalDate.parse(s, DateTimeFormatter.ofPattern(DATE_FORMAT));
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }
        return true;
    }
}


