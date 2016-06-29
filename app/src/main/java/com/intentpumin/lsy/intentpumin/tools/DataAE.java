package com.intentpumin.lsy.intentpumin.tools;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yang on 2016/5/16.
 */
public class DataAE implements Serializable {
    private int total;
    private List<Add_eqpt> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Add_eqpt> getItems() {
        return items;
    }

    public void setItems(List<Add_eqpt> items) {
        this.items = items;
    }
}
