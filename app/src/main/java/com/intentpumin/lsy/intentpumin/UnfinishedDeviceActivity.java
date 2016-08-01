package com.intentpumin.lsy.intentpumin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intentpumin.lsy.intentpumin.activity.BaseActivity;
import com.intentpumin.lsy.intentpumin.adapter.DeviceAdapter;
import com.intentpumin.lsy.intentpumin.commonview.PullToRefreshLayout;
import com.intentpumin.lsy.intentpumin.commonview.PullableListView;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.device.items;
import com.intentpumin.lsy.intentpumin.tools.device.result_device_items;
import com.intentpumin.lsy.intentpumin.tools.logindate.login;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

/**
未完成设备页面 即清单页面
 */
public class UnfinishedDeviceActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener {
    private DeviceAdapter adapter;
    private List<items> mdata;
    private SharedPreferences sp;
    private login mlogin;
    private Button btn_back;

    private int currentPage = 1;
    private int MAX_PAGE = 10;
    private PullableListView mPullRefreshListView;//上拉下拉加载刷新
    private PullToRefreshLayout ptrl;
    private static final int FIRST_INTO = 0;
    private static final int REFUSH_UP = 1;
    private static final int LOAG_MORE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupData() {
        sp = getSharedPreferences("info", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_unfinisheddevice, R.string.UnfinishedDevice, MODE_BACK_NAVIGATION);
        mPullRefreshListView = (PullableListView) findViewById(R.id.list_tasklist);
        ptrl = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        ptrl.setOnRefreshListener(this);
        mdata = new ArrayList<>();
        if (adapter == null) {

            adapter = new DeviceAdapter(this, mdata);
        }
        mPullRefreshListView.setAdapter(adapter);
        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items items = mdata.get(position);
                Log.d("un", mdata.toString());
                Intent intent = new Intent(UnfinishedDeviceActivity.this, DataExecuteTasks2Activity.class);
                intent.putExtra("item", items);
                UnfinishedDeviceActivity.this.startActivity(intent);
            }
        });
       requestData(1, 10, FIRST_INTO);
    }

    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {

        mdata.clear();
//          //请求第一页的数据
        requestData(1, 10, REFUSH_UP);
        adapter.setItems(mdata);
        // 下拉刷新操作
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 千万别忘了告诉控件刷新完毕了哦！
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 1000);
    }
    @Override
    public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
        currentPage++;
        if (currentPage <= MAX_PAGE) {
            requestData(currentPage, 10, LOAG_MORE);
            adapter.addDate(mdata);
            adapter.notifyDataSetChanged();
        }
        // 加载操作
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 千万别忘了告诉控件加载完毕了哦！
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 1000);
    }

    private void requestData(int page, int count, final int i) {
        RequestParams params = new RequestParams();
        String finished = "N";
        params.addFormDataPart("signature", "1");
        params.addFormDataPart("page", page);
        params.addFormDataPart("count", count);
        params.addFormDataPart("finished", finished);
        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        String mPhoneno = sp.getString("phoneno", "");
        params.addFormDataPart("phoneno", mPhoneno);

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
                System.out.println("完成");
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}