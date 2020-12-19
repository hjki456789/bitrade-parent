package cn.ztuo.bitrade.utils;

import java.math.*;

public class BigDecimalCalculateUtils
{
    private static final int DEF_DIV_SCALE = 10;
    private static final long E8 = 100000000L;
    
    private BigDecimalCalculateUtils() {
    }
    
    public static long mulE8(final double v1) {
        final long back = (long)mul(v1, 1.0E8);
        return back;
    }
    
    public static double divE8(final long v1) {
        final double back = div((double)v1, 1.0E8);
        return back;
    }
    
    public static double add(final double v1, final double v2) {
        final BigDecimal b1 = new BigDecimal(Double.toString(v1));
        final BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    
    public static double sub(final double v1, final double v2) {
        final BigDecimal b1 = new BigDecimal(Double.toString(v1));
        final BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    
    public static double mul(final double v1, final double v2) {
        final BigDecimal b1 = new BigDecimal(Double.toString(v1));
        final BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
    
    public static double div(final double v1, final double v2) {
        return div(v1, v2, 10);
    }
    
    public static double div(final double v1, final double v2, final int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        final BigDecimal b1 = new BigDecimal(Double.toString(v1));
        final BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, 4).doubleValue();
    }
    
    public static double round(final double v, final int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        final BigDecimal b = new BigDecimal(Double.toString(v));
        final BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, 4).doubleValue();
    }
    
    public static float convertsToFloat(final double v) {
        final BigDecimal b = new BigDecimal(v);
        return b.floatValue();
    }
    
    public static int convertsToInt(final double v) {
        final BigDecimal b = new BigDecimal(v);
        return b.intValue();
    }
    
    public static long convertsToLong(final double v) {
        final BigDecimal b = new BigDecimal(v);
        return b.longValue();
    }
    
    public static double returnMax(final double v1, final double v2) {
        final BigDecimal b1 = new BigDecimal(v1);
        final BigDecimal b2 = new BigDecimal(v2);
        return b1.max(b2).doubleValue();
    }
    
    public static double returnMin(final double v1, final double v2) {
        final BigDecimal b1 = new BigDecimal(v1);
        final BigDecimal b2 = new BigDecimal(v2);
        return b1.min(b2).doubleValue();
    }
    
    public static int compareTo(final double v1, final double v2) {
        final BigDecimal b1 = new BigDecimal(v1);
        final BigDecimal b2 = new BigDecimal(v2);
        return b1.compareTo(b2);
    }
    
    public static double formatDouble2(final double d) {
        final int num = 100;
        return Math.round(d * num) / (double)num;
    }
    
    public static double formatDouble3(final double d) {
        final int num = 1000;
        return Math.round(d * num) / (double)num;
    }
    
    public static double formatDouble15(final double d) {
        final long num = 1000000000000000L;
        return Math.round(d * num) / (double)num;
    }
}
