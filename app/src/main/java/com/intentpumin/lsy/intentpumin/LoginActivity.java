package com.intentpumin.lsy.intentpumin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intenpumin.lsy.intentpumin.repairs.MainRepairsActivity;
import com.intentpumin.lsy.intentpumin.activity.BaseActivity;
import com.intentpumin.lsy.intentpumin.activity.BcBaseActivity;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.logindate.login;
import com.intentpumin.lsy.intentpumin.util.DeviceInfo;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

  private void init() {
      et_name = (EditText) findViewById(R.id.et_name);
      et_psd = (EditText) findViewById(R.id.et_pwd);
      cb_pwd= (CheckBox) findViewById(R.id.cb_pwd);
      cb_auto= (CheckBox) findViewById(R.id.cb_auto);
      btn_login = (TextView) findViewById(R.id.tv_login_x);

      if (sp.getBoolean("ISCHECK", false)) {
          //记住密码框状态标记为选中
          cb_pwd.setChecked(true);
          userNameValue=sp.getString("USER_NAME", "");
          passwordValue=sp.getString("PASSWORD", "");
              et_name.setText(userNameValue);
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
              if (!DeviceInfo.isNetAvailable()) {
                  showToast(R.string.tip_no_internet);
                  return;
              }

              if (et_name.length() == 0) {
                  et_name.setError("请输入手机号码");
                  et_name.requestFocus();
                  return;
              }
              if (et_psd.length() == 0) {
                  et_psd.setError("请输入密码");
                  et_psd.requestFocus();
                  return;
              }

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
                             showProgressDialog(R.string.tip_communicating);
                          }

                          @Override
                          protected void onSuccess(String s) {
                              //如果记住密码框未选中状态
                              if (cb_pwd.isChecked()) {
                                  SharedPreferences.Editor editor = sp.edit();
                                  editor.putString("USER_NAME", userNameValue);
                                  editor.putString("PASSWORD", passwordValue);
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
              sp.edit().putString("name", login.getName()).putString("phoneno", login.getPhoneno())
                      .putString("priv", login.getPriv()).commit();
              if (login.getPriv().equals("2")) {
                  Intent i = new Intent(LoginActivity.this, MainActivity.class);
                  //第三步
                  i.putExtra("login", (Serializable) login);
                  startActivity(i);
              } else if (login.getPriv().equals("3")) {
                  Intent i = new Intent(LoginActivity.this, MainRepairsActivity.class);
                  //第三步
                  i.putExtra("login", (Serializable) login);
                  startActivity(i);
              }
          }

          @Override
          public void onFailure(int errorCode, String msg) {
              Toast.makeText(LoginActivity.this, "用户名或者密码有误，请重新输入！", Toast.LENGTH_SHORT).show();
              System.out.println("error code " + errorCode + "\nmsg " + msg);
          }

          @Override
          public void onFinish() {
             cancelProgressDialog();
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


