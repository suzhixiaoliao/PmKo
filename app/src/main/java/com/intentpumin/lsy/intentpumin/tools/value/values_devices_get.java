package com.intentpumin.lsy.intentpumin.tools.value;

import java.util.List;

/**
 * Created by yang on 2016/6/4.
 */
public class values_devices_get {
    //表示这条线是否有选中,如果选中,为true,默认为false
    private boolean isChecked = false;
    private String eqpt_name;
    private String stat_name;
    private String eqpt_id;
    private String stat_id;
    private String s_date;
    private String e_date;
    private List<values_all_items> items;

    public String getEqpt_name() {
        return eqpt_name;
    }

    public void setEqpt_name(String eqpt_name) {
        this.eqpt_name = eqpt_name;
    }

    public String getStat_name() {
        return stat_name;
    }

    public void setStat_name(String stat_name) {
        this.stat_name = stat_name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getEqpt_id() {
        return eqpt_id;
    }

    public void setEqpt_id(String eqpt_id) {
        this.eqpt_id = eqpt_id;
    }

    public String getStat_id() {
        return stat_id;
    }

    public void setStat_id(String stat_id) {
        this.stat_id = stat_id;
    }

    public String getS_date() {
        return s_date;
    }

    public void setS_date(String s_date) {
        this.s_date = s_date;
    }

    public String getE_date() {
        return e_date;
    }

    public void setE_date(String e_date) {
        this.e_date = e_date;
    }

    public List<values_all_items> getItems() {
        return items;
    }

    public void setItems(List<values_all_items> items) {
        this.items = items;
    }


}
