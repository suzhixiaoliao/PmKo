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
          put("E3BF0082-AC49-9C56-C3D3-18EE8B31ECC9", R.mipmap.icon_wendu);
          put("C82CF813-76AC-1C58-6163-79F33F4AC323",R.mipmap.icon_liuliang);
          put("9BE05853-FE5B-5C8F-BDF8-4836D9C123E4",R.mipmap.icon_yali);
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

}
