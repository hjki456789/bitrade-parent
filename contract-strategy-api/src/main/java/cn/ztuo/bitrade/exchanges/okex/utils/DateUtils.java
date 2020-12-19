package cn.ztuo.bitrade.exchanges.okex.utils;

import java.time.*;
import java.math.*;

import org.apache.commons.lang3.*;

import java.text.*;
import java.util.*;

public class DateUtils {
    public static String TIME_STYLE_S1;
    public static String TIME_STYLE_S2;
    public static String TIME_STYLE_S3;
    public static String TIME_STYLE_S4;
    public static String TIME_STYLE_S5;
    public static String TIME_STYLE_S6;
    public static String TIME_STYLE_S7;
    public static SimpleDateFormat SDF;

    public static String timeToString(Date time, final int style) {
        if (time == null) {
            time = new Date();
        }
        String timeStyle = null;
        switch (style) {
            case 1: {
                timeStyle = DateUtils.TIME_STYLE_S1;
                break;
            }
            case 2: {
                timeStyle = DateUtils.TIME_STYLE_S2;
                break;
            }
            case 3: {
                timeStyle = DateUtils.TIME_STYLE_S3;
                break;
            }
            case 4: {
                timeStyle = DateUtils.TIME_STYLE_S4;
                break;
            }
            case 5: {
                timeStyle = DateUtils.TIME_STYLE_S5;
                break;
            }
            case 6: {
                timeStyle = DateUtils.TIME_STYLE_S6;
                break;
            }
            case 7: {
                timeStyle = DateUtils.TIME_STYLE_S7;
                break;
            }
            case 8: {
                final SimpleDateFormat sdf = (SimpleDateFormat) DateUtils.SDF.clone();
                return sdf.format(time);
            }
            case 9: {
                return getEpochTime(time);
            }
            default: {
                return time.toString();
            }
        }
        return new SimpleDateFormat(timeStyle).format(time);
    }

    public static String timeToStringNull(final Date time, final int style) {
        return (time == null) ? null : timeToString(time, style);
    }

    public static String getUnixTime() {
        final StringBuilder nowStr = new StringBuilder(Instant.now().toString());
        return new StringBuilder().append(nowStr.toString()).toString();
    }

    public static String getEpochTime(final Date... time) {
        long milliseconds;
        if (time != null && time.length > 0) {
            milliseconds = time[0].getTime();
        } else {
            milliseconds = Instant.now().toEpochMilli();
        }
        milliseconds -= 28800000L;
        final BigDecimal bd1 = new BigDecimal(milliseconds);
        final BigDecimal bd2 = new BigDecimal(1000);
        return bd1.divide(bd2).toString();
    }

    public static Date parseUTCTime(final String utcTime) throws ParseException {
        if (StringUtils.isEmpty((CharSequence) utcTime)) {
            return null;
        }
        final SimpleDateFormat sdfi = (SimpleDateFormat) DateUtils.SDF.clone();
        return sdfi.parse(utcTime);
    }

    public static Date parseDecimalTime(final String decimalTime) {
        if (StringUtils.isEmpty((CharSequence) decimalTime)) {
            return null;
        }
        final BigDecimal bd1 = new BigDecimal(decimalTime);
        final BigDecimal bd2 = new BigDecimal(1000);
        return new Date(bd1.multiply(bd2).longValue());
    }

    static {
        DateUtils.TIME_STYLE_S1 = "yyyy-MM-dd";
        DateUtils.TIME_STYLE_S2 = "yyyy-MM-dd HH:mm";
        DateUtils.TIME_STYLE_S3 = "yyyy-MM-dd HH:mm:ss";
        DateUtils.TIME_STYLE_S4 = "yyyy-MM-dd HH:mm:ss:S";
        DateUtils.TIME_STYLE_S5 = "yyyy-MM-dd HH:mm:ss:S E zZ";
        DateUtils.TIME_STYLE_S6 = "yyyyMMddHHmmssS";
        DateUtils.TIME_STYLE_S7 = "yyyy\u5e74MM\u6708dd\u65e5HH\u65f6mm\u5206ss\u79d2";
        (DateUtils.SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'")).setTimeZone(TimeZone.getTimeZone("UTC"));
    }
}
