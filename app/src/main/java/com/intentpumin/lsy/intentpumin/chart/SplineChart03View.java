package com.intentpumin.lsy.intentpumin.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.tools.StataValueResult;

import org.xclcharts.chart.CustomLineData;
import org.xclcharts.chart.PointD;
import org.xclcharts.chart.SplineChart;
import org.xclcharts.chart.SplineData;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.event.click.PointPosition;
import org.xclcharts.renderer.XEnum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SplineChart03View extends DemoView {

    private SplineChart chart = new SplineChart();

    //分类轴标签集合
    //日期轴
    private LinkedList<String> labels = new LinkedList<String>();
    //数据轴
    private LinkedList<SplineData> chartDate = new LinkedList<SplineData>();
    //点击显示轴
    private Paint mPaintTooltips = new Paint(Paint.ANTI_ALIAS_FLAG);

    //Splinechart支持横向和竖向定制线
    private List<CustomLineData> mXCustomLindateset = new ArrayList<CustomLineData>();
    private List<CustomLineData> yXCustomLindateset = new ArrayList<CustomLineData>();

    public SplineChart03View(Context context) {
        super(context);
        initView();
    }

    public SplineChart03View(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SplineChart03View(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        chart.setCategoryAxisMax(100);
        chart.setCategoryAxisMin(10);
        chartDataSet();
        chartLabels();
        chartRender();
        //绑定手势滑动事件
        this.bindTouch(this, chart);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //图所占范围大小
        chart.setChartRange(w, h);
    }

    private void chartRender() {
        try {

            int[] ltrb = getBarLnDefaultSpadding();
            chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);

            //显示边框
            chart.showRoundBorder();

            //数据源
            chart.setCategories(labels);
            chart.setDataSource(chartDate);

            //坐标系
            //数轴轴最大值
            chart.getDataAxis().setAxisMax(100);
            chart.getDataAxis().setAxisMin(0);

            //数据轴刻度间隔
            chart.getDataAxis().setAxisSteps(10);
            chart.setCustomLines(yXCustomLindateset);//x轴

//标签轴最大值
            chart.getDataAxis().setAxisMax(100);
            chart.getDataAxis().setAxisMin(0);

            //数据轴刻度间隔
            chart.getDataAxis().setAxisSteps(10);
            chart.setCustomLines(mXCustomLindateset);//y轴

            //設置背景顏色
            chart.setApplyBackgroundColor(true);
            chart.setBackgroundColor(R.color.colorPrimary);
            chart.getBorder().setBorderLineColor(R.color.colorAccent);

            //调轴线与网格线风格
            chart.getCategoryAxis().hideTickMarks();
            chart.getDataAxis().hideAxisLine();
            chart.getDataAxis().hideTickMarks();
            chart.getPlotGrid().showHorizontalLines();

            chart.getCategoryAxis().getAxisPaint().setColor(chart.getPlotGrid().getHorizontalLinePaint().getColor());
            chart.getCategoryAxis().getAxisPaint().setStrokeWidth(chart.getPlotGrid().getHorizontalLinePaint().getStrokeWidth());

            chart.setDotLabelFormatter(new IFormatterTextCallBack() {
                @Override
                public String textFormatter(String value) {
                    String label = "[" + value + "]";
                    return (label);
                }
            });
//标题
            chart.setTitle("浦敏科技");

            //点击激活监听
            chart.ActiveListenItemClick();
            //为了让触发更灵敏，可以扩大5px的点击监听范围
            chart.extPointClickRange(5);
            chart.showClikedFocus();

            //显示平滑曲线
            chart.setCrurveLineStyle(XEnum.CrurveLineStyle.BEZIERCURVE);

            //图例显示子啊正下方
            chart.getPlotLegend().setVerticalAlign(XEnum.VerticalAlign.BOTTOM);
            chart.getPlotLegend().setHorizontalAlign(XEnum.HorizontalAlign.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Main", e.toString());

        }
    }

    private void chartDataSet() {
    }

    //x轴
    private void chartLabels() {
        labels.add("12");
    }

    private int lineLables = 0;

    public void setData(StataValueResult result) {
       /* List<String> inlabels = new ArrayList<>();
        for (DataBean data : result.getData()) {
            int abels = 0;
            if (data != null) {
                List<PointD> inlabel = new ArrayList<PointD>();
                //泛型 <>
                for (Data_Bean dataPoint : data.getData()) {
                    abels += 10;
                    //inlabels.add(new PointD(abels, Double.parseDouble(date.getDate())));
                    inlabels.add(dataPoint.getDate2());
                }
                labels.clear();
                labels.addAll(inlabels);
            }
        }
        int lineIndex = 0;

        for (DataBean lineData : result.getData()) {
            int index = 0;
            lineIndex++;
            switch (lineIndex) {
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }

            if (lineData != null) {
                List<PointD> linePoint = new ArrayList<PointD>();
                for (Data_Bean dataPoint : lineData.getData()) {
                    index += 10;
                    linePoint.add(new PointD(index, Double.parseDouble(dataPoint.getR_value())));

                }

                SplineData dataSeries = new SplineData("线", linePoint,
                        getContext().getResources().getColor(R.color.black_overlay));

                chartDate.add(dataSeries);
            }
        }
*/
    }

    @Override
    public void render(Canvas canvas) {
        super.render(canvas);
        try {
            chart.render(canvas);
        } catch (Exception e) {
            Log.e("Main", e.toString());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            triggerClick(getX(), getY());
        }
        return true;
    }

    //触发监听
    private void triggerClick(float x, float y) {
        if (!chart.getListenItemClickStatus()) return;

        PointPosition record = chart.getPositionRecord(x, y);
        if (null == record) return;

        if (record.getDataID() >= chartDate.size()) return;
        SplineData lData = chartDate.get(record.getDataID());
        List<PointD> linePoint = lData.getLineDataSet();
        int pos = record.getDataChildID();
        int i = 0;
        Iterator it = linePoint.iterator();
        while (it.hasNext()) {
            PointD entry = (PointD) it.next();

            if (pos == i) {
                Double xValue = entry.x;
                Double yValue = entry.y;

                float r = record.getRadius();
                chart.showFocusPointF(record.getPosition(), r + r * 0.8f);
                chart.getFocusPaint().setStyle(Style.FILL);
                chart.getFocusPaint().setStrokeWidth(3);
                if (record.getDataID() >= 2) {
                    chart.getFocusPaint().setColor(Color.BLUE);
                } else {
                    chart.getFocusPaint().setColor(Color.RED);
                }
                //在点击处显示tooltip
                mPaintTooltips.setColor(Color.RED);
                chart.getToolTip().setCurrentXY(x, y);
                chart.getToolTip().addToolTip(" Key:" + lData.getLineKey(), mPaintTooltips);
                chart.getToolTip().addToolTip(
                        " Current Value:" + Double.toString(xValue) + "," + Double.toString(yValue), mPaintTooltips);
                chart.getToolTip().getBackgroundPaint().setAlpha(100);
                this.invalidate();

                break;
            }
            i++;
        }
    }

}

