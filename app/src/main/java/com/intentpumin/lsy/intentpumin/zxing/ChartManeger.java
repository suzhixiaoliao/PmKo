package com.intentpumin.lsy.intentpumin.zxing;

import android.graphics.Color;
import android.util.Log;

import com.intentpumin.lsy.intentpumin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.view.LineChartView;
public class ChartManeger {

    LineChartView lineChartView;
    LineChartData lineChartData;



    List<Line> allLines = new ArrayList<>();
    Map<Integer, Integer> colosMap = new HashMap<>();

    public ChartManeger(LineChartView lineChartView) {
        this.lineChartView = lineChartView;
        refresh();
    }


    public void showLine(int position) {
        ChartLine line = (ChartLine) allLines.get(position);
        if (colosMap.containsKey(position)) {
            System.out.println("color");
            line.setColor(colosMap.get(position));
        } else {
            line.setColor(R.color.shenlan);
        }

        line.show = true;
        update();
    }


    public void hideLine(int position) {
        ChartLine line = (ChartLine) allLines.get(position);
        if (!colosMap.containsKey(position)) {
            colosMap.put(position, line.getColor());

            Log.e("hide", "hideLine: in" );

        }
        line.setColor(Color.parseColor("#00000000"));
        line.show = false;
        update();
    }


    public void update() {
        lineChartView.setLineChartData(lineChartData);
    }

    public void toggleShow(int position) {
        ChartLine line = (ChartLine) allLines.get(position);
        if (line.show) {
            hideLine(position);
        } else {
            System.out.println("show");
            showLine(position);
        }
    }

    public void refresh() {
        lineChartData = lineChartView.getLineChartData();
        allLines = lineChartData.getLines();
    }


}
