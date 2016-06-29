package com.pumin.lzl.pumin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.intentpumin.lsy.intentpumin.R;

import java.util.ArrayList;

/**
 * 作者：lzl on 2016/6/17 08:57
 * 邮箱：zhilin——comeon@163.com
 *   所有设备管理适配器
 */
public class Management_adapter extends BaseAdapter {

    Context context;
    ArrayList<String> manage_array;

    public Management_adapter(Context context,ArrayList<String> manage_array) {
        this.context = context;
        this.manage_array=manage_array;
    }

    @Override
    public int getCount() {
        return manage_array.size();
    }

    @Override
    public Object getItem(int position) {
        return manage_array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        TextView managment_name_row;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(context, R.layout.activity_management_row,null);
            holder.managment_name_row= (TextView) convertView.findViewById(R.id.managment_name_row);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.managment_name_row.setText("A000001");
        return convertView;
    }
}
