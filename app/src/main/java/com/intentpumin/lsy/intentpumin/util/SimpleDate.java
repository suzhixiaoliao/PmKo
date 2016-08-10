package com.intentpumin.lsy.intentpumin.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * Created by yang on 2016/8/9.
 */
public class SimpleDate {
    private static String defaultFormat = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sf = null ;
    private static Calendar cal = Calendar.getInstance();
    private static Date date = null;

    /**
     * get current date time (default 'yyyy-MM-dd HH:mm:ss')
     * @return string
     */
    public static String getDateNow() {
        sf = new SimpleDateFormat(defaultFormat);
        String currentTime = "";
        currentTime = sf.format(new Date());
        return currentTime;
    }

    /**
     * get year (default now)
     * @return int
     */
    public static int getYear(){
        int currentYear = cal.get(Calendar.YEAR);
        return currentYear;
    }

    /**
     * get mon (default now)
     * @return int
     */
    public static int getMonth(){
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        return currentMonth;
    }

    /**
     * get day of month (default now)
     * @return int
     */
    public static int getDay(){
        int currentDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return currentDayOfMonth;
    }

    /**
     * get hours (default now)
     * @return int
     */
    public static int getHours(){
        int currentHours = cal.get(Calendar.HOUR_OF_DAY);
        return currentHours;
    }

    /**
     * get  minutes (default now)
     * @return int
     */
    public static int getMinutes(){
        int currentMinute = cal.get(Calendar.MINUTE);
        return currentMinute;
    }

    /**
     * get seconds (default now)
     * @return int
     */
    public static int getSeconds(){
        int currentSecond = cal.get(Calendar.SECOND);
        return currentSecond;
    }

    /**
     * string change to date
     * @param strDate
     * @param dateFormat
     * @return date
     */
    public static Date toDate(String strDate, String dateFormat){
        if(strDate == null || strDate.length() == 0){
            return null;
        }
        Date date = null;
        DateFormat df = new SimpleDateFormat(dateFormat);
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static long getMillisOfDate(Date date) {
        cal.setTime(date);
        return cal.getTimeInMillis();
    }



    /**
     * compare two date
     * return the greater date
     * if equals return null
     * @param strStartDate
     * @param strEndDate
     * @return date
     */
    public static Date getGreaterDate(String strStartDate, String strEndDate){
        Date date = null;
        Date startDate = toDate(strStartDate, "yyyy-MM-dd");
        Date endDate = toDate(strEndDate, "yyyy-MM-dd");
        long startTime = getMillisOfDate(startDate);
        long endTime = getMillisOfDate(endDate);
        if((startTime - endTime) > 0){
            return startDate;
        }else if((endTime - startTime) > 0){
            return endDate;
        }
        return date;
    }

    /**
     * get days between 2 different date
     * @param strStartDate less date (yyyy-MM-dd)
     * @param strEndDate greater date (yyyy-MM-dd)
     * @return long
     */
    public static long getDaysOftwoDiffDate(String strStartDate, String strEndDate){
        Date startDate = toDate(strStartDate, "yyyy-MM-dd");
        Date endDate = toDate(strEndDate, "yyyy-MM-dd");
        long startTime = getMillisOfDate(startDate);
        long endTime = getMillisOfDate(endDate);
        long betweenDays = (long) ((endTime - startTime) / ( 1000 * 60 * 60 * 24 ));
        return betweenDays;
    }

    /**
     * get weeks between 2 different date
     * @param strStartDate less date (yyyy-MM-dd)
     * @param strEndDate greater date (yyyy-MM-dd)
     * @return long
     */
    public static long getWeeksOfTwoDiffDate(String strStartDate, String strEndDate){
        return getDaysOftwoDiffDate(strStartDate, strEndDate) / 7;
    }

    /**
     * get months between 2 different date
     * @param strStartDate less date (yyyy-MM-dd)
     * @param strEndDate greater date (yyyy-MM-dd)
     * @return long
     */
    public static long getMonthsOfTwoDiffDate(String strStartDate, String strEndDate){
        return getDaysOftwoDiffDate(strStartDate, strEndDate) / 30;
    }

    /**
     * get years between 2 different date
     * @param strStartDate less date (yyyy-MM-dd)
     * @param strEndDate greater date (yyyy-MM-dd)
     * @return long
     */
    public static long getYearsOfTwoDiffDate(String strStartDate, String strEndDate){
        return getDaysOftwoDiffDate(strStartDate, strEndDate) / 365;
    }

    /**
     * add date
     * @param date
     * @param count
     * @param field Calendar.YEAR(MONTH ..)
     * @param format DateFormat(yyyy-MM-dd)
     * @return string
     */
    public static String addDate(Date date,int count,int field,String format){
        cal.setTime(date);
        int year = getYear();
        int month = getMonth() - 1;
        int day = getDay();
        int hours = getHours();
        int minutes = getMinutes();
        int seconds = getSeconds();
        Calendar calendar = new GregorianCalendar(year, month, day, hours, minutes, seconds);
        calendar.add(field,count);
        DateFormat df = new SimpleDateFormat(format);
        String tmpDate = df.format(calendar.getTime());
        cal.setTime(new Date());
        return tmpDate;
    }

    /**
     * calendar settime
     * @param date
     */
    private static void setCalTime(Date date){
        if(date != null){
            cal.setTime(date);
        }
    }

    //setter getter

    public static String getDefaultFormat() {
        return defaultFormat;
    }

    public static void setDefaultFormat(String defaultFormat) {
        SimpleDate.defaultFormat = defaultFormat;
    }

    public static Date getDate() {
        return date;
    }

    public static void setDate(Date date) {
        SimpleDate.date = date;
        setCalTime(date);
    }

}
