package com.pumin.lzl.pumin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.adapter.Management_adapter;
import com.pumin.lzl.pumin.util.Alltitle;

import java.util.ArrayList;

/*
*@author lzl
*created at 2016/6/14 14:16
*   设备管理界面→ 将会跳转到运维人员管理统界面
*/
public class Management_activity extends AppCompatActivity {

    private ListView managment_list;
    private Alltitle managment_title;
    private TextView managment_name;    //管理人名字
    private TextView managment_equipment;  //设备的总共数
    private Button managment_select;

    Context context = this;
    Intent it;

    //适配器
    Management_adapter manage_adapter;
    ArrayList<String> manage_array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        initview();
        setManagment_title();
        setManage_adapter();
        initListener();
    }

    //初始化控件
    private void initview() {
        managment_title = (Alltitle) findViewById(R.id.managment_title);
        managment_name = (TextView) findViewById(R.id.managment_name);
        managment_equipment = (TextView) findViewById(R.id.managment_equipment);
        managment_select = (Button) findViewById(R.id.managment_select);
        managment_list = (ListView) findViewById(R.id.managment_list);
    }

    //设置标题
    private void setManagment_title() {
        managment_title.setTitle("设备管理");
    }

    //初始化控件的点击事件
    private void initListener() {
        managment_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳转到运维人员管理
                it = new Intent(context, Management_Operations.class);
                startActivity(it);
            }
        });
    }

    private void setManage_adapter() {
        manage_array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            manage_array.add("");
        }
        manage_adapter = new Management_adapter(context, manage_array);
        managment_list.setAdapter(manage_adapter);

        //listview长按事件
        managment_list.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return true;
            }
        });
    }
}
