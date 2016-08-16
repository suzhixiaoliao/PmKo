package com.intentpumin.lsy.intentpumin.tools.allstat;

import java.io.Serializable;
import java.util.List;

public class stats_all_items implements Serializable {
    private int total;
    private List<stats_all> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<stats_all> getItems() {
        return items;
    }

    public void setItems(List<stats_all> items) {
        this.items = items;
    }
}
