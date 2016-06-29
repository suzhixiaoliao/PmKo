package com.intentpumin.lsy.intentpumin.tools;

/**
 * Created by yang on 2016/5/20.
 */
public class Add_value {
    String date;
    String eqpt_id;
    String stat_id;
    String stat_name;
    String unit;
    String stat_comment;
    String r_value;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getStat_name() {
        return stat_name;
    }

    public void setStat_name(String stat_name) {
        this.stat_name = stat_name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStat_comment() {
        return stat_comment;
    }

    public void setStat_comment(String stat_comment) {
        this.stat_comment = stat_comment;
    }

    public String getR_value() {
        return r_value;
    }

    public void setR_value(String r_value) {
        this.r_value = r_value;
    }

    @Override
    public String toString() {
        return "Add_value{" +
                "date='" + date + '\'' +
                ", eqpt_id='" + eqpt_id + '\'' +
                ", stat_id='" + stat_id + '\'' +
                ", stat_name='" + stat_name + '\'' +
                ", unit='" + unit + '\'' +
                ", stat_comment='" + stat_comment + '\'' +
                ", r_value='" + r_value + '\'' +
                '}';
    }
}
