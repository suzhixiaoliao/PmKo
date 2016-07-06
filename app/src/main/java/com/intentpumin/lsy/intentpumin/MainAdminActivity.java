package com.intentpumin.lsy.intentpumin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.network.LogUtils;
import com.intentpumin.lsy.intentpumin.tools.login;
import com.intentpumin.lsy.intentpumin.zxing.CaptureActivity;
import com.pumin.lzl.pumin.MainActivity2;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class MainAdminActivity extends AppCompatActivity {
    private TextView tv_main;
    private ImageView iv_ss;
    private TextView tv_return;
    private ImageView iv_scan;
    // String url = "http://app.pumintech.com:40000/api/user/logout";
    String url = "http://10.16.1.201:40000/api/user/logout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_main = (TextView) findViewById(R.id.tv_main);
        //第四步
        iv_scan = (ImageView) findViewById(R.id.iv_scan);
        iv_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent   it = new Intent(MainAdminActivity.this, CaptureActivity.class);
                it.putExtra("str_all","1");
                startActivity(it);
            }
        });
        login mlogin = (login) getIntent().getSerializableExtra("login");
        tv_main.setText(mlogin.getName() + ",您好");
        iv_ss = (ImageView) findViewById(R.id.iv_ss);
        iv_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainAdminActivity.this, HelloChartActivity.class);
                startActivity(i);
            }
        });
        tv_return = (TextView) findViewById(R.id.tv_return);
        tv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams();
                params.addFormDataPart("signature", "1");
                HttpUtil.getInstance().post(MainLogic.LOGOUT, params, new StringHttpRequestCallback() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    protected void onSuccess(String s) {

                        System.out.println(s);
                        Intent i = new Intent(MainAdminActivity.this, LoginActivity.class);
                        startActivity(i);
                        LogUtils.LOGD("login", s.toString());
                    }

                    @Override
                    public void onFinish() {
                        System.out.println("");
                    }
                });

            }

        });
    }
}