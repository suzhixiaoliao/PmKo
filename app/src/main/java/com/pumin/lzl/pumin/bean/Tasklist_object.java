package com.pumin.lzl.pumin.bean;

/**
 * 作者：lzl on 2016/6/27 13:59
 * 邮箱：zhilin——comeon@163.com
 * 物品任务清单  状态清单
 */
public class Tasklist_object {
    private String taskname;
    private String taskcomment;


    public Tasklist_object(String taskname, String taskcomment) {
        this.taskname = taskname;
        this.taskcomment = taskcomment;

    }



    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }


    public String getTaskcomment() {
        return taskcomment;
    }

    public void setTaskcomment(String taskcomment) {
        this.taskcomment = taskcomment;
    }


}
