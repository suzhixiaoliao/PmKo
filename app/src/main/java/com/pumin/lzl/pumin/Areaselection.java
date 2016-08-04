package com.pumin.lzl.pumin;

import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.intentpumin.lsy.intentpumin.zxing.CaptureActivity;
import com.pumin.lzl.pumin.adapter.List_dialog_adapter;
import com.pumin.lzl.pumin.bean.Area_dialog_obj;
import com.pumin.lzl.pumin.utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/*
*@author lzl
*created at 2016/7/14 13:30
*  布置临时任务界面
*  通过搜索，扫描，选择区域来获得设备编号
*  得到设备编号然后把设备编号传入到布置任务界面
*/
public class Areaselection extends AppCompatActivity {
    Intent it;

    private ImageButton area_sm; //扫描
    private ImageButton area_ss; //搜索
    private EditText area_sr; //输入框

    //    数据添加
    List_dialog_adapter dialog_dapter;
    ArrayList<Area_dialog_obj> dialog_array = new ArrayList<>();
    Area_dialog_obj dialog_obj;
    private ListView area_listsj_dialog;
    //解析
    JSONObject jsonobj;
    JSONArray jsonarr;
    RequestQueue request;
    String info;
    //接收搜索设备的字符串
    String eqpt;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areaselection);
        request = Volley.newRequestQueue(this);  //得到volley对象
        initview();
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        qure();
        initlinear();
    }

    //初始化控件
    private void initview() {
        area_sm = (ImageButton) findViewById(R.id.area_sm);
        area_ss = (ImageButton) findViewById(R.id.area_ss);
        area_sr = (EditText) findViewById(R.id.area_sr);
    }

    //初始化监听事件
    private void initlinear() {
        //扫描设备 --得到二维码(编号)
        area_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                it = new Intent(Areaselection.this, CaptureActivity.class);
                it.putExtra("str_all", "3");
                startActivity(it);
                //put_areament   传的值
            }
        });
    }

    //借口请求
    private void qure() {
        eqpt = area_sr.getText().toString();
        String path = "";
        //把path转码-网路请求获取所有符合的设备
        try {
//            http://app.pumintech.com:40000/api/?signature=1
            path = Url.path + "get_eqpt_list_by_qry?signature=1&text_input=" + eqpt;
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(path, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // 成功获取数据后将数据显示在屏幕上
                        try {
                            info = response.toString();
                            // info = response.getString("UTF-8");
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Log.e("TAG", response + "" + "url");
                        geteqpt(info);
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
        request.add(jsonObjectRequest);
    }

    private void geteqpt(final String getinfo) {
        //搜索设备 --得到编号
        area_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_array = new ArrayList<>();

                //这里要生成一个dialog，再有dialog得到listview中设备到布置任务界面
                try {
                    jsonobj = new JSONObject(getinfo);
                    String w = jsonobj.getString("data");
                    jsonobj = new JSONObject(w);
                    String itm = jsonobj.getString("items");
                    jsonarr = new JSONArray(itm);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonarr.length() > 0 && jsonarr != null) {
                    for (int i = 0; i < jsonarr.length(); i++) {
                        try {
                            JSONObject obj = jsonarr.getJSONObject(i);
                            dialog_obj = new Area_dialog_obj(obj.getString("eqpt_name")
                                    , obj.getString("eqpt_id"));
                            dialog_array.add(dialog_obj);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                //dialog的显示
                AlertDialog.Builder builder = new AlertDialog.Builder(Areaselection.this);
                View view = getLayoutInflater().inflate(R.layout.area_list_dialog, null);
                 /*
                数据的适配
                 */
                area_listsj_dialog = (ListView) view.findViewById(R.id.area_listsj_dialog);
                dialog_dapter = new List_dialog_adapter(dialog_array, Areaselection.this);
                area_listsj_dialog.setAdapter(dialog_dapter);
                builder.setView(view);
                final AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
                //put_areament
                area_listsj_dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dialog_obj = dialog_array.get(position);
                        dialog.cancel();
                        Intent it = new Intent(Areaselection.this, Furnishtsak.class);
                        it.putExtra("put_areament", dialog_obj.getArea_id());
                        startActivity(it);
                    }
                });
            }
        });
    }
}
