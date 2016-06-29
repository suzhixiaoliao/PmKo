package com.intentpumin.lsy.intentpumin.tools;

import java.io.Serializable;

/**
 * Created by yang on 2016/5/16.
 */
public class Add_eqpt implements Serializable{
    private String eqpt_id;
    private String eqpt_name;
    boolean isCheck = false;

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


    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public String toString() {
        return "Add_eqpt{" +
                "eqpt_id='" + eqpt_id + '\'' +
                ", eqpt_name='" + eqpt_name + '\'' +
                '}';
    }
}
