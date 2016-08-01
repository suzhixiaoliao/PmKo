package com.pumin.lzl.pumin;

import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.text.method.DateKeyListener;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
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
import com.pumin.lzl.pumin.adapter.Furn_task_adapter;
import com.pumin.lzl.pumin.bean.Furn_task_obj;
import com.pumin.lzl.pumin.utils.Url;
import com.pumin.lzl.pumin.utils.ViewHeight;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/*
*@author lzl
*created at 2016/7/27 9:57
* 布置临时任务界面
* 1.设备名称
* 2.维护 人员
* 3.时间选择
* 4.任务选择or自编任务
* 5.添加临时任务
*/

public class Furnishtsak extends AppCompatActivity {

    //任务数据的适配
    ArrayList<Furn_task_obj> furn_task_array = new ArrayList<>();
    Furn_task_adapter furn_adapter;
    Furn_task_obj task_obj;

    private ScrollView myscroll;
    private ImageButton main_back;
    private TextView furn_name;  //设备名
    private TextView furn_name_true;  //显示人员信息
    private TextView furn_name_true2;
    private ImageView furn_name_false; //选择人员
    private ImageView furn_name_false2;
    private TextView furn_date; //显示日期
    private TextView furn_select_date;  //选择日期
    private ImageView furn_add_newtsak; //输入一个新任务
    private ListView furn_list; //显示所有任务的列表
    private ImageView furn_add; //添加任务按钮
    //获取日期格式器对象
    DateFormat fmtDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
    //网络请求
    RequestQueue queue;
    JSONObject object;
    JSONObject object2;
    JSONArray jsonarr;
    String info; //解析的数据
    String furn_name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furnishtsak);
        queue = Volley.newRequestQueue(this); //得到volley对象
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initview();
        initback();
        selectpersonnel();
        myscroll.smoothScrollBy(0, 0);  //设置位置
        query(); //网络请求
        getstat(); //得到所有设备任务
    }

    private void initview() {
        main_back = (ImageButton) findViewById(R.id.main_back);
        myscroll = (ScrollView) findViewById(R.id.myscroll);
        furn_name = (TextView) findViewById(R.id.furn_name);
        furn_name_true = (TextView) findViewById(R.id.furn_name_true);
        furn_name_true2 = (TextView) findViewById(R.id.furn_name_true2);
        furn_name_false = (ImageView) findViewById(R.id.furn_name_false);
        furn_name_false2 = (ImageView) findViewById(R.id.furn_name_false2);
        furn_date = (TextView) findViewById(R.id.furn_date);
        furn_select_date = (TextView) findViewById(R.id.furn_select_date);
        furn_add = (ImageView) findViewById(R.id.furn_add);
        furn_list = (ListView) findViewById(R.id.furn_list);
    }

    //初始化监听事件listener
    private void initback() {
        main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //请求接口
    public void query() {
        String path = "";
        furn_name2 = getIntent().getStringExtra("put_areament");
        try {
            //接口规范
            // http://api.pumintech.com:40000/app/?signature=1
            path = Url.path + "get_eqpt_info?signature=1&eqpt_id=" + furn_name2;

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
                        Designview(info);
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

    private void Designview(String info) {
        //名字-做网络请求。获取该设备的所有信息
        try {
            object = new JSONObject(info);
            String data = object.getString("data");
            object = new JSONObject(data);
            String name_drv = object.getString("eqpt_name");
            furn_name.setText(name_drv);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //设置为当日时间
        SimpleDateFormat sdateformat = new SimpleDateFormat("yyyy-MM-dd");
        String end_time = sdateformat.format(new java.util.Date());
        furn_date.setText(end_time);
    }

    //选择运维人员
    public void selectpersonnel() {
        //选择主要负责人
        furn_name_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Furnishtsak.this, Chooseperson.class);
                startActivity(it);
            }
        });
        //选择备用负责人
        furn_name_false2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Furnishtsak.this, Chooseperson.class);
                startActivity(it);
            }
        });
        //选择时间
        furn_select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dateDlg = new DatePickerDialog(Furnishtsak.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateAndTime.set(Calendar.YEAR, year);
                                dateAndTime.set(Calendar.MONTH, monthOfYear);
                                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                furn_date.setText(fmtDate.format(dateAndTime.getTime()));
                            }
                        },
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg.show();
            }
        });
        //添加新的任务
        furn_add_newtsak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //所有设备任务
    private void getstat() {
        info = "";
        String path = "";
        furn_name2 = getIntent().getStringExtra("put_areament");
        //http://app.pumintech.com:40000/app/get_task_list_by_eqptid  所有任务
        try {
            //接口规范
            // http://api.pumintech.com:40000/app/?signature=1
            path = Url.path + "get_task_list_by_eqptid?signature=1&eqpt_id=" + furn_name2;

        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(path, null,
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
                        System.out.println("设备任务：xxx" + info);
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
        queue.add(jsonObjectRequest2);
    }

    //得到设备任务
    private void init(String stat) {
        furn_task_array = new ArrayList<>();
        //这里要生成一个dialog，再有dialog得到listview中设备到布置任务界面
        try {
            object2 = new JSONObject(stat);
            String w = object2.getString("data");
            object2 = new JSONObject(w);
            String itm = object2.getString("items");
            jsonarr = new JSONArray(itm);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jsonarr.length() > 0 && jsonarr != null) {
            for (int i = 0; i < jsonarr.length(); i++) {
                try {
                    JSONObject obj = jsonarr.getJSONObject(i);
                    task_obj = new Furn_task_obj(obj.getString("task_name"));
                    furn_task_array.add(task_obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        furn_adapter = new Furn_task_adapter(furn_task_array, this);
        furn_list.setAdapter(furn_adapter);
        ViewHeight.setListViewHeight(furn_list, furn_adapter);
    }
}
