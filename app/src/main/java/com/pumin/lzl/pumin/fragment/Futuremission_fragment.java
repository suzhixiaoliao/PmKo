package com.pumin.lzl.pumin.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.adapter.Future_frag_adapter;
import com.pumin.lzl.pumin.bean.Future_object;
import com.pumin.lzl.pumin.utils.AllToast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
/*
*@author lzl
*created at 2016/6/8 12:49
* 未来的任务界面
*/

public class Futuremission_fragment extends Fragment {
    View view;  //全局view

    //数据适配器
    Future_frag_adapter future_adapter;
    ArrayList<Future_object> future_Array = new ArrayList<>();
    private ListView future_list;
    //网络加载
    RequestQueue queue;
    JSONObject jsonObject;
    JSONObject object;
    String path = ""; //地址
    String str; //main传递过来的数据
    String info; //url解析的数据
    Future_object fu_object;  //保存数据的对象
    private String start_time; //获取开始日期
    private String END_time; //获取本月最后一天

    public Futuremission_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = View.inflate(getContext(), R.layout.futuremission_fragment, null);
        queue = Volley.newRequestQueue(getContext());  //得到volley对象
        initview();
        query();
        return view;
    }

    //初始化控件
    private void initview() {
        future_list = (ListView) view.findViewById(R.id.future_list);
    }

    //网络请求
    private void query() {
        SimpleDateFormat sdateformat = new SimpleDateFormat("yyyy-MM-dd");
        start_time = sdateformat.format(new java.util.Date());

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
        END_time = simpleFormate.format(calendar.getTime());  //获取本月的最后一天

        Bundle bundle1 = getArguments(); //获得传递过来的值
        str = bundle1.getString("set_url");
        //接口规范(设备编号+开始时间+结束时间>>>就是查询条件)
//            http://app.pumintech.com:40000/api/user/?signature=1
//            http://10.16.1.201:40000/api/user/login?signature=1
        path = "http://10.16.1.201:40000/api/user/get_mt_list_by_eqpt_id?" +
                "signature=1&s_date=" + start_time + "&e_date=" + END_time + "&eqpt_id=" + str;
        System.out.println("这是futuremission中的url" + path);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(path, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject response) {
                        // 成功获取数据后将数据显示在屏幕上
                        try {
                            info = response.toString();
                            // info = response.getString("UTF-8");
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Log.d("TAG", info);
                        initadapter(info);
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.getMessage(), error);
                AllToast.alltoast(Gravity.CENTER, getContext(), "加载失败,请检查网络是否通畅", R.drawable.pmlogo);
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
        queue.add(jsonObjectRequest);
    }


    private void initadapter(String str) {
        future_Array = new ArrayList<>();
        System.out.println("这是futuremission中的数据" + str + "xxxxxxxxxxxxxxxx");
        try {
            jsonObject = new JSONObject(str);
            String date = jsonObject.getString("data");

            jsonObject = new JSONObject(date);
            String items = jsonObject.getString("items");
            System.out.println(items + "xxxxxxxxxxxxxxxxxxxxx");
            JSONArray array = new JSONArray(items);

            if (array.length() > 0 && array != null) {  //判断jsonarray中是否有数据在去存放数据
                for (int w = 0; w < array.length(); w++) {
                    object = array.getJSONObject(w);
                    fu_object = new Future_object(object.getString("date"), object.getString("pmt_name")
                            , object.getString("smt_name"), object.getString("finished"));
                    future_Array.add(fu_object);  //把数据存放在对象数组中
                }
            } else {
                fu_object = new Future_object("暂定", "暂定", "暂定", "未完成");
                future_Array.add(fu_object);  //把数据存放在对象数组中
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        future_adapter = new Future_frag_adapter(getContext(), future_Array);
        future_list.setAdapter(future_adapter);
    }

}
