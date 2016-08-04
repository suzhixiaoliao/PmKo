package com.pumin.lzl.pumin.bean;

/**
 * 作者：lzl on 2016/7/29 16:37
 * 邮箱：zhilin——comeon@163.com
 */
public class Choose_object {
    private String choose_name;
    private String choose_phone;
    private String choose_id;

    public Choose_object(String choose_name, String choose_phone,String choose_id) {
        this.choose_name = choose_name;
        this.choose_phone = choose_phone;
        this.choose_id=choose_id;
    }

    public String getChoose_id() {
        return choose_id;
    }

    public void setChoose_id(String choose_id) {
        this.choose_id = choose_id;
    }

    public String getChoose_name() {
        return choose_name;
    }

    public void setChoose_name(String choose_name) {
        this.choose_name = choose_name;
    }

    public String getChoose_phone() {
        return choose_phone;
    }

    public void setChoose_phone(String choose_phone) {
        this.choose_phone = choose_phone;
    }
}
