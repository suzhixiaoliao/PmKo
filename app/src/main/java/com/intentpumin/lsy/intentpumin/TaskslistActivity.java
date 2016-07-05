package com.intentpumin.lsy.intentpumin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SyncInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intentpumin.lsy.intentpumin.adapter.TasklistAdapter;
import com.intentpumin.lsy.intentpumin.commonview.PullToRefreshLayout;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.network.LogUtils;
import com.intentpumin.lsy.intentpumin.tools.Rusult;
import com.intentpumin.lsy.intentpumin.tools.RusultItem;
import com.intentpumin.lsy.intentpumin.tools.d_exec_m;
import com.intentpumin.lsy.intentpumin.tools.item_task;
import com.intentpumin.lsy.intentpumin.tools.items;
import com.intentpumin.lsy.intentpumin.tools.login;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

/**
 * Created by yang on 2016/4/21.
 */
public class TaskslistActivity extends AutoLayoutActivity {
    private ListView mtasklist;
    private TasklistAdapter adapter;
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
        setContentView(R.layout.layout_tasklist);
        mtasklist = (ListView) findViewById(R.id.list_tasklist);
        swip = (SwipeRefreshLayout) findViewById(R.id.swip);
        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swip.setRefreshing(false);
                //  requestData();

            }
        });
        mdata = new ArrayList<>();
        if (adapter == null) {

            adapter = new TasklistAdapter(this, mdata);
        }
        mtasklist.setAdapter(adapter);
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
                RusultItem resulut = null;
                try {
                    if (!TextUtils.isEmpty(s)) {
                        Gson gson = new Gson();
                        resulut = gson.fromJson(s, RusultItem.class);
                        //正常情况是用result.getData().getItems得到数据组，而不是直接去获取result.getData().getItems().get(0)
                        if (resulut != null && resulut.getData() != null && resulut.getData().getItems() != null && resulut.getData().getItems().size() > 0) {
                            date = resulut.getData().getItems().get(0).getDate();
                            eqpt_name = resulut.getData().getItems().get(0).getEqpt_name();
                            loct_name = resulut.getData().getItems().get(0).getLoct_name();
                        }
                    }

                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    System.out.println("解析异常");
                }
                System.out.println("================" + date);
                LogUtils.LOGE("TAG", "================" + date);
                System.out.println(s);
                LogUtils.LOGD("login3", s.toString());


//                items items = new items();
//                items.setDate(date);
//                items.setEqpt_name(eqpt_name);
//                items.setLoct_name(loct_name);
//                mdata.add(items);
                if (resulut != null && resulut.getData() != null && resulut.getData().getItems() != null && resulut.getData().getItems().size() > 0) {
                    mdata.addAll(resulut.getData().getItems());
                }
                //  adapter.notifyDataSetChanged();
                adapter.setItems(mdata);
                LogUtils.LOGD("login3", mdata.toString());
                LogUtils.LOGD("login3", mdata.toString());
            }

            @Override
            public void onFinish() {
                //结束刷新
                swip.setRefreshing(false);
                System.out.println("完成");
            }
        });

    }
}