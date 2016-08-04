package com.pumin.lzl.pumin.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.bean.Choose_object;
import com.pumin.lzl.pumin.utils.callphone;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * 作者：lzl on 2016/7/29 16:36
 * 邮箱：zhilin——comeon@163.com
 * 选择运维人员界面适配器
 */
public class Choose_adapter extends BaseAdapter {
    //记录checkbox的状态
    public LinkedHashSet<String> listdata = new LinkedHashSet<>();
    public ArrayList array = new ArrayList<>();

    ArrayList<Choose_object> choose_array;
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


    class ViewHolder {
        CheckBox select_choose;
        TextView select_choose_phone;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final Choose_object choose_obj = choose_array.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.choose_list_item, null);
            holder.select_choose = (CheckBox) convertView.findViewById(R.id.select_choose);
            holder.select_choose_phone = (TextView) convertView.findViewById(R.id.select_choose_phone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.select_choose.setText(choose_obj.getChoose_name());
        holder.select_choose_phone.setText(Html.fromHtml("<u>" + choose_obj.getChoose_phone() + "</u>"));
        callphone.call(holder.select_choose_phone, context);
        holder.select_choose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                holder.select_choose.setSelected(isChecked);
                if (isChecked) {
                    holder.select_choose.setBackgroundResource(R.drawable.selectred);
                    for (int i = 0; i <= position; i++) {
                        array.add(choose_obj.getChoose_id());
                        array.add(choose_obj.getChoose_name());
                    }
                    listdata = new LinkedHashSet<String>(array);
                    ArrayList<String> array = new ArrayList<String>(listdata);
                    Toast.makeText(context, "" + array, Toast.LENGTH_SHORT).show();

                } else {
                    holder.select_choose.setBackgroundResource(R.drawable.unselectred);
                }
            }
        });
        return convertView;
    }

}
