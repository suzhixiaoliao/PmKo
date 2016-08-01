package com.pumin.lzl.pumin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.bean.Furn_task_obj;

import java.util.ArrayList;

/**
 * 作者：lzl on 2016/7/29 11:12
 * 邮箱：zhilin——comeon@163.com
 * 布置临时任务中-选择任务列表
 */
public class Furn_task_adapter extends BaseAdapter {

    ArrayList<Furn_task_obj> furn_task_array;
    Context context;

    public Furn_task_adapter(ArrayList<Furn_task_obj> furn_task_array, Context context) {
        this.furn_task_array = furn_task_array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return furn_task_array.size();
    }

    @Override
    public Object getItem(int position) {
        return furn_task_array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView furn_task_item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Furn_task_obj task_obj=furn_task_array.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.furn_task_listitem, null);
            holder.furn_task_item = (TextView) convertView.findViewById(R.id.furn_task_item);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.furn_task_item.setText(task_obj.getFurn_task_name());
        return convertView;
    }
}
