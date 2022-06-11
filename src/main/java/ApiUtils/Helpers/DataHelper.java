package ApiUtils.Helpers;

import Common.Classes.IpAddress;
import Common.Exceptions.DataValidationException;
import DAL.DataEntities.Enums.DeviceType;
import DAL.DataEntities.Enums.OsiLayer;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataHelper {
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    public static String getString(String val) {
        if (DataValidator.isNotEmpty(val)) {
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

    public static int getInt(String val) throws DataValidationException {
        if (DataValidator.isInteger(val)) {
            return Integer.parseInt(val);
        } else {
            throw new DataValidationException("Ожидается целочисленное значение");
        }
    }

    public static long getLong(String val) throws DataValidationException {
        if (DataValidator.isLong(val)) {
            return Long.parseLong(val);
        } else {
            throw new DataValidationException("Ожидается целочисленное (длинное целое число) значение");
        }
    }

    public static Date getDate(String val) throws DataValidationException {
        java.util.Date date = null;
        try {
            date = formatter.parse(val);
            return new java.sql.Date(date.getTime());
        } catch (Exception e) {
            throw new DataValidationException(String.format("Ожидается строка даты в формате \"%s\"", DATE_FORMAT));
        }
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

    public static Class<?> cast(Class<?> clazz) throws DataValidationException {
            if (clazz.equals(Long.class) || clazz.equals(long.class)) {
                return Long.class;
            }
            if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
                return Integer.class;
            }
            if (clazz.equals(Double.class) || clazz.equals(double.class)) {
                return Double.class;
            }

            if (clazz.equals(IpAddress.class)){
                return String.class;
            }
            return clazz;
    }

    public static Class<?> detectClass(String s){
        if (DataValidator.isEmpty(s)){
            return Void.class;
        }

        if (DataValidator.isIpAddress(s)){
            return String.class;
        }

        if (DataValidator.isInteger(s)){
            return Integer.class;
        }

        if (DataValidator.isDate(s)){
            return Date.class;
        }

        return String.class;
    }

    public static Object extract(String s, Class<?> clazz) throws DataValidationException {
        try {
            if (clazz.equals(Long.class)) {
                return Long.parseLong(s);
            }
            if (clazz.equals(Integer.class)) {
                return Integer.parseInt(s);
            }
            if (clazz.equals(Double.class)) {
                return Double.parseDouble(s);
            }
            if (clazz.equals(Date.class)) {
                java.util.Date date = formatter.parse(s);
                return new java.sql.Date(date.getTime());
            }
            if (clazz.equals(String.class)){
                return s;
            }
            if (clazz.equals(OsiLayer.class)){
                return OsiLayer.valueOf(s);
            }

            if (clazz.equals(DeviceType.class)){
                return DeviceType.valueOf(s);
            }

            if (clazz.equals(IpAddress.class)){
                return new IpAddress(s);
            }

            throw new DataValidationException();
        }catch (NumberFormatException | ParseException nfe){
            String msg = String.format("Строка \"%s\" не является типом \"%s\"", s, clazz.getSimpleName());
            throw new DataValidationException(msg);
        }catch (DataValidationException dve){
            String msg = String.format("Неподдерживаемый тип значения \"%s\"", clazz.getSimpleName());
            throw new DataValidationException(msg);
        }
    }


}
