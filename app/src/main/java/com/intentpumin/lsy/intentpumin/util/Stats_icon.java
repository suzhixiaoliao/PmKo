package com.intentpumin.lsy.intentpumin.util;

import com.intentpumin.lsy.intentpumin.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yang on 2016/7/6.
 */
public class Stats_icon {
    private final static Map<String,Integer> mIcon =new HashMap<String,Integer>(){
        {
            put("AI001", R.mipmap.lieji);//温度
            put("AI002",R.mipmap.liuliang);
            // put("AI002",R.mipmap.icon_liuliang);//气体流量
            put("AI003",R.mipmap.liuliang);//液体流量
            // put("AI004",R.mipmap.yali_1);    ```````1
            put("AI004",R.mipmap.yali);//压力
            put("AI101",R.mipmap.dainliu);//电流
            //put("AI102",R.mipmap.);//电压
            put("AI103",R.mipmap.gonglv);//有功功率
            //  put("PI101",R.mipmap.);//电度量
            put("PI001",R.mipmap.liuliang);//累积流量液体
        }
    };
    private final static Map<String,Integer> mUnIcon =new HashMap<String,Integer>(){
        {
            put("AI001", R.mipmap.leiji_1);//温度
            put("AI002",R.mipmap.liuliang_1);
           // put("AI002",R.mipmap.icon_liuliang);//气体流量
            put("AI003",R.mipmap.liuliang_1);//液体流量
            // put("AI004",R.mipmap.yali_1);
            put("AI004",R.mipmap.yali_1);//压力
            put("AI101",R.mipmap.dianliu_1);//电流
            //put("AI102",R.mipmap.);//电压
            put("AI103",R.mipmap.gonglv_1);//有功功率
            //  put("PI101",R.mipmap.);//电度量
            put("PI001",R.mipmap.liuliang_1);//累积流量液体
            // put("PI002",R.mipmap.);//累计流量气体
            //  put("AI105",R.mipmap.);//
            //  put("AI106",R.mipmap.);
        }
    };
    public static int getStatIcon(String stat_id) {
        int resId = 0;
        try {
            HashMap.Entry<String, Integer> entry = null;
            //采用Iterator遍历Hashmap
            Iterator<HashMap.Entry<String, Integer>> iterator = mIcon.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = iterator.next();
                if (stat_id.equals(entry.getKey())) {
                    resId = entry.getValue();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resId;
    }
    public static int getUnStatIcon(String stat_id) {
        int resId = 0;
        try {
            HashMap.Entry<String, Integer> entry = null;
            //采用Iterator遍历Hashmap
            Iterator<HashMap.Entry<String, Integer>> iterator = mUnIcon.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = iterator.next();
                if (stat_id.equals(entry.getKey())) {
                    resId = entry.getValue();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resId;
    }

}
