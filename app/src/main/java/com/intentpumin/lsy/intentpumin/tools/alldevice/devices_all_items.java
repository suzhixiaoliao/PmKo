package com.intentpumin.lsy.intentpumin.tools.alldevice;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yang on 2016/5/16.
 */
public class devices_all_items implements Serializable {
    private int total;
    private List<devices_all> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<devices_all> getItems() {
        return items;
    }

    public void setItems(List<devices_all> items) {
        this.items = items;
    }
}
