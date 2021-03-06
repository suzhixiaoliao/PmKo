package com.intentpumin.lsy.intentpumin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.device.items;
import com.intentpumin.lsy.intentpumin.tools.mRemark.remark_list;
import com.intentpumin.lsy.intentpumin.tools.stat.result_stat_get;
import com.intentpumin.lsy.intentpumin.tools.task.task_get;
import com.intentpumin.lsy.intentpumin.tools.logindate.login;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;
//备注页面
public class TaskRemarkActivity extends Activity {
    private EditText et_tast_com;
    private Button bt_tast_qd;
    private Button bt_tast_qx;
    private SharedPreferences sp;
    remark_list list=  new remark_list();
    private task_get task_get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskremark);
        task_get = (task_get) getIntent().getSerializableExtra("task");
        if (task_get != null) {
            Log.e("DataExecute", "收到了" + task_get.toString());
        }

        initDate();
    }
    private void init(String remark) {
        et_tast_com= (EditText) findViewById(R.id.et_tast_com);
        et_tast_com.setText(remark);
       // String mRemark = et_tast_com.getText().toString();
        bt_tast_qd= (Button) findViewById(R.id.bt_tast_qd);
        bt_tast_qx= (Button) findViewById(R.id.bt_tast_qx);
        bt_tast_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_tast_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDate();
            }
        });
    }
    private void initDate() {
        SharedPreferences sp = getSharedPreferences("user", Activity.MODE_PRIVATE);
        String mPhoneno=sp.getString("phoneno", "");
        String task_id=task_get.getTask_id();
        String eqpt_id=task_get.getEqpt_id();
        String mdate =task_get.getDate();
        String signature="1";
        final RequestParams params = new RequestParams();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date()).substring(0, 10);
        params.addFormDataPart("signature", signature);
        params.addFormDataPart("phoneno",mPhoneno);
        params.addFormDataPart("task_id",task_id);
        params.addFormDataPart("eqpt_id",eqpt_id);
        params.addFormDataPart("date",mdate);
        params.addFormDataPart("signature", signature);
        params.addFormDataPart("phoneno",mPhoneno);
        HttpUtil.getInstance().post(MainLogic.GET_REMARK, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(String s) {
                System.out.println(s);
                remark_list resulut = null;
                try {
                    if (!TextUtils.isEmpty(s)) {
                        Gson gson = new Gson();
                        resulut = gson.fromJson(s, remark_list.class);//将JSON数据转成Result对象
                        String remark = resulut.getData().getRemark();
                        init(remark);
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    System.out.println("解析异常");
                }

            }

            @Override
            public void onFinish() {
                System.out.println("错误");
            }

        });

    }

    private void requestDate() {
        SharedPreferences sp = getSharedPreferences("user", Activity.MODE_PRIVATE);
        String mPhoneno=sp.getString("phoneno", "");
        String task_id=task_get.getTask_id();
        String eqpt_id=task_get.getEqpt_id();
        String mdate =task_get.getDate();
        String signature="1";
        final RequestParams params = new RequestParams();
        String remark = et_tast_com.getText().toString();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date()).substring(0,10);
        params.addFormDataPart("signature", signature);
        params.addFormDataPart("phoneno",mPhoneno);
        params.addFormDataPart("remark", remark);
        params.addFormDataPart("signature", signature);
        params.addFormDataPart("phoneno",mPhoneno);
       params.addFormDataPart("task_id",task_id);
        params.addFormDataPart("eqpt_id",eqpt_id);
       params.addFormDataPart("date",mdate);

        HttpUtil.getInstance().post(MainLogic.SET_REMARK, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(String s) {
                System.out.println(s);
                Toast.makeText(TaskRemarkActivity.this, "任务备注成功！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                System.out.println("错误");
            }

        });

        }
}
