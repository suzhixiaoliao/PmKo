package com.intentpumin.lsy.intentpumin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.item_task;
import com.intentpumin.lsy.intentpumin.tools.login;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;
import okhttp3.Request;

public class TastComActivity extends Activity {
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
        final RequestParams params = new RequestParams();
        String remark = et_tast_com.getText().toString();
        final item_task  task = (item_task) getIntent().getSerializableExtra("task");
        params.addFormDataPart("signature", "1");
        params.addFormDataPart("phoneno", "13000000000");
        params.addFormDataPart("remark", remark);
        params.addFormDataPart("tast_id",task.getEqpt_id());
        params.addFormDataPart("eqpt_id",task.getTask_id());
        params.addFormDataPart("date","2016-06-07");
        HttpUtil.getInstance().post(MainLogic.LOGIN, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(String s) {
                Toast.makeText(TastComActivity.this, "备注成功" + task.getTask_name(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(TastComActivity.this, List_ZxingActivity.class);

        }

        @Override
            public void onFinish() {
                System.out.println("错误");
            }

        });

        }
}
