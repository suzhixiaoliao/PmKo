package com.intentpumin.lsy.intentpumin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.task.task_get;
import com.intentpumin.lsy.intentpumin.tools.logindate.login;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class TaskRemarkActivity extends Activity {
    private EditText et_tast_com;
    private Button bt_tast_qd;
    private Button bt_tast_qx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tast_com);
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
        final task_get task = (task_get) getIntent().getSerializableExtra("task");
        params.addFormDataPart("signature", "1");
        params.addFormDataPart("phoneno", "13000000000");
        params.addFormDataPart("remark", remark);
        params.addFormDataPart("task_id","AEE3A2FA-FB85-D9E1-673B-089DAEF9CCC4");
        params.addFormDataPart("eqpt_id","47875315-1A24-2B35-2783-AE19D7334E2D");
        params.addFormDataPart("date","2016-06-21");
        HttpUtil.getInstance().post(MainLogic.SET_REMARK, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(String s) {
                Toast.makeText(TaskRemarkActivity.this, "您按下了确定键！" + task.getTask_name(), Toast.LENGTH_SHORT).show();
              /*  Intent i = new Intent(TaskRemarkActivity.this, DataExecuteTasksActivity.class);*/

        }

        @Override
            public void onFinish() {
                System.out.println("错误");
            }

        });

        }
}
