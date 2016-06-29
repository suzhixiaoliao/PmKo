package com.intentpumin.lsy.intentpumin.tools;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yang on 2016/5/20.
 */
public class DataV implements Serializable{
    public Add_value getData() {
        return data;
    }

    public void setData(Add_value data) {
        this.data = data;
    }

    /*private List<Add_value> data;

        public List<Add_value> getData() {
            return data;
        }

        public void setData(List<Add_value> data) {
            this.data = data;
        }*/
    private Add_value data;
}
