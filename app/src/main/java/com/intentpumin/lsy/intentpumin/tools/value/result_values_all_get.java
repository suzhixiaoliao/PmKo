package com.intentpumin.lsy.intentpumin.tools.value;

import com.intentpumin.lsy.intentpumin.tools.value.values_devices_get;

import java.util.List;

/**
 * Created by yang on 2016/6/3.
 */
public class result_values_all_get {
    private int res;
    private String msg;
    private List<values_devices_get> data;

    public int getRes() {
        return res;
    }
    public void setRes(int res) {
        this.res = res;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public List<values_devices_get> getData() {
        return data;
    }
    public void setData(List<values_devices_get> data) {
        this.data = data;
    }
}
