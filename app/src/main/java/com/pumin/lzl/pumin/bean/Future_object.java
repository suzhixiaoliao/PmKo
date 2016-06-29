package com.pumin.lzl.pumin.bean;

/**
 * 作者：lzl on 2016/6/22 14:51
 * 邮箱：zhilin——comeon@163.com
 */
public class Future_object {
    private String start_time;
    private String ptm_name;
    private String stm_name;

    public Future_object(String start_time, String ptm_name, String stm_name) {
        this.start_time = start_time;
        this.ptm_name = ptm_name;
        this.stm_name = stm_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getPtm_name() {
        return ptm_name;
    }

    public void setPtm_name(String ptm_name) {
        this.ptm_name = ptm_name;
    }

    public String getStm_name() {
        return stm_name;
    }

    public void setStm_name(String stm_name) {
        this.stm_name = stm_name;
    }
}
