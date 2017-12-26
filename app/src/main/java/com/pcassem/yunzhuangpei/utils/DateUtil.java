package com.pcassem.yunzhuangpei.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {

    public static String TAG = "DateUtil";

    public static String getStandardDate(long timeStamp) {
        String temp = formatDate(timeStamp);
        return temp;
    }

    //时间戳转换
    public static String formatDate(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        return sd;
    }
}
