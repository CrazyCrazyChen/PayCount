package com.example.paycount;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    //时间转换工具类


    public static String getFormatedTime(long timeStamp){
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
        return formater.format(new Date(timeStamp));
    }



    public static String getFormatedDate(){
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        return formater.format(new Date());
    }

    private static Date strToDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String getWeekDay(String date){
        String[] weekdays = {"7","1","2","3","4","5","6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDate(date));
        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekdays[index];


    }

    public static String getDateTitle(String date){


        String[] months = {"mm","mm","mm","mm","mm","mm","mm","mm","mm","mm","mm","mm",};

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDate(date));
        int manthIndwx = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return months[manthIndwx]+" "+String.valueOf(day);



    }

}
