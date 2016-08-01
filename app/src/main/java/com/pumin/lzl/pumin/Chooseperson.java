package com.pumin.lzl.pumin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.pumin.lzl.pumin.adapter.Choose_adapter;
import com.pumin.lzl.pumin.bean.Choose_object;
import com.pumin.lzl.pumin.utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/*
*@author lzl
*created at 2016/7/28 8:35
* 选择运维人员的界面
* 呈现出所有的运维人员
*/
public class Chooseperson extends AppCompatActivity {

    private ImageButton choose_back;
    private ListView choose_list;
    private ImageView choose_add;

    ArrayList<Choose_object> choose_array = new ArrayList<>();
    Choose_object choose_obj;
    Choose_adapter choose_ada;

    //通过请求接口获取所有的运维人员
    RequestQueue queue;
    JSONObject object;
    JSONArray jsonarr;
    String info; //解析的数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseperson);
        queue = Volley.newRequestQueue(this);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initview();
        initback();
        query();
    }

    //销毁当前页面
    private void initback() {
        choose_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //初始化控件
    private void initview() {
        choose_back = (ImageButton) findViewById(R.id.choose_back);
        choose_list = (ListView) findViewById(R.id.choose_list);
        choose_add = (ImageView) findViewById(R.id.choose_add);
    }

    //请求接口
    public void query() {
        String path = "";
        try {
            //接口规范
            // http://api.pumintech.com:40000/app/?signature=1
            path = Url.path + "get_user_list_by_priv?signature=1&priv=2";

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
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Log.d("TAG", info);
                        System.out.println(info);
                        init(info);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.getMessage(), error);
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

    //得到所有运维人员
    private void init(String getper) {
        choose_array = new ArrayList<>();
        try {
            object = new JSONObject(getper);
            String w = object.getString("data");
            object = new JSONObject(w);
            String itm = object.getString("items");
            jsonarr = new JSONArray(itm);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jsonarr.length() > 0 && jsonarr != null) {
            for (int i = 0; i < jsonarr.length(); i++) {
                try {
                    JSONObject obj = jsonarr.getJSONObject(i);
                    choose_obj = new Choose_object(obj.getString("name"), obj.getString("phoneno"));
                    choose_array.add(choose_obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        choose_ada = new Choose_adapter(choose_array, this);
        choose_list.setAdapter(choose_ada);
    }

}
