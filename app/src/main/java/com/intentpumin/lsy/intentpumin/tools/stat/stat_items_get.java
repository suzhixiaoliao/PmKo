package com.intentpumin.lsy.intentpumin.tools.stat;


import java.io.Serializable;
import java.util.List;

/**
 * Created by yang on 2016/4/26.
 */
public class stat_items_get implements Serializable{
    private int total;
   private List<stat_get> items;
    public List<stat_get> getItems() {
        return items;
    }

    public void setItems(List<stat_get> items) {
        this.items = items;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
}
