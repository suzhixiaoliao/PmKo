package com.intentpumin.lsy.intentpumin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.intentpumin.lsy.intentpumin.activity.BaseActivity;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.logindate.login;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private String userNameValue, passwordValue;
    private EditText et_name;
    private EditText et_psd;
    private CheckBox cb_pwd,cb_auto;
    private TextView btn_login;
    SharedPreferences sp;
    public static final String SETTING_INFOS = "SETTING_Infos";
    public static final String NAME = "NAME";
    public static final String PASSWORD = "PASSWORD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp=this.getSharedPreferences("user",Context.MODE_PRIVATE);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
        //判断网络连接状态
        if (isNetworkAvailable(LoginActivity.this)) {
            Toast.makeText(getApplicationContext(), "当前有可用网络！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "当前没有可用网络！", Toast.LENGTH_SHORT).show();
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


  private void init() {
      et_name = (EditText) findViewById(R.id.et_name);
      et_psd = (EditText) findViewById(R.id.et_pwd);
      cb_pwd= (CheckBox) findViewById(R.id.cb_pwd);
      cb_auto= (CheckBox) findViewById(R.id.cb_auto);
      btn_login = (TextView) findViewById(R.id.tv_login_x);

      //判断记住密码框状态
      //判断记住密码框状态
      if (sp.getBoolean("ISCHECK", false)) {
          //记住密码框状态标记为选中
          cb_pwd.setChecked(true);

          try {
              userNameValue=sp.getString("USER_NAME", "");
              et_name.setText(userNameValue);
          } catch (Exception e) {
              Toast.makeText(LoginActivity.this, "用户名异常", Toast.LENGTH_SHORT).show();
              e.printStackTrace();
          }
          try {
              passwordValue=sp.getString("PASSWORD", "");
          } catch (Exception e) {
              Toast.makeText(LoginActivity.this,"密码解密异常",Toast.LENGTH_SHORT).show();
              e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
          }
          et_psd.setText(passwordValue);
          //判断自动登录框状态
          if(sp.getBoolean("AUTO_ISCHECK", false))
          {
              cb_auto.setChecked(true);

          }
      }
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
                      //如果记住密码框未选中状态
                      if(cb_pwd.isChecked())
                      {
                          SharedPreferences.Editor editor = sp.edit();

                          try {
                              editor.putString("USER_NAME", userNameValue);
                          } catch (Exception e) {
                              Toast.makeText(LoginActivity.this,"用户名加密异常",Toast.LENGTH_SHORT).show();
                          }
                          try {
                              editor.putString("PASSWORD",passwordValue);
                          } catch (Exception e) {
                              Toast.makeText(LoginActivity.this,"密码加密异常",Toast.LENGTH_SHORT).show();
                              e.printStackTrace();
                          }
                          editor.commit();
                      }
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
                          Intent i = new Intent(LoginActivity.this, MainActivity.class);
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
                  login login=new login();
                 if (login.getPriv()==""){
                   //Toast.makeText(LoginActivity.this,"用户名或者密码有误，请重新输入！",Toast.LENGTH_SHORT).show();
               }
               }
              });
          }
      });
      //标记记住密码框状态
    cb_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
              if (cb_pwd.isChecked()) {

                  System.out.println("记住密码框未选中状态");
                  sp.edit().putBoolean("ISCHECK", true).commit();

              }else {

                  System.out.println("记住密码框未选中");
                  sp.edit().putBoolean("ISCHECK", false).commit();

              }

          }
      });

      //标记自动登录框状态
      cb_auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if (cb_auto.isChecked()) {
                  System.out.println("自动登录功能以启用");
                  sp.edit().putBoolean("AUTO_ISCHECK", true).commit();

              } else {
                  System.out.println("自动登录已关闭");
                  sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
              }
          }
      });


  }
}


