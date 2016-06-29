package com.intentpumin.lsy.intentpumin.tools;

import java.io.Serializable;

/**
 * Created by yang on 2016/5/5.
 */
public class login implements Serializable{
    String name = "";
    String type_id="";
    String type_name="";
    String priv="";
    String level_id="";
    String level_name="";
    String up_level_id="";
    String phoneno="";

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getPriv() {
        return priv;
    }

    public void setPriv(String priv) {
        this.priv = priv;
    }

    public String getLevel_id() {
        return level_id;
    }

    public void setLevel_id(String level_id) {
        this.level_id = level_id;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public String getUp_level_id() {
        return up_level_id;
    }

    public void setUp_level_id(String up_level_id) {
        this.up_level_id = up_level_id;
    }

}
