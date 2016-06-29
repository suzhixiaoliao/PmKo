package com.pumin.lzl.pumin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.R;

/*
*@author lzl
*created at 2016/6/14 14:17
*  选择区域界面
*/
public class Region_admin extends AppCompatActivity {
    Context context=this;
    private TextView region_one;
    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_admin);
        initview();
        initListener();
    }

    //初始化控件
    private void initview() {
        region_one= (TextView) findViewById(R.id.region_one);
    }
    //初始化监听事件
    private void initListener(){
        region_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                it=new Intent(context,Management_activity.class);
                startActivity(it); //跳转到第一个区域
            }
        });
    }
}
