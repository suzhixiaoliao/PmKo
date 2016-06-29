package com.intentpumin.lsy.intentpumin.util;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineDataSet;
import com.intentpumin.lsy.intentpumin.tools.Add_value;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by yang on 2016/5/23.
 */
public class Chart {
   /* private LineChartView chart;
    private LineChartData data;
    private int numberOfLines = 2;
    private int maxNumberOfLines = 4;
    private int numberOfPoints = 12;
    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = false;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;
    private boolean pointsHaveDifferentColor;

    private LineChart chart;
    private ArrayList<String> xvals;
    private ArrayList<LineDataSet> dateSet;
    private List<Add_value> list;
    private  Add_value addV;

    //二位数组
    public final static String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec",};
    float[][] mData=new float[8][100];
    List<ArrayList<String>> mDataList=new ArrayList<>();*/

    /*private void generateData() {
        //循环来添加每一条折线
        List<Line> lines = new ArrayList<Line>();
        List<AxisValue> axisValues = new ArrayList<AxisValue>();
        for (int j = 0; j < months.length; j++) {

            //设置底部格式，也可以默认不设置，
            axisValues.add(new AxisValue(j).setLabel(months[j]));
        }

        int numberOfLines=2;
        for (int i = 0; i < numberOfLines; ++i) {

            List<StataValueResult.DataBean.Data_Bean> values = new ArrayList<StataValueResult.DataBean.Data_Bean>();

            for (int j = 0; j < numberOfPoints; j++) {

                //values.add(new StataValueResult.DataBean.Data_Bean(j, randomNumbersTab[i][j]));
                //设置底部格式，也可以默认不设置，
                axisValues.add(new AxisValue(j).setLabel(months[j]));
            }

//实例化线
            Line line = new Line((Line) values);
            //一些基础的设置，可以自己调整设置的值
            line.setColor(ChartUtils.COLORS[i]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            if (pointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            //将每条线保存到数组
            lines.add(line);
        }
//绑定折线的的数据
        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                //对X，Y的一些左边说明
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }
        data.setAxisXBottom(new Axis(axisValues));
//        data.setBaseValue(Float.NEGATIVE_INFINITY);
//        给控件赋值线
        chart.setLineChartData(data);

    }

    *//**
     * 设置一些默认值
     *//*
    private void reset() {
        hasAxes = true;
        hasAxesNames = true;
        hasLines = true;
        hasPoints = true;
        shape = ValueShape.CIRCLE;
        isFilled = false;
        hasLabels = false;
        isCubic = false;
        hasLabelForSelected = false;
        pointsHaveDifferentColor = false;

        chart.setValueSelectionEnabled(hasLabelForSelected);
        resetViewport();
    }

    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = 100;
        v.left = 0;
        v.right = numberOfPoints - 1;
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }

    *//**
     * 初始化数据
     *//*
    private void generateValues() {
        //循环最大条数，
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                //随机生成折线上的点
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }

    }*/
}
