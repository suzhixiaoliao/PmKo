package com.pumin.lzl.pumin;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.adapter.Parameter_adapter;
import com.pumin.lzl.pumin.adapter.Parameter_adapter2;
import com.pumin.lzl.pumin.bean.Tasklist_object;
import com.pumin.lzl.pumin.bean.Tasklist_object2;
import com.pumin.lzl.pumin.util.Alltitle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
/*
*@author lzl
*created at 2016/6/14 13:24
*  碎片任务列表跳转到这个界面 一个任务维护列表
*/

public class TaskList_alter extends AppCompatActivity {


    private TextView tasklist_name;
    private Alltitle tasklist_title;


    //网络连接
    String str;
    String time;
    String path = "";
    String info;
    RequestQueue queue;
    //    1
    ArrayList<Tasklist_object> task_list = new ArrayList<>();
    Parameter_adapter taskadapter;
    Tasklist_object taskobject;
    private ListView tasklist_maintenance; //这是设备维护任务清单
    //    2
    ArrayList<Tasklist_object2> task_list2 = new ArrayList<>();
    Parameter_adapter2 taskadapter2;
    Tasklist_object2 taskobject2;
    private ListView tasklist_parameter;  //这是设备记录参数


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist_alter);
        queue = Volley.newRequestQueue(this); //得到volley对象
        intiview();
        setTasklist_name();
        qurey();
    }

    //设置标题
    private void setTasklist_name() {
        tasklist_title.setTitle("任务清单");
    }


    //初始化控件
    private void intiview() {
        tasklist_name = (TextView) findViewById(R.id.tasklist_name);
        tasklist_title = (Alltitle) findViewById(R.id.tasklist_title);
        tasklist_maintenance = (ListView) findViewById(R.id.tasklist_maintenance);
        tasklist_parameter = (ListView) findViewById(R.id.tasklist_parameter);
    }

    //列表得到数据
    //http://app.pumintech.com:40000/api/user/get_mt_details?signature=1
    //http://10.16.1.201:40000/api/user/get_mt_details?signature=1
    //查找规范：EQPT_ID和DATE一致
    private void qurey() {
        str = getIntent().getStringExtra("charge").toString();
        time = getIntent().getStringExtra("inputdate").toString();
        time = time.substring(0, 10);  //截取日期时间
        System.out.println("tasklist" + str + "这是时间" + time);

        path = "http://10.16.1.201:40000/api/user/get_mt_details?signature=1&date=" + time + "&eqpt_id=" + str;
        System.out.println("tasklist_alter_url" + path);
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
                        Log.e("TAG", response + "" + "url");
                        getvalue(info);
                        System.out.println("这是main_tasklist中加载数据，数据加载成功" + info);
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.getMessage(), error);
                System.out.println("不好意思，加载失败");
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

    private void getvalue(String strinfo) {
        task_list = new ArrayList<>();
        task_list2 = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(strinfo);
            String data = object.getString("data");

            object = new JSONObject(data);
            String tasks = object.getString("task");  //任务清单
            String stats = object.getString("stat"); //参数清单


            JSONArray array = new JSONArray(tasks);
            if (array.length() > 0 && array != null) {
                for (int i = 0; i < array.length(); i++) {
                    object = array.getJSONObject(i);
                    taskobject = new Tasklist_object(
                            object.getString("task_name"),
                            object.getString("task_comment"));
                    task_list.add(taskobject);
                }
            } else {
                taskobject = new Tasklist_object("没有任务名称", "没有任务细节");
                task_list.add(taskobject);
            }
            //目前我没有更优的做法。。只能这样了
            JSONArray array2 = new JSONArray(stats);
            if (array2.length() > 0 && array2 != null) {
                for (int w = 0; w < array2.length(); w++) {
                    object = array2.getJSONObject(w);
                    taskobject2 = new Tasklist_object2(
                            object.getString("stat_name"),
                            object.getString("unit"),
                            object.getString("r_value"));
                    task_list2.add(taskobject2);
                }
            } else {
                taskobject2 = new Tasklist_object2("没有测量", "", "");
                task_list2.add(taskobject2);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        taskadapter = new Parameter_adapter(this, task_list);
        tasklist_maintenance.setAdapter(taskadapter);

        taskadapter2 = new Parameter_adapter2(this, task_list2);
        tasklist_parameter.setAdapter(taskadapter2);
    }
}
