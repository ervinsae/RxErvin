package com.ervin.android.rxervin.utils;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by think on 2016/4/29.
 */
public class Tools {
    private static long lastClickTime;
    public static String filterString(String str) {
        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(str);
        return matcher.replaceAll("").trim();
    }

    public static float formatDecimalKeepThree(float src){
        return (float)(Math.round(src *1000))/1000;
    }

    public static float formatDecimalKeepOne(float src){
        return (float)(Math.round(src *10))/10;
    }

    public static float formatDecimalKeepTwo(float src){
        return (float)(Math.round(src *100))/100;
    }

    public static String getCurrentMonth(){
        String time = "";
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        if(currentMonth < 10){
            time = "0" + currentMonth;
        }
        return time;
    }

    //防止按钮快速被点击
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 600) {
            lastClickTime = time;
            return true;
        }
        lastClickTime = time;
        return false;
    }

    //验证密码是否包含非法字符
    public static boolean validatePwd(String pwd){
        String reg = "[0-9a-zA-Z]{5,15}";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(pwd);
        return m.matches();
    }
}
