package com.pumin.lzl.pumin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.bean.Lookstate_object;
import com.pumin.lzl.pumin.utils.SpringProgressView;
import com.pumin.lzl.pumin.utils.Work2_SpringProgress;
import com.pumin.lzl.pumin.utils.Work_SpringProgress;
import com.pumin.lzl.pumin.utils.Works_SpringProgress;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

/*
*@author lzl
*created at 2016/6/12 10:28
* 统计工作完成度信息--趋势
*/
public class LookState_Fragment extends Fragment {
    View view;
    //json解析
    String info;
    String str;
    JSONObject jsonobject;
    JSONObject object;
    RequestQueue request;
    String path = "";

    Lookstate_object look_object;

    //日期进度条
    private TextView state_date2;
    private SpringProgressView progressView;
    int last;  //一个月最多有几天
    int year;
    int day;
    int month;

    //工作完成度
    private TextView work_number;
    private Work2_SpringProgress work_progress_view;

    //工作完成百分比
    private TextView workok_number;
    private Work_SpringProgress workok_progress_view;

    private TextView Otwork_number;
    private Works_SpringProgress Otwork_progress_view;


    public LookState_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.state_fragment, null);
        request = Volley.newRequestQueue(getContext());//得到volley对象
        initview();
        setprogerss(); //日期进度条
        query();
        return view;
    }

    //初始化控件
    private void initview() {
        state_date2= (TextView) view.findViewById(R.id.state_date2);
        progressView = (SpringProgressView) view.findViewById(R.id.spring_progress_view);


        work_number = (TextView) view.findViewById(R.id.work_number);
        work_progress_view = (Work2_SpringProgress) view.findViewById(R.id.work_progress_view);

        workok_number = (TextView) view.findViewById(R.id.workok_number);
        workok_progress_view = (Work_SpringProgress) view.findViewById(R.id.workok_progress_view);

        Otwork_number = (TextView) view.findViewById(R.id.Otwork_number);
        Otwork_progress_view = (Works_SpringProgress) view.findViewById(R.id.Otwork_progress_view);
    }

    //日期进度条
    private void setprogerss() {
        Calendar c = Calendar.getInstance(); //获取系统时间
        last = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        progressView.setMaxCount(last);
        progressView.setCurrentCount(day);
    }

    //请求--网络加载
    public void query() {
        Bundle bundle1 = getArguments(); //获取main中传来的值

        //把path转码
        try {
            str = bundle1.getString("set_url");
            System.out.println(str + "这是传过来的参数");

            //接口规范
//            http://app.pumintech.com:40000/api/user/get_stat_info?signature=1
//            http://10.16.1.201:40000/api/user/get_stat_info?signature=1
            //表名：D_EXEC_D_TASK
            //参数---时间参数
            path = "http://10.16.1.201:40000/api/user/get_stat_info?signature=1&eqpt_id=" + str;
            System.out.println("Lookstate" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(path, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject response) {
                        // 成功获取数据后将数据显示在屏幕上
                        try {

                            info = response.toString();
                            // info = response.getString("UTF-8");
                        } catch (Exception e) {
                            // TODO Auto-ge  nerated catch block
                            e.printStackTrace();
                        }
                        Log.e("TAG", response + "" + "url");
                        System.out.println("这是looksate_fragment中加载数据，数据加载成功" + info);
                        getlooksate(info);
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.getMessage(), error);
                System.out.println("加载失败,请检查网络是否通畅");
            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {

                try {
                    JSONObject jsonObject = new JSONObject(
                            new String(response.data, "UTF-8"));
                    return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }

        };
        request.add(jsonObjectRequest);
    }

    //json解析
    private void getlooksate(String infos) {
        try {
            jsonobject = new JSONObject(infos);
            String datas = jsonobject.getString("data");

            jsonobject = new JSONObject(datas);
            if(datas==null){
                Toast.makeText(getContext(), "没有数据", Toast.LENGTH_SHORT).show();
            }
            String totals = jsonobject.getString("total");
            String expects = jsonobject.getString("expect");

            String finishs = jsonobject.getString("_finish");  //已经完成的工作量
            String ontimes = jsonobject.getString("_ontime"); //按时完成的量

            System.out.println("lookstate.......百分比" + finishs + "..." + ontimes);


            look_object = new Lookstate_object(totals, expects, ontimes, finishs);

            int maxs = Integer.parseInt(look_object.getTotals());
            int counts = Integer.parseInt(look_object.getExpects());
            int okcounts = Integer.parseInt(look_object.getFinishs());
            int ontime_s = Integer.parseInt(look_object.getOntimes());

            percentage(okcounts, ontime_s); //百分比传值
            Completionofwork(maxs, counts); //工作量传值
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //工作完成百分比---单参数  按时完成百分比---单参数
    private void percentage(int okcount, int ontime) {
        workok_progress_view.setMaxCount(100);
        workok_progress_view.setCurrentCount(okcount);
        workok_number.setText("" + okcount + "%");

        Otwork_progress_view.setMaxCount(100);
        Otwork_progress_view.setCurrentCount(ontime);
        Otwork_number.setText("" + ontime + "%");
    }


    //工作完成情况----两个参数 1.工作总量 2.已完成工作量 ..预留参数接口-网络请求
    private void Completionofwork(int max, int count) {
        work_progress_view.setMaxCount(max);
        work_progress_view.setCurrentCount(count);
        work_number.setText("本月工作总量：" + max + "  完成：" + count);

    }

}
