package com.pumin.lzl.pumin.bean;

/**
 * 作者：lzl on 2016/6/27 16:10
 * 邮箱：zhilin——comeon@163.com
 */
public class Tasklist_object2 {
    private String statname;
    private String unit;
    private String rvalue;

    public Tasklist_object2(String statname,String unit, String rvalue) {
        this.statname=statname;
        this.unit = unit;
        this.rvalue = rvalue;
    }

    public String getStatname() {
        return statname;
    }

    public void setStatname(String statname) {
        this.statname = statname;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRvalue() {
        return rvalue;
    }

    public void setRvalue(String rvalue) {
        this.rvalue = rvalue;
    }
}
