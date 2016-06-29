package com.intentpumin.lsy.intentpumin;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.location.LocationService;
import com.intentpumin.lsy.intentpumin.network.CrashHandler;


/**
 * Created by Eenie on 2016/4/23.
 */
public class DemoApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        HttpUtil.init(this);
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

}
