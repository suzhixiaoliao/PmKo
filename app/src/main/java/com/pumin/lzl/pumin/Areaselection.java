package com.pumin.lzl.pumin;

import android.content.Intent;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.zxing.CaptureActivity;
import com.pumin.lzl.pumin.adapter.List_dialog_adapter;
import com.pumin.lzl.pumin.bean.Area_dialog_obj;
import com.pumin.lzl.pumin.utils.AllToast;
import com.pumin.lzl.pumin.utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
    //接收搜索设备的字符串
    String eqpt;
    private String info;
    String id;
    //百度地图
    // 定位相关
    //BMapManager 对象管理地图、定位、搜索功能
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;

    MapView mMapView;
    BaiduMap mBaiduMap;
    // UI相关
    boolean isFirstLoc = true;// 是否首次定位

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SDKInitializer.initialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        setContentView(R.layout.activity_areaselection);
        request = Volley.newRequestQueue(this);  //得到volley对象
        initview();
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        geteqpt();
        initlinear();
        mylocation();//定位
    }
    //初始化控件
    private void initview() {
        mMapView = (MapView) findViewById(R.id.my_map);
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

    //点击之后查询
    private void geteqpt() {
        //搜索设备 --得到编号
        area_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eqpt = area_sr.getText().toString();
                //把path转码-网路请求获取所有符合的设备
                if (eqpt.length() > 1) {
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
                    Toast.makeText(Areaselection.this, "请输入设备编号或设备名", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    /**
     * 开启定位
     */
    public void mylocation(){
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        // 地图初始化
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }
    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    public void getinfo(String infos) {
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
                    dialog_obj = new Area_dialog_obj(obj.getString("eqpt_name")
                            , obj.getString("eqpt_id"));
                    dialog_array.add(dialog_obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            AllToast.alltoast(Gravity.CENTER, Areaselection.this, "该设备不存在，请重新输入");
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
//                dialog.setCancelable(false);
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
}
