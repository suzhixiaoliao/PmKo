package com.intentpumin.lsy.intentpumin.tools.alldevice;

import java.io.Serializable;

public class devices_all implements Serializable{
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

}
