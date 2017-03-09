package com.ffzx.ffsip.util;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
 
/**
* Copyright (c) 2011 sxgkwei
* Author:sxgkwei
* Email:sxgkwei@gmail.com
* Date: 2012-03-30 13:01:21
* JDK: 1.6.2
*/
public class DateUtil extends org.apache.commons.lang3.time.DateUtils {
 
    /**
     * 默认的日期格式化样式
     */
    public static final String DAY_PATTERN = "yyyy-MM-dd";
    /**
     * 默认的时间格式化样式
     */
    public static final String TIME_PATTERN = "HH:mm:ss";
    
    /**
     * 默认的时间格式化样式
     */
    public static final String TIME_PATTERN_MINUTE = "HH:mm";
 
    /**
     * 将Date格式化成符合默认格式的字符串
     * 
     * @param date
     * @return 返回样例:2012-03-29 14:32:23
     */
    public static String format(Date date) {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(DAY_PATTERN + " " + TIME_PATTERN);
        return formatTool.format(date);
    }
 
    /**
     * 将Date格式化成符合默认日期格式的字符串
     * 
     * @param date
     * @return 返回样例:2012-03-29
     */
    public static String formatDate(Date date) {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(DAY_PATTERN);
        return formatTool.format(date);
    }
 
    /**
     * 将Date格式化成符合默认时间格式的字符串
     * 
     * @param date
     * @return 返回样例:14:32:23
     */
    public static String formatTime(Date date) {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(TIME_PATTERN);
        return formatTool.format(date);
    }
    
    /**
     * 将Date格式化成符合默认时间格式的字符串
     * 
     * @param date 时分
     * @return 返回样例:14:32
     */
    public static String formatTimeHourAndMinute(Date date) {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(TIME_PATTERN_MINUTE);
        return formatTool.format(date);
    }
 
    /**
     * 按照pattern格式格式化Date
     * 
     * @param date
     * @param pattern
     *            样例: yyyy/MM/dd
     * @return 返回样例:2012/03/29
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(pattern);
        return formatTool.format(date);
    }
 
    /**
     * 将符合默认格式的字符串转换成Date
     * 
     * @param dateValue
     *            样例:2012-03-29 14:32:23
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateValue) throws ParseException {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(DAY_PATTERN + " " + TIME_PATTERN);
        return formatTool.parse(dateValue);
    }
 
    /**
     * 将符合默认格式的日期字符串转换成Date
     * 
     * @param dateValue
     *            样例:2012-03-29
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateValue) throws ParseException {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(DAY_PATTERN);
        return formatTool.parse(dateValue);
    }
 
    /**
     * 将符合pattern格式的dateValue转换成Date
     * 
     * @param dateValue
     *            样例:2012/03/29
     * @param pattern
     *            样例:yyyy/MM/dd
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateValue, String pattern)
            throws ParseException {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(pattern);
        return formatTool.parse(dateValue);
    }
 
    /**
     * 返回这一天的最早的时候
     * 
     * @param date
     * @return
     */
    public static Date getEarliest(Date date) {
        return parseTime(date, 0, 0, 0, 0);
    }
 
    /**
     * 返回这一天的最晚时候
     * 
     * @param date
     * @return
     */
    public static Date getLastest(Date date) {
        return parseTime(date, 23, 59, 59, 999);
    }
 
    /**
     * 得到指定月的天数
     * 
     * @param year
     * @param month
     * @return
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
 
    /**
     * 返回指定时间加上num天后的时间
     * 
     * @param date
     * @param num
     * @return
     */
    public static Date add(Date date, int num) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.add(Calendar.DAY_OF_MONTH, num);
        return a.getTime();
    }
 
    /**
     * 返回false：date=null，date是1970年；其它都返回true
     * 
     * @param date
     * @return
     */
    public static boolean isNotEmpty(Date date) {
        if (date != null) {
            Calendar a = Calendar.getInstance();
            a.setTime(date);
            return a.get(Calendar.YEAR) != 1970;
        }
        return Boolean.FALSE;
    }
 
    /**
     * 获得date的小时数0-23
     * 
     * @param date
     * @return
     */
    public static int getHours(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        return a.get(Calendar.HOUR_OF_DAY);
    }
 
    /**
     * 获得date是一周的第几天，注意：周日 返回 1，周六 返回 7
     * 
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        int i = a.get(Calendar.DAY_OF_WEEK)-1;
        return i;
    }
 
    /**
     * 设定date的时间细节
     * 
     * @param date
     *            要设定时间细节的date
     * @param hourOfDay
     *            0-23
     * @param minute
     *            0-59
     * @param second
     *            0-59
     * @param milliSecond
     *            0-999
     * @return
     */
    public static Date parseTime(Date date, int hourOfDay, int minute,
            int second, int milliSecond) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        setCalendarTime(cal, hourOfDay, minute, second, milliSecond);
        return cal.getTime();
    }
 
    /**
     * 设定date的时间细节
     * 
     * @param date
     *            要设定时间细节的date
     * @param timeDetail
     *            以:号分隔的24小时制的时间，例:16:23:42:267 或 16(等同于16:00:00:000)
     * @return
     */
    public static Date parseTime(Date date, String timeDetail) {
 
        List<String> strList = new ArrayList<String>();
        strList.addAll(Arrays.asList(timeDetail.split(":")));
 
        while (strList.size() < 4) {
            strList.add("0");
        }
        return parseTime(date, Integer.parseInt(strList.get(0)),
                Integer.parseInt(strList.get(1)),
                Integer.parseInt(strList.get(2)),
                Integer.parseInt(strList.get(3)));
    }
 
    /**
     * 指定时间 是否在 当前时间 之后，注：和日期无关
     * 
     * @param time
     *            指定的时间， 传入样例:16:23:42:267 或 16(等同于16:00:00:000)
     * @return
     */
    public static boolean isAfterTime(String time) {
        Date date = parseTime(new Date(), time);
        return date.after(new Date());
    }
 
    /**
     * 指定时间 是否在 当前时间 之前，注：和日期无关
     * 
     * @param time
     *            指定的时间， 传入样例:16:23:42:267 或 16(等同于16:00:00:000)
     * @return
     */
    public static boolean isBeforeTime(String time) {
        Date date = parseTime(new Date(), time);
        return date.before(new Date());
    }
 
    private static void setCalendarTime(Calendar cal, int hourOfDay,
            int minute, int second, int milliSecond) {
        cal.set(HOUR_OF_DAY, hourOfDay);
        cal.set(MINUTE, minute);
        cal.set(SECOND, second);
        cal.set(MILLISECOND, milliSecond);
    }
    
    /**
     * 根据两个日期算出间隔天数
     * @param startDate 启动开始时间
     * @param endDate  结束时间
     * @return
     */
    public static int getDateNum(String startDate,String endDate){
    	 try {
            //时间转换类
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(startDate);
            Date date2 = sdf.parse(endDate); //2012-1-20
            //将转换的两个时间对象转换成Calendard对象
            Calendar can1 = Calendar.getInstance();
            can1.setTime(date1);
            Calendar can2 = Calendar.getInstance();
            can2.setTime(date2);
            //拿出两个年份
            int year1 = can1.get(Calendar.YEAR);
            int year2 = can2.get(Calendar.YEAR);
            //天数
            int days = 0;
            Calendar can = null;
            //如果can1 < can2
            //减去小的时间在这一年已经过了的天数
            //加上大的时间已过的天数
            if(can1.before(can2)){
                days -= can1.get(Calendar.DAY_OF_YEAR);
                days += can2.get(Calendar.DAY_OF_YEAR);
                can = can1;
            }else{
                days -= can2.get(Calendar.DAY_OF_YEAR);
                days += can1.get(Calendar.DAY_OF_YEAR);
                can = can2;
            }
            for (int i = 0; i < Math.abs(year2-year1); i++) {
                //获取小的时间当前年的总天数
                days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
                //再计算下一年。
                can.add(Calendar.YEAR, 1);
            }
            return days;
        } catch (Exception e) {
            e.printStackTrace();
        }
    	 return 0;
    }
    
    public static String getDateAddOne(String dateStr){
    	try {
	    	Date date=parse(dateStr, "yyyy-MM-dd");
	        Date d1=add(date, 1);
	        dateStr=DateUtil.formatDate(d1);
	        return dateStr;
    	} catch (Exception e) {
            e.printStackTrace();
        }
    	return null;
    }
    
    public static String longTurnDate(long longTime)throws ParseException{
    	Date dt2 =new Date(longTime * 1000);
    	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String sDateTime = sdf.format(dt2); 
    	return sDateTime;
    }
    
    public static long dateTurnLong(String date) throws ParseException{
    	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date dt2 = sdf.parse(date);
    	long lTime = dt2.getTime() / 1000;
    	return lTime;
    }
}