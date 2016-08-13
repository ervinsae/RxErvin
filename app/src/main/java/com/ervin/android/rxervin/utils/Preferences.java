package com.ervin.android.rxervin.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by think on 2016/4/20.
 */
public class Preferences {

    private static SharedPreferences preferences;

    private static final String PREFERENCE_NAME = "preference_medical";

    private Preferences(){
        throw new AssertionError();
    }

    private static void initSharedPreferences(Context context){
        if (preferences == null) {
            preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
    }

    public static void putString(Context context, String key, String values) {
        initSharedPreferences(context);
        preferences.edit().putString(key, values).commit();
    }

    public static String getString(Context context, String key) {
        initSharedPreferences(context);
        return preferences.getString(key, "");
    }
    public static String getString(Context context, String key, String defaultValue) {
        initSharedPreferences(context);
        return preferences.getString(key, defaultValue);
    }

    public static void putInt(Context context, String key, int values) {
        initSharedPreferences(context);
        preferences.edit().putInt(key, values).commit();
    }

    public static int getInt(Context context, String key, int defaultValues) {
        initSharedPreferences(context);
        return preferences.getInt(key, defaultValues);
    }

    public static void putFloat(Context context, String key, float values) {
        initSharedPreferences(context);
        preferences.edit().putFloat(key, values).commit();
    }

    public static float getFloat(Context context, String key, float defaultValues) {
        initSharedPreferences(context);
        return preferences.getFloat(key, defaultValues);
    }

    public static void putBoolean(Context context, String key, boolean values) {
        initSharedPreferences(context);
        preferences.edit().putBoolean(key, values).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValues) {
        initSharedPreferences(context);
        return preferences.getBoolean(key, defaultValues);
    }

    public static void putLong(Context context, String key, long values) {
        initSharedPreferences(context);
        preferences.edit().putLong(key, values).commit();
    }

    public static long getLong(Context context, String key, long defaultValues) {
        initSharedPreferences(context);
        return preferences.getLong(key, defaultValues);
    }

    public static void remove(Context context, String key) {
        initSharedPreferences(context);
        preferences.edit().remove(key).commit();
    }
}
