package com.pumin.lzl.pumin;

import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.text.method.DateKeyListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.adapter.Furn_task_adapter;
import com.pumin.lzl.pumin.bean.Furn_task_obj;
import com.pumin.lzl.pumin.utils.ViewHeight;

import java.text.DateFormat;
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
    //得到所有数据传输
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furnishtsak);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initview();
        init();
        initback();
        selectpersonnel();
        myscroll.smoothScrollBy(0, 0);  //设置位置
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

    public void selectpersonnel() {
        furn_name_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Furnishtsak.this, Chooseperson.class);
                startActivity(it);
                furn_name_false.setVisibility(View.INVISIBLE);
            }
        });
        furn_name_false2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Furnishtsak.this, Chooseperson.class);
                startActivity(it);
                furn_name_false2.setVisibility(View.INVISIBLE);
            }
        });
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
    }


    //得到数据方法
    private void init() {
        furn_task_array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            task_obj = new Furn_task_obj("测试设备" + i);
            furn_task_array.add(task_obj);
        }
        furn_adapter = new Furn_task_adapter(furn_task_array, this);
        furn_list.setAdapter(furn_adapter);
        ViewHeight.setListViewHeight(furn_list, furn_adapter);
    }
}
