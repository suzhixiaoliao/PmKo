package com.intentpumin.lsy.intentpumin.tools.maindevice;


import java.io.Serializable;
import java.util.List;

/**
 * Created by yang on 2016/5/16.
 */
public class device_items implements Serializable{
    private int total;
    //  private List<items> items;
    private List<com.intentpumin.lsy.intentpumin.tools.maindevice.items> items;

    public List<com.intentpumin.lsy.intentpumin.tools.maindevice.items> getItems() {
        return items;
    }

    public void setItems(List<com.intentpumin.lsy.intentpumin.tools.maindevice.items> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
