package com.intentpumin.lsy.intentpumin.tools;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yang on 2016/4/26.
 */
public class DataD implements Serializable{
    private int total;
  private List<item_task> items;
  // private List<DataTask> items;
//   private List<DataTask> items;

//    public List<DataTask> getDatalist() {
//        return datalist;
//    }
//
//    public void setDatalist(List<DataTask> datalist) {
//        this.datalist = datalist;
//    }

    public List<item_task> getItems() {
        return items;
    }

    public void setItems(List<item_task> items) {
        this.items = items;
    }
//    public List<com.intentpumin.lsy.intentpumin.tools.items> getItems() {
//        return items;
//    }
//
//    public void setItems(List<com.intentpumin.lsy.intentpumin.tools.items> items) {
//        this.items = items;
//    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
