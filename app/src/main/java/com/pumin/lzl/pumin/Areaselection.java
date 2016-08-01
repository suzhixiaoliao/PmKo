package com.pumin.lzl.pumin;

import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.zxing.CaptureActivity;
import com.pumin.lzl.pumin.adapter.List_dialog_adapter;
import com.pumin.lzl.pumin.bean.Area_dialog_obj;

import java.util.ArrayList;
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

    private LinearLayout area_linear;
    private ImageButton area_sm; //扫描
    private ImageButton area_ss; //搜索
    private EditText area_sr; //输入框

    //    数据添加
    List_dialog_adapter dialog_dapter;
    ArrayList<Area_dialog_obj> dialog_array = new ArrayList<>();
    Area_dialog_obj dialog_obj;
    private ListView area_list_dialog;
    //解析
    RequestQueue request;

    //接收搜索设备的字符串
    String eqpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areaselection);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        request = Volley.newRequestQueue(this);  //得到volley对象
        initview();
        initlinear();
        area_linear.getBackground().setAlpha(255); //设置透明度
    }

    //初始化控件
    private void initview() {
        area_linear = (LinearLayout) findViewById(R.id.area_linear);
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

        //搜索设备 --得到编号
        area_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eqpt = area_sr.getText().toString();

                //这里要生成一个dialog，再有dialog得到listview中设备到布置任务界面
                AlertDialog.Builder builder = new AlertDialog.Builder(Areaselection.this);
                builder.setTitle("符合条件的设备 :");
                View view = getLayoutInflater().inflate(R.layout.area_list_dialog, null);
                area_list_dialog = (ListView) view.findViewById(R.id.area_listsj_dialog);
                dialog_array = new ArrayList<>();
                for (int i = 1; i < 10; i++) {
                    dialog_obj = new Area_dialog_obj("端子箱" + i);
                    dialog_array.add(dialog_obj);
                }
                /*
                数据的适配
                 */
                dialog_dapter = new List_dialog_adapter(dialog_array, getApplication());
                area_list_dialog.setAdapter(dialog_dapter);

                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();
                area_list_dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView c = (TextView) view.findViewById(R.id.area_name);
                        String name = c.getText().toString();
                        Intent it = new Intent(Areaselection.this, Furnishtsak.class);
                        it.putExtra("put_areament", name);
                        startActivity(it);
                        Toast.makeText(Areaselection.this, "得到数据：" + name, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
