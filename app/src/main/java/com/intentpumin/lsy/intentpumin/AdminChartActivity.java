package com.intentpumin.lsy.intentpumin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intentpumin.lsy.intentpumin.chart.LineChart02View;
import com.intentpumin.lsy.intentpumin.chart.SplineChart03View;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.Add_eqpt;
import com.intentpumin.lsy.intentpumin.tools.Add_stat;
import com.intentpumin.lsy.intentpumin.tools.StataValueResult;
import com.intentpumin.lsy.intentpumin.tools.data.base.Rustatvalue;


import org.xclcharts.chart.LineChart;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class AdminChartActivity extends AppCompatActivity {
  //  private LineChart02View mline;
    private TextView tv_ss;

    //日期
    Button et_yunxing;
    //获取日期格式器对象
    DateFormat fmtDate = new java.text.SimpleDateFormat("yyyy-MM-dd");

    //定义一个TextView控件对象
    TextView sdate = null;
    TextView edate = null;
    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);


    //需要查询的设备ID集合
    List<String> eqptIds = new ArrayList<>();

    //需要查询的状态集合
    List<String> statIds = new ArrayList<>();


    Map<Add_eqpt, List<Add_stat>> resData = new LinkedHashMap<>();

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {

        //同DatePickerDialog控件
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            upDateTime();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_char);
        init();
        initChart();
    }

    private void init() {
        sdate = (TextView) findViewById(R.id.tv_date_start);
        //日期
        sdate.setClickable(true);
        sdate.setFocusable(true);
        edate = (TextView) findViewById(R.id.tv_date_end);
        edate.setClickable(true);
        edate.setFocusable(true);
        et_yunxing = (Button) findViewById(R.id.et_yunxing);
        et_yunxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inter = getIntent();
                inter.setClass(AdminChartActivity.this, AdminSSActivity.class);
                startActivityForResult(inter, 100);
            }
        });

        sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dateDlg = new DatePickerDialog(AdminChartActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                dateAndTime.set(Calendar.YEAR, year);
                                dateAndTime.set(Calendar.MONTH, monthOfYear);
                                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                sdate.setText(fmtDate.format(dateAndTime.getTime()));

                            }
                        },
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));

                dateDlg.show();

            }
        });
        edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dateDlg = new DatePickerDialog(AdminChartActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateAndTime.set(Calendar.YEAR, year);
                                dateAndTime.set(Calendar.MONTH, monthOfYear);
                                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                edate.setText(fmtDate.format(dateAndTime.getTime()));
                            }
                        },
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg.show();
            }
        });
        upDateDate();
        upDateTime();


    }

    private void upDateDate() {
        sdate.setText(fmtDate.format(dateAndTime.getTime()));
    }

    private void upDateTime() {
        edate.setText(fmtDate.format(dateAndTime.getTime()));
    }


    private void initChart() {
        //折线图控件//趋势图
       //mline= (LineChart02View) findViewById(R.id.charts);
    }
    private void requestData(List<String> eqptIds, List<String> statIds) {

        final String s_date = sdate.getText().toString().trim();
        System.out.print(sdate.getText().toString().trim());
        final String e_date = edate.getText().toString().trim();
        System.out.print(edate.getText().toString().trim());
        RequestParams params = new RequestParams();
        params.addFormDataPart("signature", "1");
        params.addFormDataPart("s_date", s_date);
        params.addFormDataPart("e_date", e_date);
        params.addFormDataPart("eqpt_id", parseMaker(eqptIds));
        // params.addFormDataPart("eqpt_id", "47875318-1A24-2B35-2783-AE19D7334E2D,47875318-1A24-2B35-2783-AE19D7334E2D");
       params.addFormDataPart("stat_id", parseMaker(statIds));
        // params.addFormDataPart("stat_id", "E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9,C82CF813-76AC-1C58-6163-79F33F4AC323");
        HttpUtil.getInstance().post(MainLogic.GET_VALUE, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(String s) {
                Gson gson = new Gson();
                Type type = new TypeToken<Rustatvalue>() {
                }.getType();
                Rustatvalue result = gson.fromJson(s, type);
                System.out.println(s);
                // 这里
                if (result.getRes() == 1) {
                    Toast.makeText(getApplicationContext(), "后台获取数据成功", Toast.LENGTH_SHORT).show();
                 //   mline.setData(result);
                   // mline.refreshChart();

                } else {
                    Toast.makeText(getApplicationContext(), result.getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

           /* private void setData(List<Data_Bean> list) {

                ArrayList<String> xVals = new ArrayList<String>();
                ArrayList<Entry> yVals = new ArrayList<Entry>();
                if (list == null || list.size() == 0) {
                    xVals.add("0");
                    yVals.add(new Entry(0, 0));
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        xVals.add(list.get(i).getDate().substring(5, 10));
                        yVals.add(new Entry(Float.valueOf(list.get(i).getR_value()), i));
                    }

                }

            }*/

            private void setSimulateClick(View view, float x, float y) {
                long downTime = SystemClock.uptimeMillis();
                final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
                        MotionEvent.ACTION_DOWN, x, y, 0);
                downTime += 1000;
                final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
                        MotionEvent.ACTION_UP, x, y, 0);
                view.onTouchEvent(downEvent);
                view.onTouchEvent(upEvent);
                downEvent.recycle();
                upEvent.recycle();
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                System.out.println("error code " + errorCode + "\nmsg " + msg);
            }


            @Override
            public void onFinish() {
                //结束刷新
                System.out.println("完成");
            }
        });
    }

    //设备信息
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 100 && resultCode == 101) {

            resData = (Map<Add_eqpt, List<Add_stat>>) data.getBundleExtra("ids").getSerializable("checkList");
            System.out.println("add_eqpt selected size is " + resData.size());
            eqptIds.clear();
            statIds.clear();


            for (Map.Entry<Add_eqpt, List<Add_stat>> entry : resData.entrySet()) {
                for (Add_stat stat : entry.getValue()) {
                    eqptIds.add(entry.getKey().getEqpt_id());
                    statIds.add(stat.getStat_id());
                }
            }
            if (eqptIds.size() > 0) {
                requestData(eqptIds, statIds);
            }

        }
    }

    /**
     * 参数构造
     *
     * @return
     */
    public String parseMaker(List<String> parseList) {
        StringBuffer strParse = new StringBuffer();
        for (String s : parseList) {
            strParse.append(s + ",");
        }
        return strParse.toString().subSequence(0, strParse.length() - 1).toString();
    }

}



