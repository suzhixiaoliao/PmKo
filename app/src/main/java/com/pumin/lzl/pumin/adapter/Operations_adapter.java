package com.pumin.lzl.pumin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.intentpumin.lsy.intentpumin.R;

import java.util.ArrayList;

/**
 * 作者：lzl on 2016/6/15 08:52
 * 邮箱：zhilin——comeon@163.com
 * 选择运维人员适配器
 */
public class Operations_adapter extends BaseAdapter {

    Context context;
    ArrayList<String> operation_array;

    public Operations_adapter(Context context, ArrayList<String> operation_array) {
        this.context = context;
        this.operation_array = operation_array;
    }

    @Override
    public int getCount() {
        return operation_array.size();
    }

    @Override
    public Object getItem(int position) {
        return operation_array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHoLder {
        CheckBox operation_row_name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoLder holder;
        if (convertView == null) {
            holder = new ViewHoLder();
            convertView = View.inflate(context, R.layout.activity_operations_row, null);
            holder.operation_row_name = (CheckBox) convertView.findViewById(R.id.operation_row_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHoLder) convertView.getTag();
        }
        //假数据
        holder.operation_row_name.setText("王五");
        return convertView;
    }
}
