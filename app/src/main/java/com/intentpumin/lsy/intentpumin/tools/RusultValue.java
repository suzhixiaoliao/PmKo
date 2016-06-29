package com.intentpumin.lsy.intentpumin.tools;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yang on 2016/5/20.
 */
public class RusultValue implements Serializable{
    private int res;
    private String msg;
    private List<DataValue> data;

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

    public List<DataValue> getData() {
        return data;
    }

    public void setData(List<DataValue> data) {
        this.data = data;
    }
}
