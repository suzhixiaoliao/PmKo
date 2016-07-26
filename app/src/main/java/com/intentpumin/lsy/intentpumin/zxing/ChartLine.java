package com.intentpumin.lsy.intentpumin.zxing;

import com.intentpumin.lsy.intentpumin.tools.value.values_devices_get;

import java.util.List;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.PointValue;
public class ChartLine extends Line{
    public boolean show = true;
    public values_devices_get values_devices_get;
    public ChartLine(List<PointValue> values) {
        super(values);
    }
}
