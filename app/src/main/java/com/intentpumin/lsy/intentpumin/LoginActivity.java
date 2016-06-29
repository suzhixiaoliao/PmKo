package com.intentpumin.lsy.intentpumin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.BaseLogic;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.network.LogUtils;
import com.intentpumin.lsy.intentpumin.tools.login;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private String userNameValue, passwordValue;
    private EditText et_name;
    private EditText et_psd;
    private ImageView btn_login;
    public  final static String SER_KEY = "com.intentpumin.lsy.intentpumin.ser";
    public  final static String PAR_KEY = "com.intentpumin.lsy.intentpumin.par";

    public static final String SETTING_INFOS = "SETTING_Infos";

    public static final String NAME = "NAME";

    public static final String PASSWORD = "PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        itiv();
        //判断网络连接状态
        if (isNetworkAvailable(LoginActivity.this)) {
            Toast.makeText(getApplicationContext(), "当前有可用网络！", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "当前没有可用网络！", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 检查当前网络是否可用
     */

    public boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


  private void itiv() {
      et_name = (EditText) findViewById(R.id.et_name);
      et_psd = (EditText) findViewById(R.id.et_psd);
      SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0); //获取一个SharedPreferences对象
      final String name = settings.getString(NAME, "");  //取出保存的NAME
      String password = settings.getString(PASSWORD, ""); //取出保存的PASSWORD
      et_name.setText(name);
      et_psd.setText(password);
      btn_login = (ImageView) findViewById(R.id.btn_login);
      btn_login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              final RequestParams params = new RequestParams();
              userNameValue = et_name.getText().toString();
              passwordValue = et_psd.getText().toString();
              params.addFormDataPart("signature", "1");
              params.addFormDataPart("phoneno", userNameValue);
              params.addFormDataPart("pswd", passwordValue);
              HttpUtil.getInstance().post(MainLogic.LOGIN, params, new StringHttpRequestCallback() {
                  @Override
                  public void onStart() {
                      super.onStart();
                  }

                  @Override
                  protected void onSuccess(String s) {
                      SharedPreferences settings = getSharedPreferences(SETTING_INFOS, 0); //首先获取一个SharedPreferences对象
                      settings.edit()
                              .putString(NAME, et_name.getText().toString())
                              .putString(PASSWORD,et_psd.getText().toString())
                              .commit();
                      //第一步
                      login login = new login();
                      try {
                          JSONObject content = new JSONObject(s);
                          JSONObject data = content.getJSONObject("data");
                          //第二步
                          login.setLevel_id(data.getString("level_id"));
                          login.setName(data.getString("name"));
                          login.setLevel_name(data.getString("level_name"));
                          login.setPriv(data.getString("priv"));
                          login.setType_id(data.getString("type_id"));
                          login.setType_name(data.getString("type_name"));
                          login.setUp_level_id(data.getString("up_level_id"));
                          login.setPhoneno(data.getString("phoneno"));
                      } catch (JSONException e) {
                          e.printStackTrace();
                      }
                      System.out.println(s);
                      if (login.getPriv().equals("0")) {
                          Intent i = new Intent(LoginActivity.this, YunWeiMainActivity.class);
                          //第三步
                          i.putExtra("login", (Serializable)login);
                          startActivity(i);
                      } else if (login.getPriv().equals("1")) {
                          Intent i = new Intent(LoginActivity.this, MainAdminActivity.class);
                          //第三步
                          i.putExtra("login", login);
                          startActivity(i);
                      }


                  }

              @Override
                  public void onFinish() {
                      System.out.println("错误");
                  }

              });
          }
      });

  }
}


