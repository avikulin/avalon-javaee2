package ApiUtils.Helpers;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DataHelper {
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    public static String getString(String val) {
        if (DataValidator.isNotNull(val)) {
            return val.trim();
        } else {
            return val;
        }
    }

    public static String getStringData(Object val) {

        if (val != null) {
            return val.toString();
        } else {
            return "";
        }
    }

    public static int getInt(String val) {
        if (DataValidator.isInteger(val)) {
            return Integer.parseInt(val);
        } else {
            return 0;
        }
    }

    public static long getLong(String val) {
        if (DataValidator.isLong(val)) {
            return Long.parseLong(val);
        } else {
            return 0;
        }
    }

    public static Date getDate(String val) {
        java.util.Date date = null;
        try {
            date = formatter.parse(val);
        } catch (Exception e) {
        }
        return new java.sql.Date(date.getTime());
    }

    public static String getDateString(Date date) {
    try {
            if(date!=null) {
                return formatter.format(date);
            }
            else{
                return "";
            }
        } catch (Exception e) {
            return "";
        }

    }

    public static Date getDate(Date date, int day) {
        return null;
    }
}
