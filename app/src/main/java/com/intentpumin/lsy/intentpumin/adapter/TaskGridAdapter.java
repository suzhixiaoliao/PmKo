package com.intentpumin.lsy.intentpumin.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.tools.task.task_get;

import java.util.List;

/**
 * Created by yang on 2016/4/28.
 */
public class TaskGridAdapter extends BaseAdapter {
    private SharedPreferences sp;
    private final String TAG = TaskGridAdapter.this.getClass().getSimpleName();
    private List<task_get> list;
    private Context context;

    public TaskGridAdapter(Context context, List<task_get> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_gridtask, null);
            hodler.rb_tab_o = (TextView) convertView.findViewById(R.id.tv_tab_o);
            hodler.rl_tab_o=(RelativeLayout)convertView.findViewById(R.id.rl__tb_o);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }
        task_get task = list.get(i);
        Log.e("TAG", task.getFinished() + "==========" + list.get(i).getTask_name());

        if (task.getFinished().equals("Y")) {
          hodler.rl_tab_o.setSelected(true);
            hodler.rl_tab_o.setBackgroundResource(R.mipmap.task_undone);
        }
        else if(task.getFinished().equals("N"))
        {
            hodler.rl_tab_o.setBackgroundResource(R.mipmap.task_complete);
        }
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


    public void setItems(List<task_get> list) {
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