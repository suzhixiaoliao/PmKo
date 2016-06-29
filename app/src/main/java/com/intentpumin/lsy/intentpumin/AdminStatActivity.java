package com.intentpumin.lsy.intentpumin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intentpumin.lsy.intentpumin.adapter.AdminStatAdapter;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.network.LogUtils;
import com.intentpumin.lsy.intentpumin.tools.Add_stat;
import com.intentpumin.lsy.intentpumin.tools.RusultStat;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class AdminStatActivity extends Activity {
    private ListView mlist;
    private AdminStatAdapter adapter;
    private List<Add_stat> mdata;
    private SharedPreferences sp;
    //String url = "http://app.pumintech.com:40000/api/user/get_stat_list_by_eqpt";
    String url = "http://10.16.1.201:40000/api/user/get_stat_list_by_eqpt";
    TextView tv_admin_stat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_stat);
        mlist= (ListView) findViewById(R.id.list_adminstat);
        init();
    }

    private void init() {
        //跳转
        tv_admin_stat=(TextView) findViewById(R.id.tv_admin_stat);
        tv_admin_stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inter = getIntent();
                inter.setClass(AdminStatActivity.this, AdminChartActivity.class);
                startActivity(inter);
                  /*  Intent inter = getIntent();
                    Bundle bun = new Bundle();
                    bun.putSerializable("checkList", (Serializable) adapter.getCheckList());


//                System.out.println("check list size is "+adapter.getCheckList().size());
                    inter.putExtra("ids", bun);
//                inter.setClass(AdminSSActivity.this, AdminChartActivity.class);
                    setResult(101,inter);
//                startActivity(inter);
                    finish();*/


            }
        });
        mdata = new ArrayList<>();
        if (adapter == null) {
            adapter = new AdminStatAdapter(this, mdata);
        }
        mlist.setAdapter(adapter);
        requestData();
    }

    private void requestData() {
        RequestParams params = new RequestParams();
        params.addFormDataPart("signature", "1");
        params.addFormDataPart("eqpt_id","47875311-1A24-2B35-2783-AE19D7334E2D");

        HttpUtil.getInstance().post(MainLogic.GET_EQPT_STAT, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(String s) {
                String stat_name = "";
                String stat_id = "";
                System.out.println("onSuccess");
                RusultStat resulut = null;
                try {
                    if (!TextUtils.isEmpty(s)) {
                        Gson gson = new Gson();
                        resulut = gson.fromJson(s, RusultStat.class);
                        //正常情况是用result.getData().getItems得到数据组，而不是直接去获取result.getData().getItems().get(0)
                        if (resulut != null && resulut.getData() != null && resulut.getData().getItems() != null && resulut.getData().getItems().size() > 0) {
                            LogUtils.LOGE("qq", resulut.toString());
                            stat_name = resulut.getData().getItems().get(0).getStat_name();
                        }
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    System.out.println("解析异常");
                }
                System.out.println(s);
                LogUtils.LOGD("login3", s.toString());
                Add_stat items=new Add_stat();
                items.setStat_name(stat_name);
                //  mdata.add(items);
                if (resulut != null && resulut.getData() != null && resulut.getData().getItems() != null && resulut.getData().getItems().size() > 0) {
                    mdata.addAll(resulut.getData().getItems());
                }
                //  adapter.notifyDataSetChanged();
                adapter.setItems(mdata);
                LogUtils.LOGD("login3", mdata.toString());
            }

            @Override
            public void onFinish() {
                //结束刷新
                System.out.println("完成");
            }
        });

    }

}

