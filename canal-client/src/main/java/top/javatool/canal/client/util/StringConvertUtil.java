package top.javatool.canal.client.util;

import com.alibaba.google.common.base.Enums;
import org.apache.commons.lang.time.DateUtils;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author yangpeng
 */
public class StringConvertUtil {

    private static String[] PARSE_PATTERNS = new String[]{
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSS",
            "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
            "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
            "yyyy.MM.dd HH:mm", "yyyy.MM", "yyyy-MM-dd"};
    public StringConvertUtil() {
    }
    static Object convertType(Class type, String columnValue) {
        if (columnValue == null || columnValue.length() == 0) {
            return null;
        } else if (type.equals(String.class)) {
            return columnValue;
        } else if (type.equals(Integer.class)) {
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
        } else if (type.equals(LocalDate.class)) {
            return parseLocalDate(columnValue);
        } else if (type.equals(LocalDateTime.class)) {
            return parseLocalDateTime(columnValue);
        } else if (type.equals(java.sql.Date.class)) {
            return parseSqlDate(columnValue);
        } else if (type.isArray()) {
            // blob/binary
            return columnValue.getBytes(StandardCharsets.ISO_8859_1);
        } else {
            // 枚举类型
            return Enums.stringConverter(type).convert(columnValue);
        }
    }

    private static Object parseLocalDateTime(String str) {
        if (str == null) {
            return null;
        } else {
            Instant instant = parseDate(str).toInstant();
            return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
    }

    private static Object parseLocalDate(String str) {
        if (str == null) {
            return null;
        } else {
            Instant instant = parseDate(str).toInstant();
            return instant.atZone(ZoneId.systemDefault()).toLocalDate();
        }
    }

    private static Date parseDate(String str) {
        if (str == null) {
            return null;
        } else {
            try {
                return DateUtils.parseDate(str, PARSE_PATTERNS);
            } catch (ParseException var2) {
                return null;
            }
        }
    }

    private static Date parseSqlDate(String str) {
        if (str == null) {
            return null;
        } else {
            try {
                Date date = DateUtils.parseDate(str, PARSE_PATTERNS);
                return new java.sql.Date(date.getTime());
            } catch (ParseException var2) {
                return null;
            }
        }
    }

    private static boolean convertToBoolean(String value) {
        return "1".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value);
    }
}
