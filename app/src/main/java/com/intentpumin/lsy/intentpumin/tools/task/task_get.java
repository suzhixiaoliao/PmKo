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

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }
}
