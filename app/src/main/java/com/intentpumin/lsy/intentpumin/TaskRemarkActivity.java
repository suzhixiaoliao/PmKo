package com.intentpumin.lsy.intentpumin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.task.task_get;
import com.intentpumin.lsy.intentpumin.tools.logindate.login;

import java.text.SimpleDateFormat;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;
//备注页面
public class TaskRemarkActivity extends Activity {
    private EditText et_tast_com;
    private Button bt_tast_qd;
    private Button bt_tast_qx;
    private com.intentpumin.lsy.intentpumin.tools.device.items items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskremark);
        items = (com.intentpumin.lsy.intentpumin.tools.device.items) getIntent().getSerializableExtra("item");
        if (items != null) {
            Log.e("DataExecute", "收到了" + items.toString());
        }
        init();
    }

    private void init() {
        et_tast_com= (EditText) findViewById(R.id.et_tast_com);
        bt_tast_qd= (Button) findViewById(R.id.bt_tast_qd);
        bt_tast_qx= (Button) findViewById(R.id.bt_tast_qx);
        bt_tast_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDate();
            }
        });
    }

    private void requestDate() {
        final login mlogin = (login) getIntent().getSerializableExtra("login");
        final RequestParams params = new RequestParams();
        String remark = et_tast_com.getText().toString();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        final task_get task = (task_get) getIntent().getSerializableExtra("task");
        params.addFormDataPart("signature", "1");
        params.addFormDataPart("phoneno", "13000000000");
        params.addFormDataPart("remark", remark);
        params.addFormDataPart("task_id",items.getTask_id());
        params.addFormDataPart("eqpt_id",items.getEqpt_id());
        params.addFormDataPart("date",date);
        HttpUtil.getInstance().post(MainLogic.SET_REMARK, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(String s) {
                Toast.makeText(TaskRemarkActivity.this, "您按下了确定键！" + task.task_name, Toast.LENGTH_SHORT).show();
              /*  Intent i = new Intent(TaskRemarkActivity.this, DataExecuteTasksActivity.class);*/

        }

        @Override
            public void onFinish() {
                System.out.println("错误");
            }

        });

        }
}
