package com.intentpumin.lsy.intentpumin.ui;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
        AppContextUtil.init(this);
        L.init();
    }

    // 获取ApplicationContext
    public static Context getContext() {
        return mApplicationContext;
    }
}
