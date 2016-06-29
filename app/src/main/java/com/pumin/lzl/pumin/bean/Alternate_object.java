package com.pumin.lzl.pumin.bean;

/**
 * 作者：lzl on 2016/6/20 08:54
 * 邮箱：zhilin——comeon@163.com
 *
 * 历史记录保存数据对象
 */
public class Alternate_object {
    private String start_time;
    private String pmt_name;
    private String isok;
    private String smt_name;






    public Alternate_object(String start_time, String pmt_name, String smt_name, String isok) {
        this.start_time = start_time;
        this.pmt_name = pmt_name;
        this.smt_name=smt_name;
        this.isok=isok;
    }

    public String getIsok() {
        return isok;
    }

    public void setIsok(String isok) {
        this.isok = isok;
    }

    public String getSmt_name() {
        return smt_name;
    }

    public void setSmt_name(String smt_name) {
        this.smt_name = smt_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getPmt_name() {
        return pmt_name;
    }

    public void setPmt_name(String pmt_name) {
        this.pmt_name = pmt_name;
    }
}
