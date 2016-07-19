package com.intentpumin.lsy.intentpumin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.activity.BaseActivity;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.network.LogUtils;

import com.intentpumin.lsy.intentpumin.zxing.CaptureActivity;

import com.intentpumin.lsy.intentpumin.tools.logindate.login;
import com.pumin.lzl.pumin.Areaselection;


import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class MainAdminActivity extends BaseActivity {
    private TextView tv_main;
    private ImageView iv_ss;
    private TextView tv_return;
    private ImageView iv_scan;
    private ImageView iv_renwu;

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
                //进入到扫描界面。
                Intent   it = new Intent(MainAdminActivity.this, CaptureActivity.class);
                it.putExtra("str_all","1");
                startActivity(it);
            }
        });

        iv_renwu= (ImageView) findViewById(R.id.iv_renwu);
        iv_renwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入部署任务界面(搜索，扫描，选择)
                Intent it=new Intent(MainAdminActivity.this, Areaselection.class);
                startActivity(it);
            }
        });

        login mlogin = (login) getIntent().getSerializableExtra("login");
        tv_main.setText(mlogin.getName() + ",您好");
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