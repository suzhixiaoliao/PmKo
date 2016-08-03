package com.intentpumin.lsy.intentpumin.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import com.intentpumin.lsy.intentpumin.DemoApplication;


/**
 * Created by Seven1979 on 2015/10/14.
 */
public class DeviceInfo {

    public static boolean isNetAvailable() {
        ConnectivityManager manager = (ConnectivityManager) DemoApplication.sInstance.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isAvailable());
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }
}
