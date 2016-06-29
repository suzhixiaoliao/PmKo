package com.intentpumin.lsy.intentpumin.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.TastComActivity;
import com.intentpumin.lsy.intentpumin.tools.item_task;

import java.util.List;

/**
 * Created by yang on 2016/4/28.
 */
public class ZxingTaskGridAdapter extends BaseAdapter {
    private SharedPreferences sp;
    private final String TAG = ZxingTaskGridAdapter.this.getClass().getSimpleName();
    private List<item_task> list;
    private Context context;

    public ZxingTaskGridAdapter(Context context, List<item_task> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public Object getItem(int i) {
        if (list != null && list.size() > 0) {
            return list.get(i);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        if (list != null && list.size() > 0) {
            return i;
        } else {
            return 0;
        }

    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHodler hodler = null;
        if (convertView == null) {
            hodler = new ViewHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_list__zxing, null);
            hodler.rb_tab_o = (TextView) convertView.findViewById(R.id.tv_tab_o);
//            hodler.rb_tab_o.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent inter = new Intent();
//                    inter.setClass(context, TastComActivity.class);
//                    context.startActivity(inter);
//                }
//            });
            hodler.rl_tab_o=(RelativeLayout)convertView.findViewById(R.id.rl__tb_o);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }
        item_task task = list.get(i);
        Log.e("TAG", task.getFinished() + "==========" + list.get(i).getTask_name());

        if (task.getFinished().equals("Y")) {
//            hodler.rl_tab_o.setSelected(true);
            hodler.rl_tab_o.setBackgroundColor(context.getResources().getColor(R.color.divider));
        }
//        else if(task.getFinished().equals("Y"))
//        {
//            hodler.rl_tab_o.setBackgroundColor(context.getResources().getColor(R.color.divider));
//        }
        else if(task.getFinished().equals("N"))
        {
            hodler.rl_tab_o.setBackgroundResource(R.drawable.myshape);
        }
//        else {
////            hodler.rl_tab_o.setSelected(false);
//            hodler.rl_tab_o.setBackgroundResource(R.drawable.myshape);
//        }
        hodler.rb_tab_o.setText(task.getTask_name());

        convertView.setTag(hodler);
        return convertView;
    }
    @Override
    public int getCount() {
        if (list != null && list.size() > 0) {
            System.out.println("`list的size是" + list.size());
        } else {
            System.out.println("`list的null");
        }
        return list.size();
    }


    public void setItems(List<item_task> list) {
        this.list = list;
        notifyDataSetChanged();
        if (list != null && list.size() > 0) {
            System.out.println("list的size是" + list.size());
        } else {
            System.out.println("list的null");
        }
    }

    class ViewHodler {
        private TextView rb_tab_o;

        private RelativeLayout rl_tab_o;
    }
}