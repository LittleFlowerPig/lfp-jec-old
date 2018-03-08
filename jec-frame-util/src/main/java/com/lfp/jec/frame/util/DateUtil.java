package com.lfp.jec.frame.util;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Project: lfp-jec
 * Title: 时间处理工具类
 * Description: 结合SimpleDateFormat，实现时间转化函数
 * Date: 2018-03-08
 * Copyright: Copyright (c) 2018
 * Company: LFP
 *
 * @author ZhuTao
 * @version 1.0
 */
public class DateUtil {

    /*==================================================
                        部分静态变量
     ==================================================*/
    /** 一天的毫秒数 */
    public static final long MISEC_OF_DAY = 1000 * 60 * 60 * 24;
    /** 一小时的毫秒数 */
    public static final long MISEC_OF_HOUR = 1000 * 60 * 60;
    /** 一天的秒数 */
    public static final long SECOND_OF_DAY = 60 * 60 * 24;
    /** 一小时的秒数 */
    public static final long SECOND_OF_HOUR = 60 * 60;
    /** 精确完整的时间样式 */
    public static final String TIME_ALL_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    /** 精确到秒的时间样式 */
    public static final String TIME_SEC_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** 精确到分的时间样式 */
    public static final String TIME_MIN_PATTERN = "yyyy-MM-dd HH:mm";
    /** 精确到天的时间样式 */
    public static final String TIME_DATE_PATTERN = "yyyy-MM-dd";
    /** 精确完整的时间格式 */
    public static final SimpleDateFormat TIME_ALL_FORMAT = new SimpleDateFormat(TIME_ALL_PATTERN);
    /** 精确到秒的时间格式 */
    public static final SimpleDateFormat TIME_SEC_FORMAT = new SimpleDateFormat(TIME_SEC_PATTERN);
    /** 精确到分的时间格式 */
    public static final SimpleDateFormat TIME_MIN_FORMAT = new SimpleDateFormat(TIME_MIN_PATTERN);
    /** 精确到天的时间格式 */
    public static final SimpleDateFormat TIME_DATE_FORMAT = new SimpleDateFormat(TIME_DATE_PATTERN);

    /*==================================================
                        日期各属性值
     ==================================================*/
    /**
     * 获取给定时间的Calendar
     * @param date              给定时间
     * @return Calendar         日历对象
     */
    public static Calendar getCalendar(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    /**
     * 获取给定时间的年度
     * @param date              给定时间
     * @return year             年度
     */
    public static int getYear(Date date) {
        if(date == null) return 0;
        return getCalendar(date).get(Calendar.YEAR);
    }
    /**
     * 获取当前时间的年度
     * @return int              年度
     */
    public static int getYear() {
        return getYear(new Date());
    }
    /**
     * 获取给定时间的季度
     * @param date              给定时间
     * @return int              季度
     */
    public static int getQuarter(Date date) {
        Integer quarter=0;
        int month = getMonth(date);
        if(month>=1 && month<=3)
            quarter=1;
        if(month>=4 && month<=6)
            quarter=2;
        if(month>=7 && month<=9)
            quarter=3;
        if(month>=10 && month<=12)
            quarter=4;
        return quarter;
    }
    /**
     * 获取当前时间的季度
     * @return int              季度
     */
    public static int getQuarter() {
        return getQuarter(new Date());
    }
    /**
     * 获取给定时间的月度
     * @param date              给定时间
     * @return int              月度
     */
    public static int getMonth(Date date) {
        if(date == null) return 0;
        return getCalendar(date).get(Calendar.MONTH)+1;
    }
    /**
     * 获取当前时间的月度
     * @return int              月度
     */
    public static int getMonth() {
        return getMonth(new Date());
    }
    /**
     * 获取给定时间的月度天数
     * @param date              给定时间
     * @return int              天数
     */
    public static int getDay(Date date) {
        if(date == null) return 0;
        return getCalendar(date).get(Calendar.DATE);
    }
    /**
     * 获取当前时间的月度天数
     * @return int              天数
     */
    public static int getDay() {
        return getDay(new Date());
    }
    /**
     * 获取给定时间的周度天数
     * @param date              给定时间
     * @return int              天数
     */
    public static int getDayOfWeek(Date date) {
        if(date == null) return 0;
        return getCalendar(date).get(Calendar.DAY_OF_WEEK);
    }
    /**
     * 获取当前时间的周度天数
     * @return int              天数
     */
    public static int getDayOfWeek(){
        return getDayOfWeek(new Date());
    }
    /**
     * 获取给定时间的年度天数
     * @param date              给定时间
     * @return int              天数
     */
    public static int getDayOfYear(Date date) {
        if(date == null) return 0;
        return getCalendar(date).get(Calendar.DAY_OF_YEAR);
    }
    /**
     * 获取当前时间的年度天数
     * @return int              天数
     */
    public static int getDayOfYear(){
        return getDayOfYear(new Date());
    }
    /**
     * 获取给定时间的小时数
     * @param date              给定时间
     * @return int              小时数
     */
    public static int getHour(Date date) {
        if(date == null) return 0;
        return getCalendar(date).get(Calendar.HOUR_OF_DAY);
    }
    /**
     * 获取当前时间的小时数
     * @return int              小时数
     */
    public static int getHour(){
        return getHour(new Date());
    }
    /**
     * 获取给定时间的分钟数
     * @param date              给定时间
     * @return int              分钟数
     */
    public static int getMinute(Date date) {
        if(date == null) return 0;
        return getCalendar(date).get(Calendar.MINUTE);
    }
    /**
     * 获取当前时间的分钟数
     * @return int              分钟数
     */
    public static int getMinute(){
        return getMinute(new Date());
    }
    /**
     * 获取给定时间的秒数
     * @param date              给定时间
     * @return int              秒数
     */
    public static int getSecond(Date date) {
        if(date == null) return 0;
        return getCalendar(date).get(Calendar.SECOND);
    }
    /**
     * 获取当前时间的秒数
     * @return int              秒数
     */
    public static int getSecond(){
        return getMinute(new Date());
    }

    /*==================================================
                        日期格式转化
     ==================================================*/
    /**
     * 字符串转为时间，由长到短逐一尝试进行
     * @param str               时间字符串
     * @return Date             时间
     */
    public static Date parse(String str) {
        if(str==null || str.trim().length()==0)	return null;
        Date ret;
        SimpleDateFormat df = TIME_ALL_FORMAT;
        try {
            ret = df.parse(str);
        }catch (Exception e) {
            df = TIME_SEC_FORMAT;
            try{
                ret = df.parse(str);
            }catch(Exception e1){
                df = TIME_MIN_FORMAT;
                try{
                    ret = df.parse(str);
                }catch(Exception e2){
                    df = TIME_DATE_FORMAT;
                    try{
                        ret = df.parse(str);
                    }catch(Exception e3){
                        e3.printStackTrace();
                        return null;
                    }
                }
            }
        }
        return ret;
    }
    /**
     * 字符串转为时间，精确到秒
     * @param str               时间字符串
     * @return Date             时间
     */
    public static Date parseSecond(String str) {
        if(str==null || str.trim().length()==0) return null;
        try {
            return TIME_SEC_FORMAT.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 字符串转为时间，精确到分
     * @param str               时间字符串
     * @return Date             时间
     */
    public static Date parseMinute(String str) {
        if(str==null || str.trim().length()==0) return null;
        try {
            return TIME_MIN_FORMAT.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 字符串转为时间，精确到天
     * @param str               时间字符串
     * @return Date             时间
     */
    public static Date parseDate(String str) {
        if(str==null || str.trim().length()==0)	return null;
        try {
            return TIME_DATE_FORMAT.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 字符串转为时间，自定义格式，默认为YYYY-MM-DD
     * @param str               时间字符串
     * @param pattern           自定义格式
     * @return Date             时间
     */
    public static Date parse(String str, String pattern) {
        if(str==null || str.trim().length()==0) return null;
        SimpleDateFormat df ;
        if (pattern == null || pattern.trim().length() == 0)
            df = TIME_DATE_FORMAT;
        else
            df = new SimpleDateFormat(pattern);
        try {
            return df.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 时间转为字符串，由长到短逐一尝试进行
     * @param date              时间
     * @return String           时间字符串
     */
    public static String format(Date date) {
        if(date==null) return null;
        SimpleDateFormat df = TIME_ALL_FORMAT;
        try {
            return df.format(date);
        } catch (Exception e) {
            df = TIME_SEC_FORMAT;
            try {
                return df.format(date);
            } catch (Exception e1) {
                df = TIME_MIN_FORMAT;
                try{
                    return df.format(date);
                }catch(Exception e2){
                    df = TIME_DATE_FORMAT;
                    try{
                        return df.format(date);
                    }catch(Exception e3){
                        e3.printStackTrace();
                        return null;
                    }
                }
            }
        }
    }
    /**
     * 时间转为字符串，精确到秒
     * @param date              时间
     * @return String           时间字符串
     */
    public static String formatSecond(Date date) {
        if(date == null) return null;
        try {
            return TIME_SEC_FORMAT.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 时间转为字符串，精确到分
     * @param date              时间
     * @return String           时间字符串
     */
    public static String formatMinute(Date date) {
        if(date == null) return null;
        try {
            return TIME_MIN_FORMAT.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 时间转为字符串，精确到天
     * @param date              时间
     * @return String           时间字符串
     */
    public static String formatDate(Date date) {
        if(date == null) return null;
        try {
            return TIME_DATE_FORMAT.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 时间转为字符串，自定义格式，默认为YYYY-MM-DD
     * @param date              时间
     * @param pattern           自定义格式
     * @return String           时间字符串
     */
    public static String format(Date date, String pattern) {
        if(date == null) return null;
        SimpleDateFormat df ;
        if (pattern == null || pattern.trim().length() == 0)
            df = TIME_DATE_FORMAT;
        else
            df = new SimpleDateFormat(pattern);
        try {
            return df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*==================================================
                        日期边界获取
     ==================================================*/
    /**
     * 获取给定日期的第一秒
     * @param date              时间
     * @return Date             时间
     */
    public static Date firstSecond(Date date){
        String shortDate = formatDate(date);
        return parse(shortDate + " 00:00:00", TIME_SEC_PATTERN);
    }
    /**
     * 获取给定日期的第一秒
     * @param dateString        时间字符串
     * @return Date             时间
     */
    public static Date firstSecond(String dateString){
        return firstSecond(parseDate(dateString));
    }
    /**
     * 获取给定日期的最后一秒
     * @param date              时间
     * @return Date             时间
     */
    public static Date lastSecond(Date date){
        String shortDate = formatDate(date);
        return parse(shortDate + " 23:59:59", TIME_SEC_PATTERN);
    }
    /**
     * 获取给定日期的最后一秒
     * @param dateString        时间字符串
     * @return Date             时间
     */
    public static Date lastSecond(String dateString){
        return lastSecond(parseDate(dateString));
    }
    /**
     * 获取给定日期的上午最后一秒
     * @param date              时间
     * @return Date             时间
     */
    public static Date amlastSecond(Date date){
        String shortDate = formatDate(date);
        return parse(shortDate + " 11:59:59", TIME_SEC_PATTERN);
    }
    /**
     * 获取给定日期的上午最后一秒
     * @param dateString        时间字符串
     * @return Date             时间
     */
    public static Date amlastSecond(String dateString){
        return amlastSecond(parseDate(dateString));
    }
    /**
     * 获取给定日期的下午第一秒
     * @param date              时间
     * @return Date             时间
     */
    public static Date pmfirstSecond(Date date){
        String shortDate = formatDate(date);
        return parse(shortDate + " 12:00:00", TIME_SEC_PATTERN);
    }
    /**
     * 获取给定日期的下午第一秒
     * @param dateString        时间字符串
     * @return Date             时间
     */
    public static Date pmfirstSecond(String dateString){
        return pmfirstSecond(parseDate(dateString));
    }

    /**
     * 获取指定年月的第一天
     * @param year              指定年
     * @param month             指定月
     * @return Date             时间
     */
    public static Date firstDayOfMonth(int year, int month){
        GregorianCalendar firstDay=new GregorianCalendar(new Integer(year).intValue(),new Integer(month).intValue()-1,1);
        return firstDay.getTime();
    }
    /**
     * 获取指定时间所在年月的第一天
     * @param date              指定时间
     * @return Date             时间
     */
    public static Date firstDayOfMonth(Date date){
        return firstDayOfMonth(getYear(date),getMonth(date));
    }
    /**
     * 获取指定年月的最后一天
     * @param year              指定年
     * @param month             指定月
     * @return Date             时间
     */
    public static Date lastDayOfMonth(int year, int month){
        GregorianCalendar lastDay=new GregorianCalendar(new Integer(year).intValue(),new Integer(month).intValue(),1);
        lastDay.add(GregorianCalendar.DATE, -1);
        return lastDay.getTime();
    }
    /**
     * 获取指定时间所在年月的最后一天
     * @param date              指定时间
     * @return Date             时间
     */
    public static Date lastDayOfMonth(Date date){
        return lastDayOfMonth(getYear(date),getMonth(date));
    }
    /**
     * 获取指定时间所在年月的第一秒
     * @param date              指定时间
     * @return Date             时间
     */
    public static Date firstSecondOfMonth(Date date){
        return firstSecond( firstDayOfMonth(getYear(date),getMonth(date)) );
    }
    /**
     * 获取指定时间所在年月的最后一秒
     * @param date              指定时间
     * @return Date             时间
     */
    public static Date lastSecondOfMonth(Date date){
        return lastSecond( lastDayOfMonth(getYear(date),getMonth(date)) );
    }
    /**
     * 获取指定时间所在周的第一秒（周一 00:00:00）
     * @param date              指定时间
     * @return Date             时间
     */
    public static Date firstSecondOfWeek(Date date){
        Calendar c = getCalendar(date);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if(day_of_week == 0) day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        return firstSecond(c.getTime());
    }
    /**
     * 获取指定时间所在周的最后一秒（周日 23:59:59）
     * @param date              指定时间
     * @return Date             时间
     */
    public static Date lastSecondOfWeek(Date date){
        Calendar c = getCalendar(date);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if(day_of_week == 0) day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        return lastSecond(c.getTime());
    }

    /*==================================================
                        日期步进计算
     ==================================================*/
    /**
     * 获取指定时间、指定天数之前的时间
     * @param date              指定时间
     * @param days              指定天数
     * @return Date             时间
     */
    public static Date getDateBeforeDays(Date date, int days) {
        Calendar cal = getCalendar(date);
        long millis = cal.getTimeInMillis() - days * MISEC_OF_DAY;
        cal.setTimeInMillis(millis);
        return cal.getTime();
    }
    /**
     * 获取指定时间、指定天数之前的时间
     * @param date              指定时间
     * @param days              指定天数
     * @return Date             时间
     */
    public static Date getDateBeforeDays(String date, int days) {
        Date cdate = parse(date);
        return getDateBeforeDays(cdate,days);
    }
    /**
     * 获取指定时间、指定天数之后的时间
     * @param date              指定时间
     * @param days              指定天数
     * @return Date             时间
     */
    public static Date getDateAfterDays(Date date, int days) {
        Calendar cal = getCalendar(date);
        long millis = cal.getTimeInMillis() + days * MISEC_OF_DAY;
        cal.setTimeInMillis(millis);
        return cal.getTime();
    }
    /**
     * 获取指定时间、指定天数之后的时间
     * @param date              指定时间
     * @param days              指定天数
     * @return Date             时间
     */
    public static Date getDateAfterDays(String date, int days) {
        Date cdate = parse(date);
        return getDateAfterDays(cdate,days);
    }
    /**
     * 获取指定时间、指定小时数之前的时间
     * @param date              指定时间
     * @param hours             指定小时数
     * @return Date             时间
     */
    public static Date getDateBeforeHours(Date date, int hours) {
        Calendar cal = getCalendar(date);
        long millis = cal.getTimeInMillis() - hours * MISEC_OF_HOUR;
        cal.setTimeInMillis(millis);
        return cal.getTime();
    }
    /**
     * 获取指定时间、指定小时数之前的时间
     * @param date              指定时间
     * @param hours             指定小时数
     * @return Date             时间
     */
    public static Date getDateBeforeHours(Date date, double hours) {
        Calendar cal = getCalendar(date);
        long millis = (long) (cal.getTimeInMillis() - hours * MISEC_OF_HOUR);
        cal.setTimeInMillis(millis);
        return cal.getTime();
    }
    /**
     * 获取指定时间、指定小时数之后的时间
     * @param date              指定时间
     * @param hours             指定小时数
     * @return Date             时间
     */
    public static Date getDateAfterHours(Date date, int hours) {
        Calendar cal = getCalendar(date);
        long millis = cal.getTimeInMillis() + hours * MISEC_OF_HOUR;
        cal.setTimeInMillis(millis);
        return cal.getTime();
    }
    /**
     * 获取指定时间、指定小时数之后的时间
     * @param date              指定时间
     * @param hours             指定小时数
     * @return Date             时间
     */
    public static Date getDateAfterHours(Date date, double hours) {
        Calendar cal = getCalendar(date);
        long millis = (long) (cal.getTimeInMillis() + hours * MISEC_OF_HOUR);
        cal.setTimeInMillis(millis);
        return cal.getTime();
    }
    /**
     * 获取指定时间、指定年数之前的时间
     * @param date              指定时间
     * @param years             指定年数
     * @return Date             时间
     */
    public static Date getDateBeforeYears(Date date, int years) {
        Calendar cal = getCalendar(date);
        cal.add(Calendar.YEAR,0-years);
        return cal.getTime();
    }
    /**
     * 获取指定时间、指定年数之后的时间
     * @param date              指定时间
     * @param years             指定年数
     * @return Date             时间
     */
    public static Date getDateAfterYears(Date date, int years) {
        Calendar cal = getCalendar(date);
        cal.add(Calendar.YEAR,years);
        return cal.getTime();
    }
    /**
     * 获取指定时间、指定月数之前的时间
     * @param date              指定时间
     * @param months            指定月数
     * @return Date             时间
     */
    public static Date getDateBeforeMonths(Date date, int months) {
        Calendar cal = getCalendar(date);
        cal.add(Calendar.MONTH,0-months);
        return cal.getTime();
    }
    /**
     * 获取指定时间、指定月数之后的时间
     * @param date              指定时间
     * @param months            指定月数
     * @return Date             时间
     */
    public static Date getDateAfterMonths(Date date, int months) {
        Calendar cal = getCalendar(date);
        cal.add(Calendar.MONTH,months);
        return cal.getTime();
    }

    /*==================================================
                        日期差值计算
     ==================================================*/
    /**
     * 获取两个时间的毫秒差，date1-date2
     * @param date1             开始时间
     * @param date2             结束时间
     * @return long             差值
     */
    public static long getDiffMillis(Date date1, Date date2){
        if (date1!=null && date2!=null) {
            return date1.getTime()-date2.getTime();
        }
        return 0;
    }
    /**
     * 获取两个时间的小时差，date1-date2
     * @param date1             开始时间
     * @param date2             结束时间
     * @return long             差值
     */
    public static long getDiffHours(Date date1, Date date2){
        long diff = getDiffMillis(date1,date2);
        return diff/MISEC_OF_HOUR;
    }
    /**
     * 获取两个时间的日期差，date1-date2
     * @param date1             开始时间
     * @param date2             结束时间
     * @return long             差值
     */
    public static long getDiffDays(Date date1, Date date2){
        long diff = getDiffMillis(date1,date2);
        return diff/MISEC_OF_DAY;
    }
    /**
     * 获取两个时间的月份差，date1-date2
     * @param date1             开始时间
     * @param date2             结束时间
     * @return int              差值
     */
    public static int getDiffMonths(Date date1,Date date2){
        if (date1!=null && date2!=null) {
            int month1 = getYear(date1)*12 + getMonth(date1);
            int month2 = getYear(date2)*12 + getMonth(date2);
            return month1-month2;
        }
        return 0;
    }
    /**
     * 获取两个时间的年份差，date1-date2
     * @param date1             开始时间
     * @param date2             结束时间
     * @return int              差值
     */
    public static int getDiffYears(Date date1,Date date2){
        if (date1!=null && date2!=null) {
            int year1 = getYear(date1);
            int year2 = getYear(date2);
            return year1 - year2;
        }
        return 0;
    }

    /*==================================================
                        日期其他方法
     ==================================================*/
    /**
     * 获取年度列表
     * @param startAmount 	    起点
     * @param endAmount		    终点
     * @return list             年度列表
     */
    public static List<Integer> listYears(int startAmount, int endAmount){
        Calendar ec = Calendar.getInstance();
        int thisYear = ec.get(Calendar.YEAR);
        List<Integer> years = new ArrayList<>();
        if(startAmount < endAmount){
            for(int i=startAmount; i<=endAmount; i++)	years.add(thisYear + i);
        }else{
            for(int i=startAmount; i>=endAmount; i--)	years.add(thisYear + i);
        }
        return years;
    }
    /**
     * 判断两个时间之差 是否大于指定的小时数
     * @param begin             开始时间
     * @param end               结束时间
     * @param hours             指定的小时数
     * @return boolean          是否
     */
    public static boolean isAboveHours(Date begin, Date end, double hours) {
        long milliseconds1 = begin.getTime();
        long milliseconds2 = end.getTime();
        long aw = milliseconds1 - milliseconds2;
        return (aw >= (MISEC_OF_HOUR*hours));
    }


    public static void main(String[] args) {
        System.out.println(DateUtil.format(new Date()));
        System.out.println(DateUtil.parse("2017-07-24"));
        System.out.println(DateUtil.listYears(-1,1));

    }

}
