package com.pumin.lzl.pumin;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
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
import com.pumin.lzl.pumin.util.AllToast;
import com.pumin.lzl.pumin.util.Alltitle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/*
*@author lzl
*created at 2016/6/12 8:50
*    操作主界面
*/
public class Main_View extends AppCompatActivity implements View.OnClickListener {

    //context
    Context context = this;
    //Tag的提示
    private static final String TAG = "Main_view";
    //碎片的适配加载
    Task_adapter task_adapter;  //碎片的适配器
    private ArrayList<Fragment> adapters_list = new ArrayList<>();//保存碎片的集合
    Alternate_fragment alter_fragment;  //历史任务
    Futuremission_fragment futur_fragment; //未来任务碎片界面
    Historicaltask_fragment histor_fragment; //趋势
    LookState_Fragment lookState_fragment;  //查看状态的界面
    private ViewPager view_page;

    String str = "";

    //标题
    private Alltitle equipment;
    //flag表头
    private TextView historical_tv;
    private TextView lookstate_tv;
    private TextView alter_tv;
    private TextView future_tv;

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
        Toast.makeText(Main_View.this, "正在加载中....", Toast.LENGTH_SHORT).show();
        request = Volley.newRequestQueue(this);//得到volley对象
        initview();
        initTitle();
        FragmentIncident();
        query();
        callphone();
        setvalue();
        getimg(R.drawable.deive);
        view_page.setCurrentItem(1);  //创建时加载第二个界面
    }

    //初始化控件
    private void initview() {
        view_page = (ViewPager) findViewById(R.id.view_page);
        equipment = (Alltitle) findViewById(R.id.equipment);
        device_name = (TextView) findViewById(R.id.device_name);
        device_type = (TextView) findViewById(R.id.device_type);
        device_start_date = (TextView) findViewById(R.id.device_start_date);
        device_last_date = (TextView) findViewById(R.id.device_last_date);
        device_principal = (TextView) findViewById(R.id.device_principal);
        device_phone = (TextView) findViewById(R.id.device_phone);
        device_image = (ImageView) findViewById(R.id.device_image);
        historical_tv = (TextView) findViewById(R.id.historical_tv);
        lookstate_tv = (TextView) findViewById(R.id.lookstate_tv);
        alter_tv = (TextView) findViewById(R.id.alter_tv);
        future_tv = (TextView) findViewById(R.id.future_tv);

        historical_tv.setOnClickListener(this);
        lookstate_tv.setOnClickListener(this);
        alter_tv.setOnClickListener(this);
        future_tv.setOnClickListener(this);
    }


    //设置标题栏
    private void initTitle() {
        equipment.setTitle("设备信息");
        equipment.setLeftButton(null, R.drawable.down, new Alltitle.OnLeftButtonClickListener() {
            @Override
            public void onLeftBtnClick(View button) {
                finish();
            }
        }, null);
    }

    //碎片的加载
    private void FragmentIncident() {
        lookState_fragment = new LookState_Fragment();
        alter_fragment = new Alternate_fragment();
        futur_fragment = new Futuremission_fragment();
        histor_fragment = new Historicaltask_fragment();
        adapters_list.add(histor_fragment);
        adapters_list.add(lookState_fragment);
        adapters_list.add(alter_fragment);
        adapters_list.add(futur_fragment);

        task_adapter = new Task_adapter(getSupportFragmentManager(), adapters_list);
        view_page.setAdapter(task_adapter);  //适配器的适配
        view_page.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        textcolor(Color.BLUE, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);
                        break;
                    case 1:
                        textcolor(Color.TRANSPARENT, Color.BLUE, Color.TRANSPARENT, Color.TRANSPARENT);
                        break;
                    case 2:
                        textcolor(Color.TRANSPARENT, Color.TRANSPARENT, Color.BLUE, Color.TRANSPARENT);
                        break;
                    case 3:
                        textcolor(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.BLUE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        view_page.setOffscreenPageLimit(adapters_list.size());  //碎片全部加载
    }

    //flag点击事件
    @Override
    public void onClick(View v) {

    }

    //颜色的变化
    private void textcolor(int a, int b, int c, int d) {
        historical_tv.setBackgroundColor(a);
        lookstate_tv.setBackgroundColor(b);
        alter_tv.setBackgroundColor(c);
        future_tv.setBackgroundColor(d);
    }

    //请求
    public void query() {
        //把path转码
        try {
            str = getIntent().getStringExtra("put_equipment");
            //接口规范
//            http://app.pumintech.com:40000/api/user/?signature=1
//            http://10.16.1.201:40000/api/user/?signature=1
            path = "http://10.16.1.201:40000/api/user/get_eqpt_info?signature=1&eqpt_id=" + str;

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

//                        AllToast.alltoast(Gravity.CENTER, context, "加载完成", R.drawable.pmlogo);
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

    //图片的压缩
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    //假设图片
    private void getimg(int img) {
        device_image.setImageBitmap(decodeSampledBitmapFromResource(getResources(), img, 100, 100));
        imageonclick();
    }

    //预留接口--点击设备样子上传图片
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

            String type2_name = jsonObj.getString("next_rpd_date");
            device_type.setText(type2_name); //下次维修日期

            String start_date = jsonObj.getString("inuse_date");
            device_start_date.setText(start_date); //设备启用日期

            String last_date = jsonObj.getString("last_rpd_date");
            device_last_date.setText(last_date); //上次维修日期


            String principal = jsonObj.getString("respenser");
            device_principal.setText(principal);  //负责人


            String phone = jsonObj.getString("phoneno");
            device_phone.setText(phone); //联系号码

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
                Intent it = new Intent();
                it.setData(Uri.parse("tel:" + device_phone.getText().toString()));
                it.setAction(Intent.ACTION_CALL);
            }
        });

    }

}
