package com.intentpumin.lsy.intentpumin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intentpumin.lsy.intentpumin.activity.BaseActivity;
import com.intentpumin.lsy.intentpumin.adapter.MainViewPagerAdapter;
import com.intentpumin.lsy.intentpumin.adapter.MainDeviceAdapter;
import com.intentpumin.lsy.intentpumin.commonview.PullToRefreshLayout;
import com.intentpumin.lsy.intentpumin.commonview.PullableListView;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.network.LogUtils;
import com.intentpumin.lsy.intentpumin.tools.device.items;
import com.intentpumin.lsy.intentpumin.tools.device.result_device_items;


import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class MainActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener
        ,AdapterView.OnItemClickListener {
    private TextView tv_main;
    private TextView tv_return;
    private TextView count_main;
    private MainDeviceAdapter adapter;
    private List<items> mdata;
    private SharedPreferences sp;

    private int ScreeWidth;
    private PullableListView mPullRefreshListView;//上拉下拉加载刷新
    private PullToRefreshLayout ptrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("info", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        //initViewpager();
        mPullRefreshListView  = (PullableListView) findViewById(R.id.list_tasklist_fu);
        ptrl = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        ptrl.setOnRefreshListener(this);
        mdata = new ArrayList<>();
        if (adapter == null) {
            adapter = new MainDeviceAdapter(this, mdata);
        }
        mPullRefreshListView.setAdapter(adapter);
        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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


    private void initViewpager(String count_main_tv) {
        tv_main = (TextView) findViewById(R.id.tv_main);
        //第四步
        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        String mName=sp.getString("name","");
        tv_main.setText(mName + ",您好");
        count_main= (TextView) findViewById(R.id.count_main);
        //count_main.setText("您今天需要检修的设备台（套）数为"+count_main_tv);
        SpannableString ss = new SpannableString("您今天需要检修的设备台（套）数为"+count_main_tv);
        ss.setSpan(new ForegroundColorSpan(Color.GRAY), 0, 16,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        count_main.setText(ss);
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
        ImageView  shangchuang = (ImageView) view1.findViewById(R.id.iv_shangchaung);
        shangchuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inter1 = getIntent();
                inter1.setClass(MainActivity.this, TheRepairListActivity.class);
                startActivity(inter1);
            }
        });
        ImageView scan = (ImageView) view1.findViewById(R.id.iv_scan);
        //第四步
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inter1 = getIntent();
                inter1.setClass(MainActivity.this, MipcaActivityCapture.class);
                inter1.putExtra("str_all", "0");
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
        setAutoWidth(shangchuang);
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
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 千万别忘了告诉控件刷新完毕了哦！
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 1000);
    }

    private void requestData() {
        RequestParams params = new RequestParams();
        params.addFormDataPart("signature", "1");
        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        String mPhoneno=sp.getString("phoneno","");
        params.addFormDataPart("phoneno",mPhoneno);

        HttpUtil.getInstance().post(MainLogic.GET_TODAY, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(String s) {
                System.out.println("onSuccess");
                result_device_items resulut = null;
                try {
                    if (!TextUtils.isEmpty(s)) {
                        Gson gson = new Gson();
                        resulut = gson.fromJson(s, result_device_items.class);
                        //正常情况是用result.getData().getItems得到数据组，而不是直接去获取result.getData().getItems().get(0)
                        if (resulut != null && resulut.getData() != null && resulut.getData()
                                .getItems() != null && resulut.getData().getItems().size() > 0) {
                                String count_main_tv=resulut.getData().getTotal();
                            initViewpager(count_main_tv);
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
                System.out.println("完成");
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}