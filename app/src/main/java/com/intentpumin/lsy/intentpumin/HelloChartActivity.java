package com.intentpumin.lsy.intentpumin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intentpumin.lsy.intentpumin.activity.BaseActivity;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.alldevice.devices_all;
import com.intentpumin.lsy.intentpumin.tools.allstat.stats_all;
import com.intentpumin.lsy.intentpumin.tools.value.result_values_all_get;
import com.intentpumin.lsy.intentpumin.tools.value.values_all_items;
import com.intentpumin.lsy.intentpumin.tools.value.values_devices_get;
import com.intentpumin.lsy.intentpumin.util.Stats_icon;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;


public class HelloChartActivity extends BaseActivity {
    private ListView listview;
    private ChartAdapter chartadapter;
    private LineChartView lineChartView;
    List<Line> lines = new ArrayList<>();
    //日期
    Button et_yunxing;
    //获取日期格式器对象
    DateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd");

    //定义一个TextView控件对象
    TextView sdate = null;
    TextView edate = null;
    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);


    //需要查询的设备ID集合
    List<String> eqptIds = new ArrayList<>();

    //需要查询的状态集合
    List<String> statIds = new ArrayList<>();


    ChartManeger chartManeger;


    Map<devices_all, List<stats_all>> resData = new LinkedHashMap<>();

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
    }
    protected void setupData() {
        setContentView(R.layout.activity_adminchar, R.string.mCharts, MODE_BACK_NAVIGATION);
        init();
        initChart();
    }

    private void init() {
        listview = (ListView) findViewById(R.id.lv_xs);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return true;
            }
        });
        chartadapter = new ChartAdapter();
        listview.setAdapter(chartadapter);
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
                inter.setClass(HelloChartActivity.this, AdminDevicesActivity.class);
                startActivityForResult(inter, 100);
            }
        });
        sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dateDlg = new DatePickerDialog(HelloChartActivity.this,
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
                DatePickerDialog dateDlg = new DatePickerDialog(HelloChartActivity.this,
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
        lineChartView = (LineChartView) findViewById(R.id.charts);
        chartManeger = new ChartManeger(lineChartView);


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
        params.addFormDataPart("stat_id", parseMaker(statIds));
        HttpUtil.getInstance().post(MainLogic.GET_VALUE, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(String s) {
                Gson gson = new Gson();
                Type type = new TypeToken<result_values_all_get>() {
                }.getType();
                result_values_all_get result = gson.fromJson(s, type);
                System.out.println(s);
                // 这里


                if (result.getRes() == 1) {
                    //Toast.makeText(getApplicationContext(), "后台获取数据成功", Toast.LENGTH_SHORT).show();
                    List<values_devices_get> datas = result.getData();
                    System.out.println("datas = " + datas);
                    lineChartView.setLineChartData(generateLineChartData("", "", datas));


//                    listData.clear();
//                    listData.addAll(result.getData());
                    chartadapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), result.getMsg(), Toast.LENGTH_SHORT).show();
                }
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


    /**
     * 移除一根线
     */
    private void removeLine() {

    }


    private LineChartData generateLineChartData(String eqpt_name, String stat_name, List<values_devices_get> datas) {


        int[] lineColor = new int[]{
                Color.parseColor("#32BB7F"),
                Color.parseColor("#BB6BAC"),
                Color.parseColor("#226FA7"),
                Color.parseColor("#FFB061"),
                Color.parseColor("#F4659D"),
                Color.parseColor("#939EAA")};


        //构建20个点的数据
        //
        // value.
        Axis distanceAxis = new Axis();
        for (int index = 0; index < datas.size(); index++) {
            if ("".equals(eqpt_name) && "".equals(stat_name)) {
                if (index == 1) {
                    datas.get(index).setIsChecked(true);
                } else {
                    datas.get(index).setIsChecked(false);
                }
            } else {
                if (datas.get(index).getEqpt_name().equals(eqpt_name) && datas.get(index).getStat_name().equals(stat_name)) {
                    datas.get(index).setIsChecked(true);
                } else {
                    datas.get(index).setIsChecked(false);
                }
            }
            List<PointValue> values = new ArrayList<>();
            List<String> dates = new ArrayList<>();
            values_devices_get itemData = datas.get(index);
            List<values_all_items> itemsData = itemData.getItems();
            for (int i = 0; i < itemsData.size(); i++) {
                values_all_items valuesallitems = itemsData.get(i);
                //三目運算符 如果不是怎麼樣 那麼就怎麼樣
                String rValue = "".equals(valuesallitems.getItem().getR_value()) ? "0" : valuesallitems
                        .getItem().getR_value();
               /*String rValue = valuesallitems.getItem().getR_value();

                if (rValue==null){
                    Toast.makeText(this,"里面有空数据,请重新查询",Toast.LENGTH_SHORT).show();


                }else{
                    valuesallitems.getItem().getR_value();
                }*/
                //values.add(new PointValue(i,(float) Double.parseDouble(rValue)) == 0 ? Float.NaN :(Double.parseDouble(rValue)));

                values.add(new PointValue(i, (Float.parseFloat(rValue)) == 0 ? Float.NaN : (Float.parseFloat(rValue))));
                Log.d("key", "value = " + rValue);
                //String date = itemsData.get(index).getDate();
                String date = valuesallitems.getDate();
                Log.d("itemdate", itemData.toString());
                dates.add(date);
            }

            //设置日期
            distanceAxis.setValues(getAxisValues(itemsData));
            //初始化一条线
            ChartLine line = new ChartLine(values);


            line.setColor(lineColor[index]);

//            line.setColor(getResources().getColor(datas.get(index).isChecked() ? R.color.shenlan : R.color.qianlan));
            line.setHasLabels(true);
            line.values_devices_get = datas.get(index);


            //将线装入集合中
            //需要显示的线才加入集合中
            lines.add(line);
        }

        LineChartData data = new LineChartData(lines);
        // value.
//        distanceAxis.setName("Distance");
        //设置底部x轴字体的颜色
        distanceAxis.setTextColor(ChartUtils.COLOR_ORANGE);
        distanceAxis.setMaxLabelChars(1);

        distanceAxis.setHasLines(true);
        distanceAxis.setHasTiltedLabels(true);
        distanceAxis.setName("   ");
        //设置底部
        data.setAxisXBottom(distanceAxis);
        data.setAxisYLeft(new Axis().setName(" ").setHasLines(true));
        return data;

    }


    private void setChart() {

    }


    //初始化x轴的值
    public List<AxisValue> getAxisValues(List<values_all_items> hours) {
        int numSubcolumns = 1;
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < hours.size(); ++i) {
            for (int j = 0; j < numSubcolumns; j++) {
                axisValues.add(new AxisValue(i, getFormatDate(hours.get(i).getDate()).toCharArray()));
            }
        }
        return axisValues;
    }


    private String getFormatDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-HH-dd");
        try {
            Date newDate = simpleDateFormat.parse(date);
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH-dd");
            return dateFormat.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

//    private List<values_devices_get> listData = new ArrayList<>();

    private class ChartAdapter extends BaseAdapter {
        private ViewHolder holder;

        @Override
        public int getCount() {
            return lines.size();
        }

        @Override
        public values_devices_get getItem(int position) {
            return ((ChartLine) lines.get(position)).values_devices_get;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final values_devices_get valuesdevicesget = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(HelloChartActivity.this).inflate(R.layout.item_value_list, null);
                holder = new ViewHolder(convertView);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.driverNameTv.setText( valuesdevicesget.getEqpt_name());
            holder.driverStatusTv.setText(valuesdevicesget.getStat_name());


            if (((ChartLine) lines.get(position)).show) {

                int resId = Stats_icon.getStatIcon(valuesdevicesget.getStat_id());
                holder.pictureIv.setImageResource(resId);

            } else {
                int resId = Stats_icon.getUnStatIcon(valuesdevicesget.getStat_id());
                holder.pictureIv.setImageResource(resId);
            }
            convertView.setOnClickListener(new ItemListener(valuesdevicesget, position));
            return convertView;
        }

        private class ViewHolder {
            public TextView driverNameTv;
            public TextView driverStatusTv;
            public ImageView pictureIv;

            public ViewHolder(View convertView) {
                driverNameTv = (TextView) convertView.findViewById(R.id.tv_driver_name);
                driverStatusTv = (TextView) convertView.findViewById(R.id.tv_driver_status);
                pictureIv = (ImageView) convertView.findViewById(R.id.iv_picture);
                convertView.setTag(this);
            }

        }
    }

    private class ItemListener implements View.OnClickListener {
        private final values_devices_get valuesdevicesget;
        private int position;


        public ItemListener(values_devices_get valuesdevicesget, int position) {
            this.valuesdevicesget = valuesdevicesget;
            this.position = position;
        }

        @Override
        public void onClick(View v) {

            chartManeger.refresh();

            chartManeger.toggleShow(position);
            chartadapter.notifyDataSetChanged();


        }
    }

    private class ValueTouchListener implements
            LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex,
                                    PointValue value) {
        }

        @Override
        public void onValueDeselected() {
        }
    }

    //设备信息
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 100 && resultCode == 101) {

            resData = (Map<devices_all, List<stats_all>>) data.getBundleExtra("ids").getSerializable("checkList");
            System.out.println("add_eqpt selected size is " + resData.size());
            eqptIds.clear();
            statIds.clear();


            for (Map.Entry<devices_all, List<stats_all>> entry : resData.entrySet()) {
                for (stats_all stat : entry.getValue()) {
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

