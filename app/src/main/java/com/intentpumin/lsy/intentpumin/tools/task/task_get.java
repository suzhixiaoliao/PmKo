package com.intentpumin.lsy.intentpumin.tools.task;

import java.io.Serializable;

/**
 * Created by yang on 2016/4/28.
 */
public class task_get implements Serializable{
    public String area_id;
    public String date="";
    public String loct_id="";
    public String loct_name="";
    public String eqpt_id="";
    public String eqpt_name="";
    public String axis_x="";
    public String axis_y="";
    public String axis_z="";
    public String task_id="";
    public String task_name="";
    public String task_comment="";
    public String pmt_id="";
    public String pmt_name="";
    public String smt_id="";
    public String smt_name="";
    public String finished="";
    public String spot_x="";
    public String spot_y="";
    public String spot_z="";
    public String uploaded="";
    public String remark="";

    public String getPmt_name() {
        return pmt_name;
    }

    public void setPmt_name(String pmt_name) {
        this.pmt_name = pmt_name;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getEqpt_id() {
        return eqpt_id;
    }

    public void setEqpt_id(String eqpt_id) {
        this.eqpt_id = eqpt_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }
}
