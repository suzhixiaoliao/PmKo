package com.intentpumin.lsy.intentpumin;

import android.app.Application;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.network.CrashHandler;
import com.intentpumin.lsy.intentpumin.proxy.YiDialogProxy;

import in.srain.cube.Cube;
import in.srain.cube.request.RequestCacheManager;

public class DemoApplication extends Application {

    public static DemoApplication sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        HttpUtil.init(this);
        sInstance =this;
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        initDialog();
        RequestCacheManager.init(this, "request-cache", 1024 * 10, 1024 * 10);
        Cube.onCreate(this);
        //BaiduLocation.onCreate(this);
    }

    private void initDialog() {
        YiDialogProxy.setMsgDialogLayoutRes(R.layout.yi_dialog_template);
        YiDialogProxy.setMsgDialogTheme(R.style.Custom_Dialog_Dim);
        YiDialogProxy.setProgressDialogLayoutRes(R.layout.yi_progress_dialog_template);
        YiDialogProxy.setProgressDialogTheme(R.style.Custom_Dialog_Dim);
    }
}
