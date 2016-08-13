package com.ervin.android.rxervin.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Kevin on 2015/7/20.
 */
public class  TimeHelper {

    private static final String TAG = TimeHelper.class.getSimpleName();

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyy.MM.dd";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String HH_MM = "HH:mm";
    public static final String HH_MM_SS = "mm:ss";
    /**
     * 将毫秒时间格式化成 yyyy-MM-dd HH:mm:ss
     *
     * @param miles
     * @return
     */
    public static String millisToYYYYMMddHHmmss(long miles) {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS, Locale.getDefault());

        return format.format(miles);
    }


    /**
     * 将毫秒时间格式化成 yyyy-MM-dd HH:mm
     *
     * @param miles
     * @return
     */
    public static String millisToYYYYMMddHHmm(long miles) {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM, Locale.getDefault());
        return format.format(miles);
    }


    /**
     * 将毫秒时间格式化成 yyyy-MM-dd
     *
     * @param miles
     * @return
     */
    public static String millisToYYYYMMdd(long miles) {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD, Locale.getDefault());

        return format.format(miles);
    }
    /**
     * 将毫秒时间格式化成 yyyy.MM.dd
     *
     * @param miles
     * @return
     */
    public static String millisToYYYYMMddWithPoint(long miles) {
        SimpleDateFormat format = new SimpleDateFormat(YYYYMMDD, Locale.getDefault());

        return format.format(miles);
    }
    public static String millisToHHmm(long millis) {
        SimpleDateFormat format = new SimpleDateFormat(HH_MM);
        return format.format(millis);
    }
    public static String millisToHHmmss(long millis) {
        SimpleDateFormat format = new SimpleDateFormat(HH_MM_SS, Locale.UK);
        return format.format(millis);
    }

    /**
     * 将毫秒时间格式化成 yyyy-MM
     *
     * @param miles
     * @return
     */
    public static String millisToYYYYMM(long miles) {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM, Locale.getDefault());

        return format.format(miles);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss 格式的时间转化成毫秒
     *
     * @param time
     * @return
     */
    public static long yyyyMMddHHmmssToMillis(String time) {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS, Locale.getDefault());
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将yyyy-MM-dd HH:mm 格式的时间转化成毫秒
     *
     * @param time
     * @return
     */
    public static long yyyyMMddHHmmToMillis(String time) {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM, Locale.getDefault());
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将yyyy-MM-dd 格式的时间转化成毫秒
     *
     * @param time
     * @return
     */
    public static long yyyyMMddToMillis(String time) {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD, Locale.getDefault());
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static long hhmmToMillis(String time) {
        SimpleDateFormat format = new SimpleDateFormat(HH_MM, Locale.getDefault());
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取当天是一周中的第几天.第一天为周日
     * @return
     */
    public static int getWeekDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1)
            day = 7;
        else {
            day = day - 1;
        }
        return day;
    }

    /**
     * 获取当前周周日的日期，即一周的开始日期，格式yyyy-MM-dd
     * @return
     */
    public static String getWeekStartDate(){
        Calendar c = Calendar.getInstance();
        int indexDay = c.get(Calendar.DAY_OF_WEEK)-1;
        long startTimeMiles = System.currentTimeMillis()-indexDay * 24 * 60 * 60 * 1000;
        String startTime = millisToYYYYMMdd(startTimeMiles);
        return startTime;
    }



    public static long getWeekStartTimeMillis(){
        Calendar c = Calendar.getInstance();
        int indexDay = c.get(Calendar.DAY_OF_WEEK)-1;
        long startTimeMiles = System.currentTimeMillis()/1000/60/60*1000*60*60-indexDay * 24 * 60 * 60 * 1000;
        String startTime = millisToYYYYMMdd(startTimeMiles);
        long millis = yyyyMMddToMillis(startTime);
        Log.d(TAG,"sunday millis:" + millis + " sunday:" + startTime);
        return millis;
    }

    public static long getSevenDaysAgoTimeMillis(){
        long startTimeMiles = System.currentTimeMillis()/1000/60/60*1000*60*60- 7 * 24 * 60 * 60 * 1000;
        String startTime = millisToYYYYMMdd(startTimeMiles);
        long millis = yyyyMMddToMillis(startTime);
        Log.d(TAG,"sunday millis:" + millis + " sunday:" + startTime);
        return millis;
    }

    //获取当前月的第一天
    public static String getFirstDayOfMonth(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,1);
        String time = TimeHelper.millisToYYYYMMdd(cal.getTimeInMillis());
        return time;
    }

    /**
     * 获取今天的日期,格式yyyy-MM-dd
     * @return
     */
    public static String getToday(){
        return TimeHelper.millisToYYYYMMdd(System.currentTimeMillis());
    }

    public static String getYesterday(){
        return TimeHelper.millisToYYYYMMdd(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
    }

    //计算两个时间差 返回值：分钟
    public static int getTimeDiffmm(long beginL,long endL){
        try {
            Long day = (endL - beginL) / 86400000;
            Long hour = ((endL - beginL) % 86400000) / 3600000;
            Long min = ((endL - beginL) % 86400000 % 3600000) / 60000;
            return (int)(day*24*60 + hour*60 + min);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
}
