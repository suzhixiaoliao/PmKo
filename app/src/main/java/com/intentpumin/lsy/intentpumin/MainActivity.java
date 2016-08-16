package com.intentpumin.lsy.intentpumin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intentpumin.lsy.intentpumin.activity.BaseActivity;
import com.intentpumin.lsy.intentpumin.adapter.MainViewPagerAdapter;
import com.intentpumin.lsy.intentpumin.adapter.MainDeviceAdapter;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.network.LogUtils;
import com.intentpumin.lsy.intentpumin.tools.device.items;
import com.intentpumin.lsy.intentpumin.tools.device.result_device_items;
import com.intentpumin.lsy.intentpumin.tools.logindate.login;
import com.intentpumin.lsy.intentpumin.zxing.CaptureActivity;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class MainActivity extends BaseActivity{
    private TextView tv_main;
    private TextView tv_return;
    private ListView mtasklist;
    private MainDeviceAdapter adapter;
    private List<items> mdata;
    private SharedPreferences sp;
    private SwipeRefreshLayout swip;
    private int ScreeWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("info", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        initViewpager();
        mtasklist = (ListView) findViewById(R.id.list_tasklist_fu);
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
            adapter = new MainDeviceAdapter(this, mdata);
        }
        mtasklist.setAdapter(adapter);
        mtasklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               //items items = mdata.get(position);
                Log.d("un", mdata.toString());
                items items = mdata.get(position);
                Log.d("un", mdata.toString());
                Intent intent = new Intent(MainActivity.this, DataExecuteTasks2Activity.class);
                intent.putExtra("item", items);
                MainActivity.this.startActivity(intent);
            }
        });
        requestData();

    }


    private void initViewpager() {
        tv_main = (TextView) findViewById(R.id.tv_main);
        //第四步
        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        String mName=sp.getString("name","");
        tv_main.setText(mName + ",您好");
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
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        LogUtils.LOGD("login", s.toString());

                    }

                    @Override
                    public void onFinish() {
                        System.out.println("");
                    }
                });
            }
        });
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        View view1 = LayoutInflater.from(this).inflate(R.layout.layout_yunwei_zhu, null);
        ImageView  uploading= (ImageView) view1.findViewById(R.id.iv_download);
        ImageView uploading1 = (ImageView) view1.findViewById(R.id.iv_download2);
        ImageView  shangchuang = (ImageView) view1.findViewById(R.id.iv_shangchaung);
        ImageView shangchuang1 = (ImageView) view1.findViewById(R.id.iv_shangchuang2);
        ImageView scan1 = (ImageView) view1.findViewById(R.id.iv_scan2);
        ImageView renwu1 = (ImageView) view1.findViewById(R.id.iv_renwu2);
        ImageView chakan1 = (ImageView) view1.findViewById(R.id.iv_chakan2);
        ImageView scan = (ImageView) view1.findViewById(R.id.iv_scans);
        //第四步
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inter1 = getIntent();
                inter1.setClass(MainActivity.this, CaptureActivity.class);
                inter1.putExtra("str_all", "1");
                startActivity(inter1);
            }
        });
        ImageView renwu = (ImageView) view1.findViewById(R.id.iv_renwu);
        renwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inter1 = getIntent();
                inter1.setClass(MainActivity.this, UnfinishedDeviceActivity.class);
                startActivity(inter1);

            }
        });
        ImageView chakan = (ImageView) view1.findViewById(R.id.iv_chakan);
        chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inter1 = getIntent();
                inter1.setClass(MainActivity.this, HelloChartActivity.class);
                startActivity(inter1);
            }
        });
        ScreeWidth = (getWindowManager().getDefaultDisplay().getWidth())/8;//获取屏幕的宽度的1/8作为ImgageView的宽度和高度
        setAutoWidth(uploading);
        setAutoWidth(uploading1);
        setAutoWidth(shangchuang);
        setAutoWidth(shangchuang1);
        setAutoWidth(scan1);
        setAutoWidth(renwu1);
        setAutoWidth(chakan1);
        setAutoWidth(scan);
        setAutoWidth(renwu);
        setAutoWidth(chakan);
        View view2 = LayoutInflater.from(this).inflate(R.layout.layout_yunwei_two, null);
        ImageView  xiazai_t= (ImageView) view2.findViewById(R.id.iv_xiazai_t);
        ImageView xiazai_t2 = (ImageView) view2.findViewById(R.id.iv_xiazai_t2);
        ImageView  shangchuang_t = (ImageView) view2.findViewById(R.id.iv_shangchuang_t);
        ImageView shangchuang_t2 = (ImageView) view2.findViewById(R.id.iv_shangchuang_t2);
        ImageView scan_t = (ImageView) view2.findViewById(R.id.iv_scan_t);
        ImageView scan_t2 = (ImageView) view2.findViewById(R.id.iv_scan_t2);
        ImageView chakan_t2 = (ImageView) view2.findViewById(R.id.iv_chakan_t2);
        ImageView chakan_t = (ImageView) view2.findViewById(R.id.iv_chakan_t);
        ImageView renwu_t2= (ImageView) view2.findViewById(R.id.iv_renwu_t2);
        ImageView renwu_t = (ImageView) view2.findViewById(R.id.iv_renwu_t);
        setAutoWidth(xiazai_t2);
        setAutoWidth(xiazai_t);
        setAutoWidth(shangchuang_t);
        setAutoWidth(shangchuang_t2);
        setAutoWidth(scan_t);
        setAutoWidth(renwu_t2);
        setAutoWidth(chakan_t2);
        setAutoWidth(scan_t2);
        setAutoWidth(renwu_t);
        setAutoWidth(chakan_t);

        ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        MainViewPagerAdapter adapter = new MainViewPagerAdapter();
        adapter.setViews(views);
        viewPager.setAdapter(adapter);
    }

    /**
     */
    public void setAutoWidth(ImageView image){
        RelativeLayout.LayoutParams ps = (RelativeLayout.LayoutParams) image.getLayoutParams();
        ps.height = ScreeWidth;
        ps.width = ScreeWidth;
        image.setLayoutParams(ps);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private void requestData() {
        RequestParams params = new RequestParams();
        //final login mlogin = (login) getIntent().getSerializableExtra("login");
        String finished = "N";
        params.addFormDataPart("signature", "1");
        params.addFormDataPart("finished", finished);
        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        String mPhoneno=sp.getString("phoneno","");
        params.addFormDataPart("phoneno",mPhoneno);

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
                        if (resulut != null && resulut.getData() != null && resulut.getData()
                                .getItems() != null && resulut.getData().getItems().size() > 0) {
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
                //结束刷新
                swip.setRefreshing(false);
                System.out.println("完成");
            }
        });

    }
}