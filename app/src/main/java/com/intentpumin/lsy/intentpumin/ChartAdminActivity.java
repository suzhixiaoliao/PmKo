package com.intentpumin.lsy.intentpumin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.Add_eqpt;
import com.intentpumin.lsy.intentpumin.tools.Add_stat;
import com.intentpumin.lsy.intentpumin.tools.StataValueResult;
import com.zhy.autolayout.AutoLayoutActivity;

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

public class ChartAdminActivity extends AutoLayoutActivity {
    String url = "http://app.pumintech.com:40000/api/user/get_stat_value";
    //趋势图
    private LineChart mChart;
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



    //当点击DatePickerDialog控件的设置按钮时，调用该方法
//    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear,
//                              int dayOfMonth) {
//            //修改日历控件的年，月，日
//            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
//            dateAndTime.set(Calendar.YEAR, year);
//            dateAndTime.set(Calendar.MONTH, monthOfYear);
//            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            //将页面TextView的显示更新为最新时间
//            upDateDate();
//        }
//    };
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
        //折线图控件//趋势图
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
                inter.setClass(ChartAdminActivity.this, AdminSSActivity.class);
                startActivityForResult(inter, 100);
            }
        });

        sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dateDlg = new DatePickerDialog(ChartAdminActivity.this,
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
                DatePickerDialog dateDlg = new DatePickerDialog(ChartAdminActivity.this,
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

    //初始化MPchart
    private Highlight[] temp0 = new Highlight[]{new Highlight(4, 1), new Highlight(4, 0)};

    private void setHight(int dataSetIndex, Highlight h) {
        for (int i = 0; i < temp0.length; i++) {
            if (temp0[i].getDataSetIndex() == dataSetIndex) {
                temp0[i] = h;
            }
        }
        mChart.highlightValues(temp0);
    }

    private void initChart() {
        mChart = (LineChart) findViewById(R.id.charts);
        mChart.setDrawGridBackground(false);
        mChart.setDescription("");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(false);
        mChart.setPinchZoom(false);
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.setLabelCount(5, true);
      /*  // 设置在Y轴上是否是从0开始显示
        leftAxis.setStartAtZero(true);*/
        leftAxis.setTextColor(0xffcccccc);
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawGridLines(true);
        mChart.getAxisLeft().setEnabled(true);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(8f);
        xAxis.setTextColor(0xffcccccc);
        xAxis.setDrawLabels(true);
        xAxis.setLabelsToSkip(0);
        YAxis axisRight = mChart.getAxisRight();
        axisRight.setEnabled(true);
        axisRight.setDrawGridLines(false);
        axisRight.setDrawLabels(false);
        mChart.getAxisRight().setEnabled(false);
        //setData(null);
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

        // set the marker to the chart
        mChart.setMarkerView(mv);

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);
        l.setEnabled(false);
        // // dont forget to refresh the drawing
        mChart.invalidate();
       /* mChart.highlightValue(1, 1);*/
        // mChart.highlightValue(0, 1);
        mChart.highlightValues(temp0);
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                setHight(dataSetIndex, h);
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

   /* private void setData(List<Data_Bean> list) {

        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        if (list == null || list.size() == 0) {
            xVals.add("0");
            yVals.add(new Entry(0, 0));
        } else {
            for (int i = 0; i < list.size(); i++) {
                xVals.add(list.get(i).getDate());
                yVals.add(new Entry(Float.valueOf(list.get(i).getR_value()), i));
            }

        }
        LineDataSet set1 = new LineDataSet(yVals, "");
        set1.setColor(getResources().getColor(R.color.bg_color));
        set1.setCircleColor(getResources().getColor(R.color.white));
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setFillColor(getResources().getColor(R.color.transparent));
        set1.setFillAlpha(0);
        set1.setDrawFilled(true);
        //相当于隐藏
        set1.setHighLightColor(getResources().getColor(R.color.transparent));

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets
        LineData data = new LineData(xVals, dataSets);
        // set data
        mChart.setData(data);

    *//*    //模拟点击，最后一个显示marker
        int px = (int) Util.dip2px(BaseApp.getInstance(), 15);
        setSimulateClick(mChart, -px, px);*//*

    }*/

    //折线图控件//趋势图
    private void requestData(List<String> eqptIds, List<String> statIds) {

        final String s_date = sdate.getText().toString().trim();
        System.out.print(sdate.getText().toString().trim());
        final String e_date = edate.getText().toString().trim();
        System.out.print(edate.getText().toString().trim());
        RequestParams params = new RequestParams();
        params.addFormDataPart("signature", "1");
        params.addFormDataPart("s_date",s_date);
        params.addFormDataPart("e_date",e_date);
       //params.addFormDataPart("eqpt_id", "47875318-1A24-2B35-2783-AE19D7334E2D,47875318-1A24-2B35-2783-AE19D7334E2D");
          params.addFormDataPart("eqpt_id", parseMaker(eqptIds));
        //   params.addFormDataPart("stat_id", "E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9" +
               // ",C82CF813-76AC-1C58-6163-79F33F4AC323");
       params.addFormDataPart("stat_id", parseMaker(statIds));


        HttpUtil.getInstance().post(MainLogic.GET_VALUE, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(String s) {
                Gson gson = new Gson();
                Type type = new TypeToken<StataValueResult>() {
                }.getType();
                StataValueResult result = gson.fromJson(s, type);
                System.out.println(s);
                // 这里
                if (result.getRes() == 1) {
                    //setData(result.getData().get(0).getData());
                    Toast.makeText(getApplicationContext(), "后台获取数据成功", Toast.LENGTH_SHORT).show();
                    mChart.invalidate();

                } else {
                    Toast.makeText(getApplicationContext(), result.getMsg(), Toast.LENGTH_SHORT).show();

                }

            }

            //初始化MPchart
            private Highlight[] temp0 = new Highlight[]{new Highlight(4, 1), new Highlight(4, 0)};

            //
            private void setHight(int dataSetIndex, Highlight h) {
                for (int i = 0; i < temp0.length; i++) {
                    if (temp0[i].getDataSetIndex() == dataSetIndex) {
                        temp0[i] = h;
                    }
                }
                mChart.highlightValues(temp0);
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
                LineDataSet set1 = new LineDataSet(yVals, "");
                set1.setColor(getResources().getColor(R.color.bg_color));
                set1.setCircleColor(getResources().getColor(R.color.white));
                set1.setLineWidth(1f);
                set1.setCircleRadius(3f);
                set1.setDrawCircleHole(false);
                set1.setValueTextSize(9f);
                set1.setFillColor(getResources().getColor(R.color.transparent));
                set1.setFillAlpha(0);
                set1.setDrawFilled(true);
                //相当于隐藏
                set1.setHighLightColor(getResources().getColor(R.color.transparent));

                ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                dataSets.add(set1); // add the datasets
                LineData data = new LineData(xVals, dataSets);
                // set data
                mChart.setData(data);

    *//*    //模拟点击，最后一个显示marker
        int px = (int) Util.dip2px(BaseApp.getInstance(), 15);
        setSimulateClick(mChart, -px, px);*//*

            }

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
            }*/

            public void onFailure(int errorCode, String msg) {
                System.out.println("error code " + errorCode + "\nmsg " + msg);
            }



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

