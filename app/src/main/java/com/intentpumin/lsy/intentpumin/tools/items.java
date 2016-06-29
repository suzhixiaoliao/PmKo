package com.intentpumin.lsy.intentpumin.tools;

import java.io.Serializable;


public class items implements Serializable{
    private String area_id;
    private String date;
    private String loct_id;
    private String loct_name;
    private String eqpt_id;
    private String eqpt_name;
    private String task_id;
    private String task_name;
    private String task_comment;
    private String mt_id;
    private String mt_name;
    private String exec_time;
    private String finished;
    private String uploaded;

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
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

    public String getMt_id() {
        return mt_id;
    }

    public void setMt_id(String mt_id) {
        this.mt_id = mt_id;
    }

    public String getMt_name() {
        return mt_name;
    }

    public void setMt_name(String mt_name) {
        this.mt_name = mt_name;
    }

    public String getExec_time() {
        return exec_time;
    }

    public void setExec_time(String exec_time) {
        this.exec_time = exec_time;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getUploaded() {
        return uploaded;
    }

    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }

    @Override
    public String toString() {
        return "items{" +
                "date='" + date + '\'' +
                ", loct_id='" + loct_id + '\'' +
                ", loct_name='" + loct_name + '\'' +
                ", eqpt_id='" + eqpt_id + '\'' +
                ", eqpt_name='" + eqpt_name + '\'' +
                ", task_id='" + task_id + '\'' +
                ", task_name='" + task_name + '\'' +
                ", task_comment='" + task_comment + '\'' +
                ", mt_id='" + mt_id + '\'' +
                ", mt_name='" + mt_name + '\'' +
                ", exec_time='" + exec_time + '\'' +
                ", finished='" + finished + '\'' +
                ", uploaded='" + uploaded + '\'' +
                '}';
    }
}
