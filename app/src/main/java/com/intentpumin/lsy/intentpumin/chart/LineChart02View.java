/**
 * Copyright 2014  XCL-Charts
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 	
 * @Project XCL-Charts 
 * @Description Android图表基类库
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @Copyright Copyright (c) 2014 XCL-Charts (www.xclcharts.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 1.0
 */
package com.intentpumin.lsy.intentpumin.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.intentpumin.lsy.intentpumin.tools.StataValueResult;
import com.intentpumin.lsy.intentpumin.tools.data.base.DataBean;
import com.intentpumin.lsy.intentpumin.tools.data.base.ItemBean;
import com.intentpumin.lsy.intentpumin.tools.data.base.ItemsBean;
import com.intentpumin.lsy.intentpumin.tools.data.base.Rustatvalue;

import org.xclcharts.chart.CustomLineData;
import org.xclcharts.chart.LineChart;
import org.xclcharts.chart.LineData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XEnum;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LineChart01View
 * @Description  折线图的例子 <br/>
 *  * 	~_~
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class LineChart02View extends DemoView implements Runnable{
	
	private String TAG = "LineChart02View";
	private LineChart chart = new LineChart();
	
	//标签集合
	private LinkedList<String> labels = new LinkedList<String>();
	private LinkedList<LineData> chartData = new LinkedList<LineData>();
	private List<CustomLineData> mCustomLineDataset = new LinkedList<CustomLineData>();

	public LineChart02View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}
	
	public LineChart02View(Context context, AttributeSet attrs){   
        super(context, attrs);   
        initView();
	 }
	 
	 public LineChart02View(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView()
	 {
			chartRender();
			new Thread(this).start();
			
			//綁定手势滑动事件
			this.bindTouch(this, chart);
	 }
	 
	
	@Override  
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(w, h, oldw, oldh);  
       //图所占范围大小
        chart.setChartRange(w, h);
    }  

	private void chartRender()
	{
		try {				
						
			//设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....		
			int [] ltrb = getBarLnDefaultSpadding();
			chart.setPadding(DensityUtil.dip2px(getContext(), 45),ltrb[1], ltrb[2],  ltrb[3]);
		
			
			//设定数据源
			chart.setCategories(labels);								
		//	chart.setDataSource(chartData);
			//chart.setCustomLines(mCustomLineDataset);
			
			//数据轴最大值
			chart.getDataAxis().setAxisMax(100);
			//数据轴刻度间隔
			chart.getDataAxis().setAxisSteps(10);
			//指隔多少个轴刻度(即细刻度)后为主刻度
			chart.getDataAxis().setDetailModeSteps(1);
					
			//背景网格
			chart.getPlotGrid().showHorizontalLines();
			chart.getDataAxis().showFirstTick();
			
			//标题
			chart.setTitle("运维支撑系统");
			chart.addSubtitle("(浦敏科技)");
			
			//隐藏顶轴和右边轴
			//chart.hideTopAxis();
			//chart.hideRightAxis();
			
			//设置轴风格
		
			//chart.getDataAxis().setTickMarksVisible(false);
			chart.getDataAxis().getAxisPaint().setStrokeWidth(2);
			chart.getDataAxis().getTickMarksPaint().setStrokeWidth(2);
			chart.getDataAxis().showAxisLabels();
			
			chart.getCategoryAxis().getAxisPaint().setStrokeWidth(2);
			chart.getCategoryAxis().hideTickMarks();
												
			//定义数据轴标签显示格式
			chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
	
				@Override
				public String textFormatter(String value) {
					// TODO Auto-generated method stub		
					Double tmp = Double.parseDouble(value);
					DecimalFormat df=new DecimalFormat("#0");
					String label = df.format(tmp).toString();				
					return (label);
				}
				
			});
			
			
			//定义线上交叉点标签显示格式
			chart.setItemLabelFormatter(new IFormatterDoubleCallBack() {
				@Override
				public String doubleFormatter(Double value) {
					// TODO Auto-generated method stub
					DecimalFormat df=new DecimalFormat("#0");					 
					String label = df.format(value).toString();
					return label;
				}});
			
			//chart.setItemLabelFormatter(callBack)
						
			//允许线与轴交叉时，线会断开
			chart.setLineAxisIntersectVisible(false);
			
			//chart.setDataSource(chartData); 
			//动态线									
			chart.showDyLine();
			
			//不封闭
			chart.setAxesClosed(false);
			
			//扩展绘图区右边分割的范围，让定制线的说明文字能显示出来
			chart.getClipExt().setExtRight(150.f);
			
			//设置标签交错换行显示
			chart.getCategoryAxis().setLabelLineFeed(XEnum.LabelLineFeed.ODD_EVEN);
			
			//仅能横向移动
			chart.setPlotPanMode(XEnum.PanMode.HORIZONTAL);
			
			
			//chart.getDataAxis().hide();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}
	
	//计算下平均线
	private double calcAvg()
	{
		double total = 400d + 480d + 500d + 560d + 800d + 950d +1200d + 630d + 710d;
		double yearNumber = 9d;
		
		return (total/yearNumber);
	}
	/*public void setData(Rustatvalue result) {
		List<String> inlabels = new ArrayList<>();
		for (ItemsBean data : result.getData()) {
			int abels = 0;
			if (data != null) {
				List<LineData> inlabel = new ArrayList<LineData>();
				//泛型 <>
				for (ItemBean dataPoint : data.getItem()) {
					abels += 10;
					inlabels.add(dataPoint.getDate());
				}
				labels.clear();
				labels.addAll(inlabels);
			}
		}
		for (ItemsBean lineData : result.getData()) {
			int index = 0;
			if (lineData != null) {
				List<Double> linePoint = new ArrayList<Double>();
				for (ItemBean dataPoint :lineData.getDate()) {
					index += 10;
					linePoint.add(new Double(Double.parseDouble(dataPoint.getR_value())));
				}
				LineData lineDate = new LineData("pumin",linePoint,Color.rgb(123, 89, 168));
				lineDate.setDotStyle(XEnum.DotStyle.DOT);

			}
		}

	}*/
	@Override
    public void render(Canvas canvas) {
        try{
            chart.render(canvas);
        } catch (Exception e){
        	Log.e(TAG, e.toString());
        }
    }


	@Override
	public void run() {
		// TODO Auto-generated method stub
		 try {          
	         	chartAnimation();         	
	         }
	         catch(Exception e) {
	             Thread.currentThread().interrupt();
	         }  
	}
	
	private void chartAnimation()
	{
		  try { 
          	int count =  chartData.size();
          	for(int i=0;i< count ;i++)
          	{
          		Thread.sleep(150);
          		LinkedList<LineData> animationData = new LinkedList<LineData>();
          		for(int j=0;j<=i;j++)
                {            			            			
          			animationData.add(chartData.get(j));          			
                }   
         
          		//Log.e(TAG,"size = "+animationData.size());
          		chart.setDataSource(animationData);          		
          		if(i == count - 1)
          		{
          			chart.getDataAxis().show();
          			chart.getDataAxis().showAxisLabels();   
          			
          			chart.setCustomLines(mCustomLineDataset);
          		}
          		postInvalidate();            		
          	}            	
          }
          catch(Exception e) {
              Thread.currentThread().interrupt();
          }            
	}
			
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub		
		
		super.onTouchEvent(event);
				
		if(event.getAction() == MotionEvent.ACTION_UP) 
		{			
			//交叉线
			if(chart.getDyLineVisible())
			{
				chart.getDyLine().setCurrentXY(event.getX(),event.getY());
				if(chart.getDyLine().isInvalidate())this.invalidate();
			}
		}
		return true;
	}

}
