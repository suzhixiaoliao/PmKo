package com.pumin.lzl.pumin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.intentpumin.lsy.intentpumin.R;
/*
*@author lzl
*created at 2016/7/14 13:30
*  布置临时任务界面
*  通过搜索，扫描，选择区域来获得设备编号
*  得到设备编号然后把设备编号传入到布置任务界面
*/
public class Areaselection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areaselection);
    }
}
