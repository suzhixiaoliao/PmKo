package com.pumin.lzl.pumin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.bean.Choose_object;

import java.util.ArrayList;

/**
 * 作者：lzl on 2016/7/29 16:36
 * 邮箱：zhilin——comeon@163.com
 * 选择运维人员界面
 */
public class Choose_adapter extends BaseAdapter {

    ArrayList<Choose_object>  choose_array;
    Context context;

    public Choose_adapter(ArrayList<Choose_object> choose_array, Context context) {
        this.choose_array = choose_array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return choose_array.size();
    }

    @Override
    public Object getItem(int position) {
        return choose_array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        TextView select_choose;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Choose_object choose_obj=choose_array.get(position);
        if(convertView==null){
        holder=new ViewHolder();
            convertView=View.inflate(context, R.layout.choose_list_item,null);
            holder.select_choose= (TextView) convertView.findViewById(R.id.select_choose);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.select_choose.setText(choose_obj.getChoose_name());
        return convertView;
    }
}
