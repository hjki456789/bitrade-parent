package cn.ztuo.bitrade.util;

import java.text.*;
import java.util.*;

public class DateUtils
{
    public static final SimpleDateFormat YYYY_MM_DD;
    public static final SimpleDateFormat YYYY_MM_DD_MM_HH_SS;
    
    public static Date getSomeDaysDate(final int days) {
        final Calendar cal = Calendar.getInstance();
        cal.add(5, -days);
        return cal.getTime();
    }
    
    public static Date getYesterdayBegin() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(5, -1);
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        return calendar.getTime();
    }
    
    public static Date getYesterdayEnd() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(5, -1);
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 23, 59, 59);
        return calendar.getTime();
    }
    
    public static String getYesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(5, -1);
        final Date d = cal.getTime();
        return DateUtils.YYYY_MM_DD.format(d);
    }
    
    public static String formatDate(final Date date, final SimpleDateFormat sdf) {
        try {
            return sdf.format(date);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static int getDaysBetweenDate(final Date startDate, final Date endDate) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            final Date start = sdf.parse(sdf.format(startDate));
            final Date end = sdf.parse(sdf.format(endDate));
            final Long sub = end.getTime() - start.getTime();
            return (int)(sub / 86400000L);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("args = [" + getYesterdayBegin() + "]");
        System.out.println("args = [" + getYesterdayEnd() + "]");
        System.out.println(formatDate(new Date(), DateUtils.YYYY_MM_DD));
    }
    
    static {
        YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
        YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
