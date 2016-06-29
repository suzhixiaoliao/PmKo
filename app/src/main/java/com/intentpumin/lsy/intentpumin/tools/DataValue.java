package com.intentpumin.lsy.intentpumin.tools;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yang on 2016/5/20.
 */
public class DataValue implements Serializable {
    String eqpt_id;
    String stat_id;
    List<Add_value> data;
    String s_date;
    String e_date;

    public String getEqpt_id() {
        return eqpt_id;
    }

    public void setEqpt_id(String eqpt_id) {
        this.eqpt_id = eqpt_id;
    }

    public List<Add_value> getData() {
        return data;
    }

    public void setData(List<Add_value> data) {
        this.data = data;
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


}
