package com.intentpumin.lsy.intentpumin.tools;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yang on 2016/5/16.
 */
public class DataI implements Serializable{
    private int total;
    //  private List<items> items;
    private List<items> items;

    public List<com.intentpumin.lsy.intentpumin.tools.items> getItems() {
        return items;
    }

    public void setItems(List<com.intentpumin.lsy.intentpumin.tools.items> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
