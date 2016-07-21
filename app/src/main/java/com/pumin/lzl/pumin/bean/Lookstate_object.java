package com.pumin.lzl.pumin.bean;

/**
 * 作者：lzl on 2016/6/21 09:43
 * 邮箱：zhilin——comeon@163.com
 */
public class Lookstate_object {
    String totals;
    String expects;
    String finishs;
    String ontimes;
    String okcount;
    String oktime;

    public Lookstate_object(String totals, String expects, String ontimes, String finishs
    ,String okcount,String oktime) {
        this.okcount=okcount;
        this.oktime=oktime;
        this.totals = totals;
        this.ontimes = ontimes;
        this.expects = expects;
        this.finishs = finishs;
    }

    public String getOktime() {
        return oktime;
    }

    public void setOktime(String oktime) {
        this.oktime = oktime;
    }

    public String getOkcount() {
        return okcount;
    }

    public void setOkcount(String okcount) {
        this.okcount = okcount;
    }

    public String getTotals() {
        return totals;
    }

    public void setTotals(String totals) {
        this.totals = totals;
    }

    public String getExpects() {
        return expects;
    }

    public void setExpects(String expects) {
        this.expects = expects;
    }

    public String getFinishs() {
        return finishs;
    }

    public void setFinishs(String finishs) {
        this.finishs = finishs;
    }

    public String getOntimes() {
        return ontimes;
    }

    public void setOntimes(String ontimes) {
        this.ontimes = ontimes;
    }
}
