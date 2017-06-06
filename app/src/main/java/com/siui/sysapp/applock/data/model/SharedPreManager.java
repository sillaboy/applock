package com.siui.sysapp.applock.data.model;

import android.content.Context;

import com.siui.sysapp.applock.AppLockApplication;

/**
 * Created by yunlong.zhang on 2017/6/6.
 */
public class SharedPreManager {

    private static final String CONFIG_NAME = "applock";

    private static Context mContext = AppLockApplication.getInstance();

    public static boolean getEnable(String key, boolean defvalue) {
        return mContext.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE)
                .getBoolean(key, defvalue);
    }

    public static String getKeyWord(String key, String defvalue) {
        return mContext.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE)
                .getString(key, defvalue);
    }

    public static void setKeyword(String key, String value) {
        mContext.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE).edit()
                .putString(key, value).commit();
    }

    public static void setEnable(String key, boolean enable) {
        mContext.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE).edit()
                .putBoolean(key, enable).commit();
    }

}
