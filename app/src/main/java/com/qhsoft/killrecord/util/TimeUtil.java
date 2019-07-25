package com.qhsoft.killrecord.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Description:
 * Author:lin
 * Date:2017-09-07
 */

public class TimeUtil {

    public static String formatSimpleDate(long time) {
        if(time>0){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(time);
        }
        return "";

    }

    public static String formatSimpleDate(String time) {
        if(time==null||"".equals(time)||"0".equals(time)){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(Long.valueOf(time));
    }
    public static String getDateStr(String time) {
        if(time==null||"".equals(time)||"0".equals(time)||time.length()<10){
            return "";
        }

        return time.substring(0,10);
    }



    /**
     * 计算有效期至
     *
     * @param time
     *            生产日期
     * @param type
     *            保质期类型
     * @param interval
     *            保质期
     * @return 有效期至
     */
    public static String getPeriodDate(String time, String type, int interval) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(sdf.parse(time));
            if (type.equals("年")) {
                int year = calendar.get(Calendar.YEAR);
                calendar.set(Calendar.YEAR, year + interval);

            } else if (type.equals("月")) {
                int month = calendar.get(Calendar.MONTH);
                calendar.set(Calendar.MONTH, month + interval);

            } else if (type.equals("日") || type.equals("天")) {
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.set(Calendar.DAY_OF_MONTH, day + interval);

            }

            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";

    }








}
