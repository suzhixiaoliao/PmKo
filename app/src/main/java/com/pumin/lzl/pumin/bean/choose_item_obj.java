package com.pumin.lzl.pumin.bean;

/**
 * 作者：lzl on 2016/8/4 15:45
 * 邮箱：zhilin——comeon@163.com
 */
public class choose_item_obj {
    private String id;
    private String name;

    public choose_item_obj(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
