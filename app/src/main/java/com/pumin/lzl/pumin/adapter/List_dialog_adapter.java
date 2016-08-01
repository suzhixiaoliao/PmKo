package com.pumin.lzl.pumin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.bean.Area_dialog_obj;

import java.util.ArrayList;

/**
 * 作者：lzl on 2016/7/28 17:19
 * 邮箱：zhilin——comeon@163.com
 */
public class List_dialog_adapter extends BaseAdapter {

    ArrayList<Area_dialog_obj>  dialog_array;
    Context context;

    public List_dialog_adapter(ArrayList<Area_dialog_obj> dialog_array,Context context) {
        this.dialog_array = dialog_array;
        this.context=context;
    }

    @Override
    public int getCount() {
        return dialog_array.size();
    }

    @Override
    public Object getItem(int position) {
        return dialog_array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        TextView area_name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Area_dialog_obj  dialog_obj=dialog_array.get(position);
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.list_dialog_item,null);
            holder.area_name= (TextView) convertView.findViewById(R.id.area_name);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.area_name.setText(dialog_obj.getArea_name());
        return convertView;
    }
}
