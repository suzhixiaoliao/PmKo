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

    public Lookstate_object(String totals, String expects, String ontimes, String finishs) {
        this.totals = totals;
        this.ontimes = ontimes;
        this.expects = expects;
        this.finishs = finishs;
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
