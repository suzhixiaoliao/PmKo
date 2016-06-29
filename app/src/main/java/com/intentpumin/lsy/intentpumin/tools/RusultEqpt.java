package com.intentpumin.lsy.intentpumin.tools;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yang on 2016/5/16.
 */
public class RusultEqpt implements Serializable{
    private int res;
    private String msg;
    private int page;
    private int pages;
    private DataAE data;

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public DataAE getData() {
        return data;
    }

    public void setData(DataAE data) {
        this.data = data;
    }
}
