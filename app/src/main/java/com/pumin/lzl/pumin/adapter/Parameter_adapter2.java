package com.pumin.lzl.pumin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.bean.Tasklist_object2;

import java.util.ArrayList;

/**
 * 作者：lzl on 2016/6/27 15:50
 * 邮箱：zhilin——comeon@163.com
 * 记录参数
 */
public class Parameter_adapter2 extends BaseAdapter {

    Context context;
    ArrayList<Tasklist_object2> task_list2;

    public Parameter_adapter2(Context context, ArrayList<Tasklist_object2> task_list2) {
        this.context = context;
        this.task_list2 = task_list2;
    }

    @Override
    public int getCount() {
        return task_list2.size();
    }

    @Override
    public Object getItem(int position) {
        return task_list2.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    class ViewHolder2 {
        TextView task_statname;
        TextView task_unit;
        TextView task_value;
        LinearLayout linear_task;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder2 holder2;
        Tasklist_object2 object = task_list2.get(position);
        if (convertView == null) {
            holder2 = new ViewHolder2();
            convertView = View.inflate(context, R.layout.activity_tasklist_row2, null);
            holder2.task_statname = (TextView) convertView.findViewById(R.id.task_statname);
            holder2.task_unit = (TextView) convertView.findViewById(R.id.task_unit);
            holder2.task_value = (TextView) convertView.findViewById(R.id.task_value);
            holder2.linear_task = (LinearLayout) convertView.findViewById(R.id.linear_task);
            convertView.setTag(holder2);
        } else {
            holder2 = (ViewHolder2) convertView.getTag();
        }

        if (position % 2 == 0) {
            holder2.linear_task.setBackgroundResource(R.mipmap.bk2_tasklist);
        } else {
            holder2.linear_task.setBackgroundResource(R.mipmap.bk_tasklist);
        }
        holder2.task_statname.setText(object.getStatname());
        holder2.task_unit.setText(object.getUnit());
        if (object.getRvalue().equals("null")) {
            holder2.task_value.setText("没有记录");
        } else {
            holder2.task_value.setText(object.getRvalue());
        }
        return convertView;
    }
}
