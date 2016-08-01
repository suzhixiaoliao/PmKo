package com.intenpumin.lsy.intentpumin.repairs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intenpumin.lsy.intentpumin.repairs.Adapter.ImageAdapter;
import com.intenpumin.lsy.intentpumin.repairs.image.ImageHandleUtils;
import com.intenpumin.lsy.intentpumin.repairs.util.MaxLengthWatcher;
import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.activity.BaseActivity;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.stat.result_stat_get;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;
import me.iwf.photopicker.PhotoPickerActivity;

public class MainRepairsActivity extends BaseActivity
        implements View.OnClickListener,AdapterView.OnItemClickListener {
    private static final int REQUEST_CODE = 1;
    private GridView vPhoto;
    private FloatingActionButton vPickImage;
    private MaxLengthWatcher et_shuru;
    private List<String> photoPaths = new ArrayList<>();
    private ImageAdapter mAdapter;
    private TextView tv_fs;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupData() {
       setContentView(R.layout.activity_main_repairs,R.string.repairs,MODE_NO_NAVIGATION);
        initView();
    }

    private void initView() {

        tv_fs= (TextView) findViewById(R.id.tv_shuru);
        et_shuru= (MaxLengthWatcher) findViewById(R.id.et_shuru);
        vPickImage = (FloatingActionButton) findViewById(R.id.vPickImage);
        vPhoto = (GridView) findViewById(R.id.photos);
        mAdapter = new ImageAdapter(this, photoPaths);
        vPhoto.setAdapter(mAdapter);
        vPickImage.setOnClickListener(this);
        vPhoto.setOnItemClickListener(this);
        tv_fs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });

    }


    @Override
    public void onClick(View v) {
        boolean showTakePhotoItem =true;
        int id=v.getId();
        if (id == R.id.vPickImage) {
            int imageCount;
            Toast.makeText(this, "默认选择4张", Toast.LENGTH_SHORT).show();
            imageCount = 4;
            Intent intent = ImageHandleUtils.pickMultiImage(this, imageCount,showTakePhotoItem);
            this.startActivityForResult(intent, REQUEST_CODE);
        }else {
            Intent intent =ImageHandleUtils.pickSingleImage(this,true);
            this.startActivityForResult(intent,REQUEST_CODE);
        }

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null) {
                photoPaths.clear();
                photoPaths.addAll(data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS));
                mAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = ImageHandleUtils.previewImage(this, (ArrayList<String>) photoPaths, position);
        startActivityForResult(intent, REQUEST_CODE);
    }
    private void initData() {
        RequestParams params = new RequestParams();
        String date = "2015-07-26 12:22:00";
        String eqpt_id="FA0101001";
        String et_input=et_shuru.getText().toString();
        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        String mPhoneno= sp.getString("phoneno", "");
        String repair_priv="9";
        String process_flag="00001";

        params.addFormDataPart("process_flag",process_flag);
        params.addFormDataPart("repair_priv",repair_priv);
        params.addFormDataPart("signature", 1);
        params.addFormDataPart("date", date);
        params.addFormDataPart("repair_sent_user_id", mPhoneno);
        params.addFormDataPart("eqpt_id",eqpt_id );
        params.addFormDataPart("alm_comment",et_input);
        HttpUtil.getInstance().post(MainLogic.SET_REMARK, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }
            @Override
            protected void onSuccess(String s) {
                // TODO: 2016/6/22 获取数据接口返回服务器的数据
                System.out.println("onSuccess=======" + s);
                System.out.println(s);
            }

            @Override
            public void onFinish() {
                System.out.println("完成");
            }
        });
    }
}
