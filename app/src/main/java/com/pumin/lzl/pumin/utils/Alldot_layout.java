package com.pumin.lzl.pumin.utils;

/**
 * 作者：lzl on 2016/7/4 15:58
 * 邮箱：zhilin——comeon@163.com
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.intentpumin.lsy.intentpumin.R;

import java.util.ArrayList;

/**
 * 滑动碎片-圆点变化--创建点的视图
 * 作者：lzl on 2016/7/4 15:51
 * 邮箱：zhilin——comeon@163.com
 */
public class Alldot_layout {
    public static void dot(Context context, ArrayList<Fragment> adapters_lists, LinearLayout dot_layout) {
        for (int i = 0; i < adapters_lists.size(); i++) {
            View v = new View(context); //创建view视图
            LinearLayout.LayoutParams parmes = new LinearLayout.LayoutParams(20, 20); //宽高
            if (i != 0) {
                parmes.leftMargin = 100;
            }
            v.setLayoutParams(parmes);
            v.setBackgroundResource(R.drawable.select);  //状态
            dot_layout.addView(v);
        }
    }
}
