package com.intentpumin.lsy.intentpumin.tools;

import java.io.PipedReader;
import java.io.Serializable;
import java.util.List;

/**
 * Created by yang on 2016/4/26.
 */
public class DataE implements Serializable{
    private int total;
  //  private List<items> items;
   private List<DataTask> items;
//   private List<DataTask> items;

//    public List<DataTask> getDatalist() {
//        return datalist;
//    }
//
//    public void setDatalist(List<DataTask> datalist) {
//        this.datalist = datalist;
//    }

    public List<DataTask> getItems() {
        return items;
    }

    public void setItems(List<DataTask> items) {
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
