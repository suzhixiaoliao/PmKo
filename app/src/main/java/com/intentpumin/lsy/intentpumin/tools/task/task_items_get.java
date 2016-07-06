package com.intentpumin.lsy.intentpumin.tools.task;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yang on 2016/4/26.
 */
public class task_items_get implements Serializable{
    private int total;
  private List<task_get> items;
    public List<task_get> getItems() {
        return items;
    }

    public void setItems(List<task_get> items) {
        this.items = items;
    }
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
