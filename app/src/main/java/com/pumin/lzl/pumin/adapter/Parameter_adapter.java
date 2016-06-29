package com.pumin.lzl.pumin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.bean.Tasklist_object;

import java.util.ArrayList;

/**
 * 作者：lzl on 2016/6/22 09:40
 * 邮箱：zhilin——comeon@163.com
 * 任务清单---
 */
public class Parameter_adapter extends BaseAdapter {

    Context context;
    ArrayList<Tasklist_object> task_list;

    public Parameter_adapter(Context context, ArrayList<Tasklist_object> task_list) {
        this.context = context;
        this.task_list = task_list;
    }


    @Override
    public int getCount() {
        return task_list.size();
    }

    @Override
    public Object getItem(int position) {
        return task_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView task_row_name;
        TextView task_row_comman;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Tasklist_object taskobject = task_list.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.activity_tasklist_row, null);

            holder.task_row_name = (TextView) convertView.findViewById(R.id.task_row_name);
            holder.task_row_comman = (TextView) convertView.findViewById(R.id.task_row_comman);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.task_row_name.setText(taskobject.getTaskname());
        holder.task_row_comman.setText(taskobject.getTaskcomment());

        return convertView;
    }
}
