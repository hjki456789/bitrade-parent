package cn.ztuo.bitrade.exchanges.okex.utils;

import org.apache.commons.lang3.*;

import java.text.*;

public class NumberUtils {
    public static String doubleToString(final Double arg) {
        if (arg == null) {
            return "0.00";
        }
        final String argStr = arg.toString();
        int scale2;
        if (argStr.contains("E") || argStr.contains("e")) {
            final String[] argArray = argStr.toUpperCase().split("E");
            final int scale1 = Integer.parseInt(argArray[1]);
            scale2 = ((scale1 < 0) ? Math.abs(scale1) : scale1);
            final String scale3 = argArray[0];
            if (scale3.contains(".")) {
                final String[] argDecimalsArray = scale3.split("\\.");
                final String decimalsAfter = argDecimalsArray[1];
                final int decimalsAfterI = Integer.parseInt(decimalsAfter);
                if (decimalsAfterI > 0) {
                    scale2 += decimalsAfter.length();
                }
            }
        } else {
            scale2 = 2;
        }
        final NumberFormat format = getNumberFormat(scale2);
        String result = format.format(arg);
        if (StringUtils.isNotEmpty((CharSequence) result) && result.contains(".")) {
            result = result.replaceAll("0+?$", "");
            result = result.replaceAll("[.]$", "");
        }
        return result;
    }

    private static NumberFormat getNumberFormat(final int scale) {
        final NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        format.setMinimumFractionDigits(scale);
        return format;
    }

    public static int toInteger(final String string) {
        return toInteger(string, 0);
    }

    public static int toInteger(String string, final int defaultValue) {
        if (StringUtils.isNotEmpty((CharSequence) string)) {
            string = string.trim();
            if (StringUtils.isNumeric((CharSequence) string)) {
                return Integer.parseInt(string);
            }
        }
        return defaultValue;
    }

    public static long toLong(final String string) {
        return toLong(string, 0L);
    }

    public static long toLong(String string, final long defaultValue) {
        if (StringUtils.isNotEmpty((CharSequence) string)) {
            string = string.trim();
            if (StringUtils.isNumeric((CharSequence) string)) {
                return Long.parseLong(string);
            }
        }
        return defaultValue;
    }
}
