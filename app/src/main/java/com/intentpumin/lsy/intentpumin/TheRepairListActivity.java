package com.intentpumin.lsy.intentpumin;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intentpumin.lsy.intentpumin.activity.BaseActivity;
import com.intentpumin.lsy.intentpumin.adapter.RepairListAdapter;
import com.intentpumin.lsy.intentpumin.commonview.PullToRefreshLayout;
import com.intentpumin.lsy.intentpumin.commonview.PullableListView;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.repairlist.RepairList;
import com.intentpumin.lsy.intentpumin.tools.repairlist.Repair_item;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class TheRepairListActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    private RepairListAdapter adapter;
    private List<Repair_item> mdata;
    private SharedPreferences sp;
    private int currentPage = 1;
    private int MAX_PAGE = 10;
    private PullableListView mPullRefreshListView;//上拉下拉加载刷新
    private PullToRefreshLayout ptrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupData();
    }

    @Override
    protected void setupData() {
        sp = getSharedPreferences("info", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_unfinisheddevice, R.string.RepairList, MODE_BACK_NAVIGATION);
        mPullRefreshListView = (PullableListView) findViewById(R.id.list_tasklist);
        ptrl = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        ptrl.setOnRefreshListener(this);
        mdata = new ArrayList<>();
        if (adapter == null) {

            adapter = new RepairListAdapter(this, mdata);
        }
        mPullRefreshListView.setAdapter(adapter);
        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Repair_item items = mdata.get(position);
                Intent intent = new Intent(TheRepairListActivity.this, RepairUploadActivity.class);
                intent.putExtra("repair_item", items);
                TheRepairListActivity.this.startActivity(intent);
            }
        });
        requestData();
    }

    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {

        mdata.clear();
//          //请求第一页的数据
        requestData();
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

        if (currentPage <= MAX_PAGE) {
            currentPage++;
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

    private void requestData() {
        String signature = "1";
        RequestParams params = new RequestParams();
        params.addFormDataPart("signature", signature);
        params.addFormDataPart("page", currentPage);
        params.addFormDataPart("count", MAX_PAGE);
        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        String mPhoneno = sp.getString("phoneno", "");
        params.addFormDataPart("phoneno", mPhoneno);

        HttpUtil.getInstance().post(MainLogic.GET_REPAIR, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();

            }

            @Override
            protected void onSuccess(String s) {
                System.out.println(s);
                System.out.println("onSuccess");
                RepairList resulut = null;
                try {
                    if (!TextUtils.isEmpty(s)) {
                        Gson gson = new Gson();
                        resulut = gson.fromJson(s, RepairList.class);
                        //正常情况是用result.getData().getItems得到数据组，而不是直接去获取result.getData().getItems().get(0)
                        if (resulut.getData().getItems()!= null && resulut.getData().getItems() != null && resulut.getData().getItems() != null && resulut.getData().getItems().size() > 0) {
                        }
                    }

                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }

                if (resulut != null && resulut.getData().getItems() != null && resulut.getData().getItems() != null && resulut.getData().getItems().size() > 0) {
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