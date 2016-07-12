package com.pumin.lzl.pumin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.bean.Alternate_object;

import java.util.ArrayList;

/**
 * 作者：lzl on 2016/6/13 15:06
 * 邮箱：zhilin——comeon@163.com
 * 当前任务的数据适配器
 */
public class Alter_frag_adapter extends BaseAdapter {

    Context context;
    ArrayList<Alternate_object> alter_Array;


    public Alter_frag_adapter(ArrayList<Alternate_object> alter_Array, Context context) {
        this.alter_Array = alter_Array;
        this.context = context;
    }


    @Override
    public int getCount() {
        return alter_Array.size();
    }

    @Override
    public Object getItem(int position) {
        return alter_Array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView alter_row_time;
        TextView alter_row_task;
        TextView alter_row_charge;
        TextView alter_row_charge2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Alternate_object alter_obj = alter_Array.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.activity_alternate_row1, null);
            holder.alter_row_time = (TextView) convertView.findViewById(R.id.alter_row_time);
            holder.alter_row_task = (TextView) convertView.findViewById(R.id.alter_row_task);
            holder.alter_row_charge = (TextView) convertView.findViewById(R.id.alter_row_charge);
            holder.alter_row_charge2 = (TextView) convertView.findViewById(R.id.alter_row_charge2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
            //从后台拿数据到对象里面得值
            holder.alter_row_time.setText(alter_obj.getStart_time());
            if (alter_obj.getIsok().equals("N")||alter_obj.getIsok().equals("未知")) {
                holder.alter_row_task.setText("未知");
            } else if (alter_obj.getIsok().equals("Y")) {
                holder.alter_row_task.setText("已完成");
            }

            holder.alter_row_charge.setText(alter_obj.getPmt_name());
            holder.alter_row_charge2.setText(alter_obj.getSmt_name());

        return convertView;
    }

}
