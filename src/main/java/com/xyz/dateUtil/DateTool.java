package com.xyz.dateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  日期工具类
 */
public class DateTool {

    private static Logger logger = LoggerFactory.getLogger(DateTool.class);

    private static final String DATE_FORMAT1 = "yyyy-MM-dd";
    private static final String DATE_FORMAT2 = "yyyyMMdd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final ThreadLocal<Map<String, DateFormat>> dateFormatThreadLocal = new ThreadLocal<>();

    private static DateFormat getDateFormat(String pattern) {
        if (pattern==null || pattern.trim().length()==0) {
            throw new IllegalArgumentException("pattern cannot be empty.");
        }

        Map<String, DateFormat> dateFormatMap = dateFormatThreadLocal.get();
        if(dateFormatMap!=null && dateFormatMap.containsKey(pattern)){
            return dateFormatMap.get(pattern);
        }

        synchronized (dateFormatThreadLocal) {
            if (dateFormatMap == null) {
                dateFormatMap = new HashMap<String, DateFormat>();
            }
            dateFormatMap.put(pattern, new SimpleDateFormat(pattern));
            dateFormatThreadLocal.set(dateFormatMap);
        }

        return dateFormatMap.get(pattern);
    }

    public static String format(Date date, String patten) {
        return getDateFormat(patten).format(date);
    }

    /***********************日期转换成字符串*************************/

    /**
     *  将日期类型参数转换成yyyy-MM-dd格式字符串
     * @param date 需要转换的日期
     * @return 返回yyyy-MM-dd格式的日期字符串
     */
    public static String formatDate1(Date date) {
        return format(date, DATE_FORMAT1);
    }

    /**
     *  将日期类型参数转换成yyyyMMdd格式字符串
     * @param date 需要转换的日期
     * @return 返回yyyyMMdd格式的日期字符串
     */
    public static String formatDate2(Date date) {
        return format(date, DATE_FORMAT2);
    }

    /**
     *  将日期类型转换成yyyy-MM-dd HH:mm:ss格式字符串
     * @param date 需要转换的字符串
     * @return 返回yyyy-MM-dd HH:mm:ss格式的字符串
     */
    public static String formatDateTime(Date date) {
        return format(date, DATETIME_FORMAT);
    }


    /**********************字符串转换成日期************************/
    /**
     * 将字符串转换成指定格式的日期
     *
     * @param dateString    日期字符串
     * @param pattern       日期格式
     * @return  返回转换好的日期
     * @throws ParseException
     */
    public static Date parse(String dateString, String pattern) {
        try {
            Date date = getDateFormat(pattern).parse(dateString);
            return date;
        } catch (Exception e) {
            logger.warn("字符串转换为日期出现错误, 日期字符串 = {}, 日期格式={}; 错误信息 = {}", dateString, pattern, e.getMessage());
            return null;
        }
    }

    /**
     *  将字符串日期转换成对应的日期
     * @param dateString 需要转换的日期字符串
     * @return 返回yyyy-MM-dd格式的日期
     */
    public static Date parseDate1(String dateString) {
        return parse(dateString, DATE_FORMAT1);
    }

    /**
     *  将字符串日期转换成对应的日期
     * @param dateString 需要转换的日期字符串
     * @return 返回yyyyMMdd格式的日期
     */
    public static Date parseDate2(String dateString) {
        return parse(dateString, DATE_FORMAT2);
    }

    /***************对指定日期加天数*****************/
    //给对应的日期加天数
    private static Date add(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            return null;
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     *  给指定的日期的年份增加年份
     * @param date  需要操作的日期
     * @param amount 添加的年份数，正数是加年，负数为减年
     * @return
     */
    public static Date addYears(final Date date, final int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    /**
     *  给指定日期增减月份
     * @param date  指定日期
     * @param amount    增减的数
     * @return
     */
    public static Date addMonths(final Date date, final int amount) {
        return add(date, Calendar.MONTH, amount);
    }
}
