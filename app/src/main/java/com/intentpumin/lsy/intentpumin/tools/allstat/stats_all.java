package com.intentpumin.lsy.intentpumin.tools.allstat;

import java.io.Serializable;

/**
 * Created by yang on 2016/5/16.
 */
public class stats_all implements Serializable{
    private String  stat_id;
    private String stat_name;
    boolean isCheck = true;
    public String getStat_id() {
        return stat_id;
    }

    public void setStat_id(String stat_id) {
        this.stat_id = stat_id;
    }

    public String getStat_name() {
        return stat_name;
    }

    public void setStat_name(String stat_name) {
        this.stat_name = stat_name;
    }


    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

}
