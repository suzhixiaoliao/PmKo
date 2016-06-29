package com.intentpumin.lsy.intentpumin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.network.LogUtils;
import com.intentpumin.lsy.intentpumin.tools.login;
import com.intentpumin.lsy.intentpumin.zxing.CaptureActivity;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class MainActivity extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


    }

}