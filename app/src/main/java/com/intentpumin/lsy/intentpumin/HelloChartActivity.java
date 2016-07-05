package com.intentpumin.lsy.intentpumin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intentpumin.lsy.intentpumin.activity.BaseActivity;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.tools.Add_eqpt;
import com.intentpumin.lsy.intentpumin.tools.Add_stat;
import com.intentpumin.lsy.intentpumin.tools.data.base.DataBean;
import com.intentpumin.lsy.intentpumin.tools.data.base.ItemsBean;
import com.intentpumin.lsy.intentpumin.tools.data.base.Rustatvalue;
import com.zhy.autolayout.AutoLayoutActivity;

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
        listview = (ListView) findViewById(R.id.lv_xs);
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
                inter.setClass(HelloChartActivity.this, AdminSSActivity.class);
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
                Type type = new TypeToken<Rustatvalue>() {
                }.getType();
                Rustatvalue result = gson.fromJson(s, type);
                System.out.println(s);
                // 这里
                if (result.getRes() == 1) {
                    Toast.makeText(getApplicationContext(), "后台获取数据成功", Toast.LENGTH_SHORT).show();
                    List<DataBean> datas = result.getData();
                    lineChartView.setLineChartData(generateLineChartData("", "", datas));
                    listData.clear();
                    listData.addAll(result.getData());
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
    private LineChartData generateLineChartData(String eqpt_name, String stat_name, List<DataBean> datas) {
        //构建20个点的数据
        List<Line> lines = new ArrayList<>();
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
            DataBean itemData = datas.get(index);
            List<ItemsBean> itemsData = itemData.getItems();
            for (int i = 0; i < itemsData.size(); i++) {
                ItemsBean itemsBean = itemsData.get(i);
                //三目運算符 如果不是怎麼樣 那麼就怎麼樣
                String rValue = "".equals(itemsBean.getItem().getR_value()) ? "0": itemsBean
                        .getItem().getR_value();
               // values.add(new PointValue(i,(float) Double.parseDouble(rValue)) == 0 ? Float.NaN :(Double.parseDouble(rValue)));
                values.add(new PointValue(i,(float)(Float.parseFloat(rValue)) == 0? Float.NaN : (Float.parseFloat(rValue))));
                Log.d("key", "value = " + rValue);
                String date = itemsData.get(index).getDate();
                dates.add(date);
            }

            //设置日期
            distanceAxis.setValues(getAxisValues(itemsData));
            //初始化一条线
            Line line = new Line(values);
            line.setColor(getResources().getColor(datas.get(index).isChecked() ? R.color.shenlan : R.color.qianlan));
            line.setHasLabels(true);
            //将线装入集合中
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

    //初始化x轴的值
    public List<AxisValue> getAxisValues(List<ItemsBean> hours) {
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

    private List<DataBean> listData = new ArrayList<>();

    private class ChartAdapter extends BaseAdapter {
        private ViewHolder holder;

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public DataBean getItem(int position) {
            return listData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DataBean dataBean = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(HelloChartActivity.this).inflate(R.layout.layout_item, null);
                holder = new ViewHolder(convertView);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.driverNameTv.setText("eqpt " + dataBean.getEqpt_name());
            holder.driverStatusTv.setText("status " + dataBean.getStat_name());
            convertView.setOnClickListener(new ItemListener(dataBean));
            return convertView;
        }

        private class ViewHolder {
            public TextView driverNameTv;
            public TextView driverStatusTv;

            public ViewHolder(View convertView) {
                driverNameTv = (TextView) convertView.findViewById(R.id.tv_driver_name);
                driverStatusTv = (TextView) convertView.findViewById(R.id.tv_driver_status);
                convertView.setTag(this);
            }

        }
    }

    private class ItemListener implements View.OnClickListener {
        private final DataBean dataBean;

        public ItemListener(DataBean dataBean) {
            this.dataBean = dataBean;
        }

        @Override
        public void onClick(View v) {
            lineChartView.setLineChartData(generateLineChartData(dataBean.getEqpt_name(), dataBean.getStat_name(), listData));
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

