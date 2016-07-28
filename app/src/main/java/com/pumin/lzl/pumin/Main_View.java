package com.pumin.lzl.pumin;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.pumin.lzl.pumin.adapter.Task_adapter;

import com.pumin.lzl.pumin.fragment.Alternate_fragment;
import com.pumin.lzl.pumin.fragment.Futuremission_fragment;
import com.pumin.lzl.pumin.fragment.Historicaltask_fragment;
import com.pumin.lzl.pumin.fragment.LookState_Fragment;
import com.pumin.lzl.pumin.utils.AllToast;
import com.pumin.lzl.pumin.utils.Alldot_layout;
import com.pumin.lzl.pumin.utils.Alltitle;
import com.pumin.lzl.pumin.utils.F_image;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/*
*@author lzl
*created at 2016/6/12 8:50
*    操作主界面--
*/
public class Main_View extends AppCompatActivity {
    //context
    Context context;
    //Tag的提示
    private static final String TAG = "Main_view";

    //碎片的适配加载
    Task_adapter task_adapter;  //碎片的适配器
    private ArrayList<Fragment> adapters_list = new ArrayList<Fragment>();//保存碎片的集合
    Alternate_fragment alter_fragment;  //历史任务
    Futuremission_fragment futur_fragment; //未来任务碎片界面
    Historicaltask_fragment histor_fragment; //趋势
    LookState_Fragment lookState_fragment;  //查看状态的界面
    private ViewPager view_page;

    String str = "";

    private ImageButton main_back;

    //flag
    private LinearLayout dot_layout;

    //设备基本信息
    private TextView device_name; //设备名称
    private TextView device_type; //设备信号
    private TextView device_start_date; //启用日期
    private TextView device_last_date; //上次维修
    private TextView device_principal; //负责人
    private TextView device_phone; //电话号码
    private ImageView device_image; //设备图片

    //json解析
    RequestQueue request;
    JSONObject jsonObj;
    String info;
    String path = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main__view);
        request = Volley.newRequestQueue(this);//得到volley对象
        initview();
        initTitle();
        FragmentIncident();
        query();
        callphone();
        setvalue();
        Alldot_layout.dot(this, adapters_list, dot_layout); //创建点的视图
        view_page.setCurrentItem(1);  //创建时加载第二个界面
    }

    //初始化控件
    private void initview() {
        view_page = (ViewPager) findViewById(R.id.view_pager);
        device_name = (TextView) findViewById(R.id.device_name);
        device_type = (TextView) findViewById(R.id.device_type);
        device_start_date = (TextView) findViewById(R.id.device_start_date);
        device_last_date = (TextView) findViewById(R.id.device_last_date);
        device_principal = (TextView) findViewById(R.id.device_principal);
        device_phone = (TextView) findViewById(R.id.device_phone);
        device_image = (ImageView) findViewById(R.id.device_image);
        dot_layout = (LinearLayout) findViewById(R.id.dot_layout);
        main_back = (ImageButton) findViewById(R.id.main_back);
    }


    //设置标题栏
    private void initTitle() {
        main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //碎片的加载
    private void FragmentIncident() {
        histor_fragment = new Historicaltask_fragment();
        lookState_fragment = new LookState_Fragment();
        alter_fragment = new Alternate_fragment();
        futur_fragment = new Futuremission_fragment();
        adapters_list.add(histor_fragment);
        adapters_list.add(lookState_fragment);
        adapters_list.add(alter_fragment);
        adapters_list.add(futur_fragment);

        task_adapter = new Task_adapter(getSupportFragmentManager(), adapters_list);
        view_page.setAdapter(task_adapter);  //适配器的适配
//        view_page.setOffscreenPageLimit(adapters_list.size());  //碎片全部加载
        view_page.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //滑动事件改变圆点的状态。(黑白分明，让用户理解自己现在处在第几个位置)
                updateinfo();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //圆点的变化(黑白)
    private void updateinfo() {
        int currentpage = view_page.getCurrentItem() % adapters_list.size();
        for (int i = 0; i < dot_layout.getChildCount(); i++) {
            dot_layout.getChildAt(i).setEnabled(i == currentpage);
        }
    }

    //请求接口
    public void query() {
        try {
            str = getIntent().getStringExtra("put_equipment");
            //接口规范
//            http://app.pumintech.com:40000/api/user/?signature=1
//            http://10.16.1.201:40000/api/user/?signature=1
            //表名:S_EQPT_M
//            path = "http://10.16.1.201:40000/api/user/get_eqpt_info?signature=1&eqpt_id=" + str;

            path = "http://app.pumintech.com:40000/api/user/get_eqpt_info?signature=1&eqpt_id=" + str;

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
                        getjson(info);

                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.getMessage(), error);
                AllToast.alltoast(Gravity.CENTER, context, "加载失败,请检查网络是否通畅", R.drawable.pmlogo);
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


    //预留接口--点击设备样子(进行拍照)上传图片
    private void imageonclick() {
        device_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击打开相册
                Intent intent = new Intent();
                intent.setAction("android.media.action.IMAGE_CAPTURE");
                intent.addCategory("android.intent.category.DEFAULT");
                startActivity(intent);
                File file = new File(Environment.getExternalStorageDirectory() + "/000.jpg");
                Uri uri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivity(intent);
            }
        });
    }


    //照完相-处理照片代码
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //json解析-加载了设备的基本信息
    private void getjson(String s_json) {
        try {
            jsonObj = new JSONObject(s_json);
            String data = jsonObj.getString("data");
            jsonObj = new JSONObject(data);

            String name = jsonObj.getString("eqpt_name");
            device_name.setText(name); //设备名称

            String ids = jsonObj.getString("eqpt_id");
            F_image.image_s(ids, device_image, this);

            String type2_name = jsonObj.getString("next_rpd_date");
            device_type.setText(type2_name); //下次维修日期

            String start_date = jsonObj.getString("inuse_date");
            device_start_date.setText(start_date); //设备启用日期

            String last_date = jsonObj.getString("last_rpd_date");
            device_last_date.setText(last_date); //上次维修日期


            String principal = jsonObj.getString("respenser");
            device_principal.setText(principal);  //负责人


            String phone = jsonObj.getString("phoneno");
            device_phone.setText(Html.fromHtml("<u>" + phone + "</u>")); //联系号码


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //从主main中传值到fragment中---传递数据各司其职
    private void setvalue() {
        Bundle bundle1 = new Bundle();
        bundle1.putString("set_url", str);
        futur_fragment.setArguments(bundle1);
        alter_fragment.setArguments(bundle1); //传值
        lookState_fragment.setArguments(bundle1);
    }

    //拨打电话
    private void callphone() {
        device_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + device_phone.getText().toString()));
                if (ActivityCompat.checkSelfPermission(Main_View.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(it);
            }
        });
    }

}
