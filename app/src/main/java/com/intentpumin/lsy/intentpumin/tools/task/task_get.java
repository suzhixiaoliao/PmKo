package com.intentpumin.lsy.intentpumin.tools.task;

import java.io.Serializable;

/**
 * Created by yang on 2016/4/28.
 */
public class task_get implements Serializable{
    private String area_id;
    private String date="";
    private String loct_id="";
    private String loct_name="";
    private String eqpt_id="";
    private String eqpt_name="";
    private String axis_x="";
    private String axis_y="";
    private String axis_z="";
    private String task_id="";
    private String task_name="";
    private String task_comment="";
    private String pmt_id="";
    private String pmt_name="";
    private String smt_id="";
    private String smt_name="";
    private String finished="";
    private String spot_x="";
    private String spot_y="";
    private String spot_z="";
    private String uploaded="";
    private String exec_time="";

    public String getExec_time() {
        return exec_time;
    }

    public void setExec_time(String exec_time) {
        this.exec_time = exec_time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLoct_id() {
        return loct_id;
    }

    public void setLoct_id(String loct_id) {
        this.loct_id = loct_id;
    }

    public String getLoct_name() {
        return loct_name;
    }

    public void setLoct_name(String loct_name) {
        this.loct_name = loct_name;
    }

    public String getEqpt_id() {
        return eqpt_id;
    }

    public void setEqpt_id(String eqpt_id) {
        this.eqpt_id = eqpt_id;
    }

    public String getEqpt_name() {
        return eqpt_name;
    }

    public void setEqpt_name(String eqpt_name) {
        this.eqpt_name = eqpt_name;
    }

    public String getAxis_x() {
        return axis_x;
    }

    public void setAxis_x(String axis_x) {
        this.axis_x = axis_x;
    }

    public String getAxis_y() {
        return axis_y;
    }

    public void setAxis_y(String axis_y) {
        this.axis_y = axis_y;
    }

    public String getAxis_z() {
        return axis_z;
    }

    public void setAxis_z(String axis_z) {
        this.axis_z = axis_z;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_comment() {
        return task_comment;
    }

    public void setTask_comment(String task_comment) {
        this.task_comment = task_comment;
    }

    public String getPmt_id() {
        return pmt_id;
    }

    public void setPmt_id(String pmt_id) {
        this.pmt_id = pmt_id;
    }

    public String getPmt_name() {
        return pmt_name;
    }

    public void setPmt_name(String pmt_name) {
        this.pmt_name = pmt_name;
    }

    public String getSmt_name() {
        return smt_name;
    }

    public void setSmt_name(String smt_name) {
        this.smt_name = smt_name;
    }

    public String getSmt_id() {
        return smt_id;
    }

    public void setSmt_id(String smt_id) {
        this.smt_id = smt_id;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getSpot_x() {
        return spot_x;
    }

    public void setSpot_x(String spot_x) {
        this.spot_x = spot_x;
    }

    public String getSpot_y() {
        return spot_y;
    }

    public void setSpot_y(String spot_y) {
        this.spot_y = spot_y;
    }

    public String getSpot_z() {
        return spot_z;
    }

    public void setSpot_z(String spot_z) {
        this.spot_z = spot_z;
    }

    public String getUploaded() {
        return uploaded;
    }

    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }


    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }
}
