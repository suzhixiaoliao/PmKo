package com.intentpumin.lsy.intentpumin.tools.device;


import java.io.Serializable;
import java.util.List;

public class device_items implements Serializable{
    private int total;
    //  private List<items> items;
    private List<items> items;

    public List<items> getItems() {
        return items;
    }

    public void setItems(List<items> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
