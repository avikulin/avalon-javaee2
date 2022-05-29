package ApiUtils.Helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataValidator {
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public static boolean isName(String val) {
        String name = "^[A-Za-z ]*$";
        if (val.matches(name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPhoneNumber(String val) {
        String passregex = "^([0-9]{2}[A-Z]{2}[0-9]{1,})\\S$";
        if (val.matches(passregex)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPassword(String val) {
        String passregex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[A-Za-z0-9\\S]{6,12}$";
        if (val.matches(passregex)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPhoneNo(String val) {
        String regex = "^[7-9][0-9]{9}$";
        if (val.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNull(String val) {
        if (val == null || val.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotNull(String val) {
        return !isNull(val);
    }

    public static boolean isInteger(String val) {
        if (isNotNull(val)) {
            try {
                int i = Integer.parseInt(val);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isLong(String val) {
        if (isNotNull(val)) {
            try {
                long i = Long.parseLong(val);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isEmail(String val) {
        String eMailReg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (isNotNull(val)) {
            try {
                return val.matches(eMailReg);
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isDate(String val) {
        if (isNotNull(val)) {
            try {
                LocalDate.parse(val, DateTimeFormatter.ofPattern(DATE_FORMAT));
            } catch (DateTimeParseException e) {
                return false;
            }

        }
        return true;
    }
}


