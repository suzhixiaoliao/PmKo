package com.pumin.lzl.pumin;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
    //新增任务
    private EditText furn_edit_newtask;
    private String add_task;
    //添加一行新任务
    private String get_task; //得到任务名字
    private String get_taskid; //得到任务id
    private String name_drv; //得到设备名字
    private String name_drvid; //得到设备id
    private String new_time; //任务开始时间
    private String get_name; //运维人员
    private String get_nameid; //运维人员id

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
        addtask();//添加新任务
        add_new_task();//添加运维记录
        gettask_name(); //获取运维人员
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
        furn_add_newtsak = (ImageView) findViewById(R.id.furn_add_newtsak);
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
            name_drv = object.getString("eqpt_name");
            furn_name.setText(name_drv);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //设置为当日时间
        SimpleDateFormat sdateformat = new SimpleDateFormat("yyyy-MM-dd");
        String end_time = sdateformat.format(new java.util.Date());
        furn_date.setText(end_time);
        new_time = end_time;
    }

    //选择运维人员
    public void selectpersonnel() {
        furn_name2 = "";
        furn_name2 = getIntent().getStringExtra("put_areament");
        //选择主要负责人
        furn_name_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Furnishtsak.this, Chooseperson.class);
                it.putExtra("put_areament", furn_name2);
                startActivity(it);
            }
        });
        //选择备用负责人
        furn_name_false2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Furnishtsak.this, Chooseperson.class);
                it.putExtra("put_areament", furn_name2);
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
                                new_time = "";
                                new_time = fmtDate.format(dateAndTime.getTime());
                                furn_date.setText(fmtDate.format(dateAndTime.getTime()));
                            }
                        },
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg.show();
            }
        });
    }

    //得到运维人员
    private void gettask_name() {
        get_name = getIntent().getStringExtra("choose_name");
        furn_name_true.setText(get_name);
        if (furn_name_true.length() > 1) {
            furn_name_false.setVisibility(View.INVISIBLE);
            furn_name_true.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(Furnishtsak.this, Chooseperson.class);
                    it.putExtra("put_areament", furn_name2);
                    startActivity(it);
                    finish();
                }
            });
        }
    }

    //新增一个任务
    private void addtask() {
        furn_name2 = "";
        furn_name2 = getIntent().getStringExtra("put_areament");
        //添加新的任务
        furn_add_newtsak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Furnishtsak.this);
                View view = getLayoutInflater().inflate(R.layout.furn_edit_dialog, null);
                furn_edit_newtask = (EditText) view.findViewById(R.id.furn_edit_newtask);
                builder.setNegativeButton(R.string.area_false, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton(R.string.area_true, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        add_task = furn_edit_newtask.getText().toString();
                        System.out.println(furn_edit_newtask.getText().toString());
                        Response.Listener<String> listener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                Toast.makeText(Furnishtsak.this, "发送成功", Toast.LENGTH_SHORT).show();
                            }
                        };
                        Response.ErrorListener errorListenner = new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(Furnishtsak.this, "发送失败,请检查", Toast.LENGTH_SHORT).show();
                            }
                        };
                        //http://api.pumintech.com:40000/app/add_new_task
                        String url2 = Url.path + "add_new_task?signature=1&eqpt_id=" + furn_name2;
                        StringRequest strrequest = new StringRequest(Request.Method.POST, url2, listener, errorListenner) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> map = new HashMap<String, String>();
                                map.put("task_name", add_task);
                                return map;
                            }
                        };
                        queue.add(strrequest);
                        getstat();  //重写请求
                    }

                });
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    //所有设备任务
    private void getstat() {
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
                    task_obj = new Furn_task_obj(obj.getString("task_name"), obj.getString("task_id"));
                    furn_task_array.add(task_obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        furn_adapter = new Furn_task_adapter(furn_task_array, this);
        furn_list.setAdapter(furn_adapter);
        ViewHeight.setListViewHeight(furn_list, furn_adapter);
        furn_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                task_obj = furn_task_array.get(position);
                get_task = task_obj.getFurn_task_name();
                get_taskid = task_obj.getFurn_task_id();
//                Toast.makeText(Furnishtsak.this, get_task, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //新增一条运维记录
    private void add_new_task() {
        name_drvid = "";
        name_drvid = getIntent().getStringExtra("put_areament");
        get_nameid = getIntent().getStringExtra("choose_id");
        furn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("设备ID：" + name_drvid + "    " +
                        "设备名字：" + name_drv + "    " +
                        "任务id：" + get_taskid + "      " +
                        "设备任务：" + get_task + "      " +
                        "开始日期：" + new_time + "      " +
                        "人员ID：" + get_nameid + "      " +
                        "人员姓名：" + get_name);
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(Furnishtsak.this, "发送成功", Toast.LENGTH_SHORT).show();
                    }
                };
                Response.ErrorListener errorListenner = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Furnishtsak.this, "发送失败,请检查", Toast.LENGTH_SHORT).show();
                    }
                };
                //http://api.pumintech.com:40000/app/add_new_task
                String url2 = Url.path + "add_temporary_task?signature=1&" +
                        "date=" + new_time + "&eqpt_id=" + name_drvid + "&pmt_id=" + get_nameid
                        + "&smt_id=S9002";
                StringRequest strrequest2 = new StringRequest(Request.Method.POST, url2, listener, errorListenner) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("date", new_time);
                        map.put("eqpt_id", name_drvid);
                        map.put("eqpt_name", name_drv);
                        map.put("task_id", get_taskid);
                        map.put("task_name", get_task);
                        map.put("pmt_id", get_nameid);
                        map.put("pmt_name", get_name);
                        map.put("finished", "N");
                        return map;
                    }
                };
                queue.add(strrequest2);
            }
        });
    }

}
