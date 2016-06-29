package com.pumin.lzl.pumin.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 作者：lzl on 2016/6/8 12:50
 * 邮箱：zhilin——comeon@163.com
 * 碎片的适配器
 */
public class Task_adapter extends FragmentPagerAdapter {

    ArrayList<Fragment> adapters_list;

    public Task_adapter(FragmentManager fm, ArrayList<Fragment> adapters_list) {
        super(fm);
        this.adapters_list = adapters_list;
    }


    @Override
    public Fragment getItem(int position) {
        return adapters_list.get(position);
    }

    @Override
    public int getCount() {
        return adapters_list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        System.gc();
    }
}
