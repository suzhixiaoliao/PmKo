package com.intentpumin.lsy.intentpumin.tools.data.base;

import java.util.List;

/**
 * Created by yang on 2016/6/3.
 */
public class Rustatvalue {
    private int res;
    private String msg;
    private List<DataBean> data;

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
    public List<DataBean> getData() {
        return data;
    }
    public void setData(List<DataBean> data) {
        this.data = data;
    }
}
