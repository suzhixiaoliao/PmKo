package com.intentpumin.lsy.intentpumin.tools;

import java.util.List;

/**
 * Created by yang on 2016/5/21.
 */
public class StataValueResult {


    /**
     * res : 1
     * msg :
     * data : [{"eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","data":[{"date":"2016-05-19 13:35:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论50","r_value":"50.00"},{"date":"2016-05-18 15:10:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论49","r_value":"50.00"},{"date":"2016-05-17 19:10:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论48","r_value":"50.00"},{"date":"2016-05-16 11:00:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论47","r_value":"50.00"},{"date":"2016-05-15 20:50:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论46","r_value":"50.00"},{"date":"2016-05-14 16:40:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论45","r_value":"50.00"},{"date":"2016-05-13 10:15:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论44","r_value":"50.00"},{"date":"2016-05-12 13:30:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论43","r_value":"50.00"},{"date":"2016-05-11 16:00:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论42","r_value":"50.00"},{"date":"2016-05-10 16:15:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论41","r_value":"50.00"}],"s_date":"2016-05-09 00:00:00","e_date":"2016-05-20 00:00:00"}]
     */

    private int res;
    private String msg;
    /**
     * eqpt_id : 47875318-1A24-2B35-2783-AE19D7334E2D
     * stat_id : E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9
     * data : [{"date":"2016-05-19 13:35:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论50","r_value":"50.00"},{"date":"2016-05-18 15:10:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论49","r_value":"50.00"},{"date":"2016-05-17 19:10:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论48","r_value":"50.00"},{"date":"2016-05-16 11:00:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论47","r_value":"50.00"},{"date":"2016-05-15 20:50:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论46","r_value":"50.00"},{"date":"2016-05-14 16:40:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论45","r_value":"50.00"},{"date":"2016-05-13 10:15:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论44","r_value":"50.00"},{"date":"2016-05-12 13:30:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论43","r_value":"50.00"},{"date":"2016-05-11 16:00:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论42","r_value":"50.00"},{"date":"2016-05-10 16:15:00","eqpt_id":"47875318-1A24-2B35-2783-AE19D7334E2D","stat_id":"E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9","stat_name":"温度","unit":"摄氏度","stat_comment":"测试状态评论41","r_value":"50.00"}]
     * s_date : 2016-05-09 00:00:00
     * e_date : 2016-05-20 00:00:00
     */

    private List<DataBean> data;


    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


}
// 你 要建一个 单独的 类  不要建静态类不累

