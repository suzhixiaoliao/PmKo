package com.intentpumin.lsy.intentpumin;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.network.LogUtils;

import com.intentpumin.lsy.intentpumin.zxing.CaptureActivity;

import com.intentpumin.lsy.intentpumin.tools.logindate.login;
import com.pumin.lzl.pumin.Areaselection;
import com.pumin.lzl.pumin.Main_View;
import com.pumin.lzl.pumin.Processclose;
import com.pumin.lzl.pumin.adapter.List_dialog_adapter;
import com.pumin.lzl.pumin.bean.Area_dialog_obj;
import com.pumin.lzl.pumin.utils.AllToast;
import com.pumin.lzl.pumin.utils.Url;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class MainAdminActivity extends AppCompatActivity {
    private TextView tv_main;
    private ImageView iv_ss;
    private TextView tv_return;
    private ImageView iv_scan;
    private ImageView iv_renwu;
    private ImageButton main_ss;
    private EditText main_sr;
    //    数据添加
    List_dialog_adapter dialog_dapter_main;
    ArrayList<Area_dialog_obj> dialog_array = new ArrayList<>();
    Area_dialog_obj dialog_obj_main;
    private ListView area_listsj_dialog_main;
    //解析
    JSONObject jsonobj;
    JSONArray jsonarr;
    RequestQueue request;
    String info;
    String eqpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        request = Volley.newRequestQueue(this);  //得到volley对象
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_main = (TextView) findViewById(R.id.tv_main);
        //第四步
        iv_scan = (ImageView) findViewById(R.id.iv_scan);
        iv_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入到扫描界面。
                Intent it = new Intent(MainAdminActivity.this, CaptureActivity.class);
                it.putExtra("str_all", "1");
                startActivity(it);
            }
        });

        iv_renwu = (ImageView) findViewById(R.id.iv_renwu);
        iv_renwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入部署任务界面(搜索，扫描，选择)
                Intent it = new Intent(MainAdminActivity.this, Areaselection.class);
                startActivity(it);
            }
        });

        login mlogin = (login) getIntent().getSerializableExtra("login");
        tv_main.setText(mlogin.getName() + ",您好");
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
                        Intent i = new Intent(MainAdminActivity.this, LoginActivity.class);
                        startActivity(i);
                        LogUtils.LOGD("login", s.toString());
                    }

                    @Override
                    public void onFinish() {
                        System.out.println("");
                    }
                });

            }

        });
        //查看保修流程
        iv_ss = (ImageView) findViewById(R.id.iv_ss);
        iv_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainAdminActivity.this, Processclose.class);
                startActivity(it);
            }
        });
        main_sr = (EditText) findViewById(R.id.main_sr);
        main_ss = (ImageButton) findViewById(R.id.main_ss);
        main_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eqpt = main_sr.getText().toString();
                if (main_sr.length() > 1) {
                    String path = "";
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
                                    getinfo(info);
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
                } else {
                    Toast.makeText(MainAdminActivity.this, "请输入设备编号或设备名", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //
    private void getinfo(String infos) {
        dialog_array = new ArrayList<>();
        //这里要生成一个dialog，再有dialog得到listview中设备到布置任务界面
        try {

            System.out.println(infos + "xxxxxxxxxxxxxxxxxxxxxxxxxx");
            jsonobj = new JSONObject(infos);
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
                    dialog_obj_main = new Area_dialog_obj(obj.getString("eqpt_name")
                            , obj.getString("eqpt_id"));
                    dialog_array.add(dialog_obj_main);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            AllToast.alltoast(Gravity.CENTER, MainAdminActivity.this, "该设备不存在，请重新输入");
        }
        //dialog的显示
        AlertDialog.Builder builder = new AlertDialog.Builder(MainAdminActivity.this);
        View view = getLayoutInflater().inflate(R.layout.area_list_dialog, null);
                 /*
                数据的适配
                 */
        area_listsj_dialog_main = (ListView) view.findViewById(R.id.area_listsj_dialog);
        dialog_dapter_main = new List_dialog_adapter(dialog_array, MainAdminActivity.this);
        area_listsj_dialog_main.setAdapter(dialog_dapter_main);
        builder.setView(view);
        final AlertDialog dialogs = builder.create();
        dialogs.show();
//        //put_areament
        area_listsj_dialog_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog_obj_main = dialog_array.get(position);
                Intent it = new Intent(MainAdminActivity.this, Main_View.class);
                it.putExtra("put_equipment", dialog_obj_main.getArea_id());
                startActivity(it);
            }
        });
    }
}