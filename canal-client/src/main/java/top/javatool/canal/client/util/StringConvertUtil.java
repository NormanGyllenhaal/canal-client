package top.javatool.canal.client.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * @author yangpeng
 */
public class StringConvertUtil {


    private static String[] PARSE_PATTERNS = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};


    static Object convertType(Class<?> type, String columnValue) {
        if(columnValue==null || columnValue.equals("")){
            return null;
        }else if (type.equals(Integer.class)) {
            return Integer.parseInt(columnValue);
        } else if (type.equals(Long.class)) {
            return Long.parseLong(columnValue);
        } else if (type.equals(Boolean.class)) {
            return convertToBoolean(columnValue);
        } else if (type.equals(BigDecimal.class)) {
            return new BigDecimal(columnValue);
        } else if (type.equals(Double.class)) {
            return Double.parseDouble(columnValue);
        } else if (type.equals(Float.class)) {
            return Float.parseFloat(columnValue);
        } else if (type.equals(Date.class)) {
            return parseDate(columnValue);
        } else if (type.equals(java.sql.Date.class)) {
            return parseDate(columnValue);
        } else {
            return columnValue;
        }
    }


    private static Date parseDate(String str){
        if (str == null) {
            return null;
        }
        try {
            return org.apache.commons.lang.time.DateUtils.parseDate(str, PARSE_PATTERNS);
        } catch (ParseException e) {
            return null;
        }
    }


    private static boolean convertToBoolean(String value) {
        return "1".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value);
    }

}
