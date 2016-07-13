package com.intentpumin.lsy.intentpumin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intentpumin.lsy.intentpumin.activity.BaseActivity;
import com.intentpumin.lsy.intentpumin.adapter.DeviceAdapter;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.device.items;
import com.intentpumin.lsy.intentpumin.tools.device.result_device_items;
import com.intentpumin.lsy.intentpumin.tools.logindate.login;
import com.intentpumin.lsy.intentpumin.zxing.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

/**
未完成设备页面 即清单页面
 */
public class UnfinishedDeviceActivity extends BaseActivity {
    private ListView mtasklist;
    private DeviceAdapter adapter;
    private List<items> mdata;
    String result = "";
    private SharedPreferences sp;
    private SwipeRefreshLayout swip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("info", Context.MODE_PRIVATE);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_unfinisheddevice);
        mtasklist = (ListView) findViewById(R.id.list_tasklist);
        swip = (SwipeRefreshLayout) findViewById(R.id.swip);
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swip.setRefreshing(false);
                 requestData();

            }
        });
        mdata = new ArrayList<>();
        if (adapter == null) {

            adapter = new DeviceAdapter(this, mdata);
        }
        mtasklist.setAdapter(adapter);
        mtasklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items items = mdata.get(position);
                Log.d("un",mdata.toString());
                Intent intent = new Intent(UnfinishedDeviceActivity.this, CaptureActivity.class);

                intent.putExtra("item", items);
                UnfinishedDeviceActivity.this.startActivity(intent);
            }
        });
        requestData();
    }

    private void requestData() {
        RequestParams params = new RequestParams();
        final login mlogin = (login) getIntent().getSerializableExtra("login");
        String finished = "N";
        params.addFormDataPart("signature", "1");
        params.addFormDataPart("finished", finished);
        params.addFormDataPart("phoneno", mlogin.getPhoneno());

        HttpUtil.getInstance().post(MainLogic.GET_TASK_LIST, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(String s) {
                String eqpt_name = "";
                String date = "";
                String loct_name = "";
                System.out.println("onSuccess");
                result_device_items resulut = null;
                try {
                    if (!TextUtils.isEmpty(s)) {
                        Gson gson = new Gson();
                        resulut = gson.fromJson(s, result_device_items.class);
                        //正常情况是用result.getData().getItems得到数据组，而不是直接去获取result.getData().getItems().get(0)
                        if (resulut != null && resulut.getData() != null && resulut.getData().getItems() != null && resulut.getData().getItems().size() > 0) {
                            date = resulut.getData().getItems().get(0).getDate();
                            eqpt_name = resulut.getData().getItems().get(0).getEqpt_name();
                            loct_name = resulut.getData().getItems().get(0).getLoct_name();
                        }
                    }

                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
                System.out.println(s);
                if (resulut != null && resulut.getData() != null && resulut.getData().getItems() != null && resulut.getData().getItems().size() > 0) {
                    mdata.addAll(resulut.getData().getItems());
                }
                adapter.setItems(mdata);
            }

            @Override
            public void onFinish() {
                swip.setRefreshing(false);
                System.out.println("完成");
            }
        });
    }
}