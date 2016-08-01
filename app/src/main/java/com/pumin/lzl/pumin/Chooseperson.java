package com.pumin.lzl.pumin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.adapter.Choose_adapter;
import com.pumin.lzl.pumin.bean.Choose_object;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseperson);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initview();
        init();
        initback();
    }
    //销毁当前页面
    private void initback(){
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


    private void init() {
        choose_array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            choose_obj = new Choose_object("运维人员" + i);
            choose_array.add(choose_obj);
        }
        choose_ada = new Choose_adapter(choose_array, this);
        choose_list.setAdapter(choose_ada);
    }

}
