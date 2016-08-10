package com.intentpumin.lsy.intentpumin.tools.device;


import java.io.Serializable;
import java.util.List;

public class device_items implements Serializable{
    private String total;
    //  private List<items> items;
    private List<items> items;

    public List<items> getItems() {
        return items;
    }

    public void setItems(List<items> items) {
        this.items = items;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
