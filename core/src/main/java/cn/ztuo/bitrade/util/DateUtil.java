package cn.ztuo.bitrade.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static final DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat HHMMSS = new SimpleDateFormat("HH:mm:ss");
    public static final DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    public static final DateFormat YYYYMMDDMMHHSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static final DateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");

    public static final DateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

    public static String dateToString(Date date) {
        return YYYY_MM_DD_MM_HH_SS.format(date);
    }

    public static String dateToStringDate(Date date) {
        return YYYY_MM_DD.format(date);
    }

    public static String YYYYMMDDMMHHSSSSS(Date date) {
        return YYYYMMDDMMHHSSSSS.format(date);
    }

    /**
     * 开始时间 结束时间 是否合法  // 判断是否开始时间小于今天并且开始时间小于结束时间
     *
     * @param startDate
     * @param endDate
     */
    public static void validateDate(Date startDate, Date endDate) {
        Date currentDate = DateUtil.getCurrentDate();
        int compare = compare(startDate, currentDate);
        int compare2 = compare(startDate, endDate);
        Assert.isTrue(compare != -1, "startDate cannot be less than currentDate!");
        Assert.isTrue(compare2 != 1, "startDate must be less than endDate!");
    }

    public static void validateEndDate(Date endDate) {
        Date currentDate = DateUtil.getCurrentDate();
        int compare = compare(currentDate, endDate);
        Assert.isTrue(compare != 1, "currentDate must be less than endDate!");
    }

    /**
     * @param date1
     * @param date2
     * @return 1 大于 -1 小于 0 相等
     */
    public static int compare(Date date1, Date date2) {
        try {
            if (date1.getTime() > date2.getTime()) {
                return 1;
            } else if (date1.getTime() < date2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取当时日期时间串 格式 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getDateTime() {
        return YYYY_MM_DD_MM_HH_SS.format(new Date());
    }

    public static Date stringToDate(String dateString) throws Exception{
        return YYYY_MM_DD_MM_HH_SS.parse(dateString);

    }

    /**
     * 获取当时日期串 格式 yyyy-MM-dd
     *
     * @return
     */
    public static String getDate() {
        return YYYY_MM_DD.format(new Date());
    }

    public static String getDateYMD() {
        return YYYYMMDD.format(new Date());
    }

    public static String getDateYMD(Date date) {
        return YYYYMMDD.format(date);
    }

    public static Date strToDate(String dateString) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date strToYYMMDDDate(String dateString) {
        Date date = null;
        try {
            date = YYYY_MM_DD.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static long diffDays(Date startDate, Date endDate) {
        long days = 0L;
        long start = startDate.getTime();
        long end = endDate.getTime();
        days = (end - start) / 86400000L;
        return days;
    }

    public static Date dateAddMonth(Date date, int month) {
        return add(date, 2, month);
    }

    public static Date dateAddDay(Date date, int day) {
        return add(date, Calendar.DAY_OF_YEAR, day);
    }

    public static Date dateAddYear(Date date, int year) {
        return add(date, 1, year);
    }

    public static String dateAddDay(String dateString, int day) {
        Date date = strToYYMMDDDate(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(6, day);
        return YYYY_MM_DD.format(calendar.getTime());
    }

    public static String dateAddDay(int day) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(6, day);
        return YYYY_MM_DD.format(calendar.getTime());
    }

    public static String dateAddMonth(int month) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, month);
        return YYYY_MM_DD.format(calendar.getTime());
    }

    public static String remainDateToString(Date startDate, Date endDate) {
        StringBuilder result = new StringBuilder();
        if (endDate == null) {
            return "过期";
        }
        long times = endDate.getTime() - startDate.getTime();
        if (times < -1L) {
            result.append("过期");
        } else {
            long temp = 86400000L;

            long d = times / temp;

            times %= temp;
            temp /= 24L;
            long m = times / temp;

            times %= temp;
            temp /= 60L;
            long s = times / temp;

            result.append(d);
            result.append("天");
            result.append(m);
            result.append("小时");
            result.append(s);
            result.append("分");
        }
        return result.toString();
    }

    private static Date add(Date date, int type, int value) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, value);
        return calendar.getTime();
    }

    public static String getLinkUrl(boolean flag, String content, String id) {
        if (flag) {
            content = "<a href='finance.do?id=" + id + "'>" + content + "</a>";
        }
        return content;
    }

    public static long getTimeCur(String format, String date) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.parse(sf.format(date)).getTime();
    }

    public static long getTimeCur(String format, Date date) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.parse(sf.format(date)).getTime();
    }

    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        long lcc_time = Long.valueOf(cc_time).longValue();
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }


    public static Date getCurrentDate() {
        return new Date();
    }

    public static String getFormatTime(DateFormat format, Date date) throws ParseException {
        return format.format(date);
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static long getTimeMillis() {
        return System.currentTimeMillis();
    }

    public static String getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
        }
        return "";
    }

    public static String toGMTString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.UK);
        df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    /**
     * 得到当前时间与某个时间的差的分钟数
     *
     * @param date
     * @return
     */
    public static BigDecimal diffMinute(Date date) {
        return BigDecimalUtils.div(new BigDecimal(System.currentTimeMillis() - date.getTime()), new BigDecimal
                ("60000"));
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取未来 第 past 天的日期
     *
     * @param past
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    public static int getDatePart(Date date, int part) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(part);
    }

    /**
     * 返回目标日期的前n天
     *
     * @param date target date
     * @param day  n
     * @return Date格式 yyyy-MM-dd
     */
    public static Date getDate(Date date, int day) {

        synchronized (YYYY_MM_DD) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, -day);
            date = calendar.getTime();
            try {
                return YYYY_MM_DD.parse(YYYY_MM_DD.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 获取当前时间到凌晨多少s
     *
     * @return
     */
    public static Long calculateCurrentTime2SecondDaySec() {
        Long currentTime = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, 1);
        Long endTime = calendar.getTimeInMillis();
        return (endTime - currentTime) / 1000;
    }

    public static Date geLastWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public static Date getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }

    public static Date getThisMonthFirstDay(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, 1);
        return cal.getTime();
    }

    public static Date getLastMonthFirstDay(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(getThisMonthFirstDay(date));
        cal.add(2, -1);
        return cal.getTime();
    }

    public static Date getNextMonthFirstDay(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(getThisMonthFirstDay(date));
        cal.add(2, 1);
        return cal.getTime();
    }

    public static Date getTimestamp(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getTime();
    }

    public static Date nDaysBaseOnDate(final Date date, final int nDay) {
        final Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(5, now.get(5) + nDay);
        return now.getTime();
    }

    public static long getLaterTime(final Date date, final Integer num, final Integer type) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (type == 1) {
            calendar.add(5, num);
        }
        else if (type == 2) {
            calendar.add(2, num);
        }
        else {
            if (type != 3) {
                return 0L;
            }
            calendar.add(1, num);
        }
        return calendar.getTime().getTime();
    }

    public static long getDays(long minSequence, long maxSequence) {
        if (minSequence >= maxSequence) {
            return 0L;
        }
        try {
            final String dateMin = DateFormatUtils.format(minSequence, "yyyyMMdd");
            final String dateMax = DateFormatUtils.format(maxSequence, "yyyyMMdd");
            final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            minSequence = dateFormat.parse(dateMin).getTime();
            maxSequence = dateFormat.parse(dateMax).getTime();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return (maxSequence - minSequence) / 86400000L;
    }

    public static long getTodaySequence() {
        try {
            final String date = DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd");
            final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            return dateFormat.parse(date).getTime();
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static long getYesterdaySequence() {
        try {
            final String date = DateFormatUtils.format(System.currentTimeMillis() - 86400000L, "yyyyMMdd");
            final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            return dateFormat.parse(date).getTime();
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static Date getYesterdayZeroDate(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(getZeroDate(date));
        cal.add(5, -1);
        return cal.getTime();
    }

    public static Date getZeroDate(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        return cal.getTime();
    }

    public static Date getTomorrowZeroDate(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(getZeroDate(date));
        cal.add(5, 1);
        return cal.getTime();
    }

    public static Long getStartTimeOfDay(final Date now) {
        try {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            return calendar.getTimeInMillis();
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static Date getLastWeekMonday(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(5, -7);
        return cal.getTime();
    }
}
