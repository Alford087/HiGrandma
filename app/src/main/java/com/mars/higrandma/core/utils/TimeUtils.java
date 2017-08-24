package com.mars.higrandma.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间操作类
 * Created by tc on 2016/2/17.
 */
public class TimeUtils {

    public static final String FORMAT_STYLE_B_2 = "MM-dd HH:mm";
    public static final String FORMAT_STYLE_B_3 = "HH:mm";

    public static long getMillis(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        return sdf.parse(date).getTime();//毫秒
    }

    public static String convertDate(long millis, String format) {
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }

    public static String getFriendlyTime(long millis) {
        long nowSeconds = System.currentTimeMillis();
        long distance = nowSeconds - millis;
        if (distance < 60 * 60 * 24 * 1000) {
            return convertDate(millis, TimeUtils.FORMAT_STYLE_B_3);
        } else if (distance < 60 * 60 * 24 * 1000 * 365) {
            return convertDate(millis, TimeUtils.FORMAT_STYLE_B_2);
        } else {
            return convertDate(millis, TimeUtils.FORMAT_STYLE_B_2);
        }
    }
}
