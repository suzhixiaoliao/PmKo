package com.intentpumin.lsy.intentpumin.tools.data.base;

/**
 * Created by yang on 2016/6/4.
 */
public class ItemsBean {
    private String date;
    /**
     * date : 2016-05-24 11:05:00
     * eqpt_id : 47875313-1A24-2B35-2783-AE19D7334E2D
     * stat_id : E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9
     * stat_name : 温度
     * unit : 摄氏度
     * stat_comment : 测试状态评论55
     * r_value : 50.00
     */

    private ItemBean item;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ItemBean getItem() {
        return item;
    }

    public void setItem(ItemBean item) {
        this.item = item;
    }


}
