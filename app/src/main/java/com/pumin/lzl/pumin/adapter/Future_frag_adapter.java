package com.pumin.lzl.pumin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.bean.Future_object;

import java.util.ArrayList;

/**
 * 作者：lzl on 2016/6/16 08:59
 * 邮箱：zhilin——comeon@163.com
 * 未来任务适配器
 */
public class Future_frag_adapter extends BaseAdapter {

    Context context;
    ArrayList<Future_object> future_Array;

    public Future_frag_adapter(Context context, ArrayList<Future_object> future_Array) {
        this.context = context;
        this.future_Array = future_Array;
    }

    @Override
    public int getCount() {
        return future_Array.size();
    }

    @Override
    public Object getItem(int position) {
        return future_Array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView future_row_time;
        TextView future_row_task;
        TextView future_row_charge;
        TextView future_row_charge2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Future_object object = future_Array.get(position);
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.activity_future_row, null);
            holder = new ViewHolder();
            holder.future_row_time = (TextView) convertView.findViewById(R.id.future_row_time);
            holder.future_row_task = (TextView) convertView.findViewById(R.id.future_row_task);
            holder.future_row_charge = (TextView) convertView.findViewById(R.id.future_row_charge);
            holder.future_row_charge2 = (TextView) convertView.findViewById(R.id.future_row_charge2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.future_row_time.setText(object.getStart_time());
        holder.future_row_task.setText("未完成");
        holder.future_row_charge.setText(object.getPtm_name());
        holder.future_row_charge2.setText(object.getStm_name());

        return convertView;
    }
}
