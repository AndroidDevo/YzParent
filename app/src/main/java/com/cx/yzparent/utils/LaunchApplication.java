package com.cx.yzparent.utils;

import android.app.Application;

/**
 * Created by cj on 2018/1/10
 */

public class LaunchApplication extends Application {
    private static LaunchApplication mApplication;

    public static LaunchApplication getInstance(){
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
