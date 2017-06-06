package com.siui.sysapp.applock;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.siui.sysapp.applock.activities.BaseActivity;

/**
 * Created by yunlong.zhang on 2017/6/4.
 */
public class AppLockApplication extends Application {

    private static AppLockApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getInstance() {
        return mContext;
    }


    public void goHome(BaseActivity activity) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        activity.startActivity(homeIntent);
        activity.finish();
    }
}
