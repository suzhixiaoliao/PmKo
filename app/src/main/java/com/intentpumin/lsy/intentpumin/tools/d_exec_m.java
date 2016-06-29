package com.intentpumin.lsy.intentpumin.tools;

import java.io.Serializable;

/**
 * Created by yang on 2016/4/21.
 */
public class d_exec_m implements Serializable{
    private String date;
    private String loct_id;
    private String loct_name;
    private String eqpt_id;
    private String eqpt_name;
    private String pmt_id;
    private String pmt_name;
    private String smt_id;
    private String smt_name;
    private String frqc_name;
    private String finished;
    private String uploaded;
    private String remark;

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

    public String getPmt_name() {
        return pmt_name;
    }

    public void setPmt_name(String pmt_name) {
        this.pmt_name = pmt_name;
    }

    public String getPmt_id() {
        return pmt_id;
    }

    public void setPmt_id(String pmt_id) {
        this.pmt_id = pmt_id;
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

    public String getFrqc_name() {
        return frqc_name;
    }

    public void setFrqc_name(String frqc_name) {
        this.frqc_name = frqc_name;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "d_exec_m{" +
                "date='" + date + '\'' +
                ", loct_id='" + loct_id + '\'' +
                ", loct_name='" + loct_name + '\'' +
                ", eqpt_id='" + eqpt_id + '\'' +
                ", eqpt_name='" + eqpt_name + '\'' +
                ", pmt_id='" + pmt_id + '\'' +
                ", pmt_name='" + pmt_name + '\'' +
                ", smt_id='" + smt_id + '\'' +
                ", smt_name='" + smt_name + '\'' +
                ", frqc_name='" + frqc_name + '\'' +
                ", finished='" + finished + '\'' +
                ", uploaded='" + uploaded + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
