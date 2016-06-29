package com.pumin.lzl.pumin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.zxing.CaptureActivity;
import com.pumin.lzl.pumin.util.Alltitle;


/*
*@author lzl
*created at 2016/6/12 8:51
*二维码的扫描与实现
*/
public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private ImageView sweep;  //扫一扫
    Intent it;

    private Alltitle main_title;
    Context context = this;

    //点击两次退出
    private long clickTime = 0; //记录第一次点击的时间
    private String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initview();
        initListener();
        setMain_title();

    }

    //初始化点击事件
    private void initListener() {
        sweep.setOnClickListener(this);
    }

    //控件的初始化
    private void initview() {
        main_title = (Alltitle) findViewById(R.id.main_title);
        sweep = (ImageView) findViewById(R.id.sweep);
    }


    //设置标题栏
    private void setMain_title() {
        main_title.setTitle("浦敏科技");
        main_title.setLeftButton(null, R.drawable.down, new Alltitle.OnLeftButtonClickListener() {
            @Override
            public void onLeftBtnClick(View button) {
                exit();
            }
        }, null);
        main_title.setRightButton(null, R.drawable.tag, new Alltitle.OnRightButtonClickListener() {
            @Override
            public void onRightBtnClick(View button) {
                Intent it=new Intent();
                startActivity(it);
            }
        }, null);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //获取二维码的信息
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            //扫描二维码得到值返回到第二个界面
            Intent it2 = new Intent(this, Main_View.class);
            it2.putExtra("put_equipment", bundle.get("result").toString());
            startActivity(it2);
//            Log.e(TAG, bundle.get("result").toString());
        } else {

        }
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sweep:
                //扫描二维码
                it = new Intent(context, CaptureActivity.class);
                startActivityForResult(it, 100);
                break;
            default:
                break;
        }
    }


    //点击两次才能退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次返回键退出程序",
                    Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            Log.e(TAG, "exit application");
            this.finish();
            //System.exit(0);
        }
    }
}

