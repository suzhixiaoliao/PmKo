package com.pumin.lzl.pumin;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.adapter.Operations_adapter;
import com.pumin.lzl.pumin.util.Alltitle;

import java.util.ArrayList;

/*
*@author lzl
*created at 2016/6/14 15:04
* 运维人员管理界面
*/
public class Management_Operations extends AppCompatActivity {

    Context context = this;
    private Alltitle operation_title;
    private TextView operation_name;
    private Button operation_enter;


    //适配器的数据适配
    ArrayList<String> operation_array=new ArrayList<>();
    Operations_adapter operations_adapter;
    private ListView operation_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);
        intiview();
        setOperation_title();
        initListener();
        intiadapter();
    }

    //设置标题
    private void setOperation_title() {
        operation_title.setTitle("运维人员管理");
    }

    //初始化控件
    private void intiview() {
        operation_title = (Alltitle) findViewById(R.id.operation_title);
        operation_name = (TextView) findViewById(R.id.operation_name);
        operation_enter = (Button) findViewById(R.id.operation_enter);
        operation_list= (ListView) findViewById(R.id.operation_list);
    }

    //初始化监听事件
    private void initListener() {
        operation_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("下一步整体架构还没出来");
                builder.setTitle("提示");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        });
    }
    //数据适配器
    private void intiadapter(){
        operation_array=new ArrayList<>();
        for(int i=0;i<10;i++){
            operation_array.add("");
        }
        operations_adapter=new Operations_adapter(context,operation_array);
        operation_list.setAdapter(operations_adapter);
    }

}
