package com.intentpumin.lsy.intentpumin.tools.task;

import com.intentpumin.lsy.intentpumin.tools.task.task_items_get;

import java.io.Serializable;

/**
 * Created by yang on 2016/4/26.
 */
public class result_task_get implements Serializable{

    private int res;
    private String msg;
    private int page;
    private int pages;
    private task_items_get data;

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

    public task_items_get getData() {
        return data;
    }

    public void setData(task_items_get data) {
        this.data = data;
    }

}
