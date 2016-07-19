package com.intentpumin.lsy.intentpumin.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
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
    private int paddingWidth;
    public TaskGridAdapter(Context context, List<task_get> list, int paddingWidth) {
        super();
        this.context = context;
        this.list = list;
        this.paddingWidth=paddingWidth;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHodler hodler = null;
        if (convertView == null) {
            hodler = new ViewHodler();
            //     convertView=View.inflate(context,R.layout.layout_gridtask,null);
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_gridtask, null);
            hodler.rb_tab_o = (TextView) convertView.findViewById(R.id.tv_tab_o);
            hodler.rl_tab_o=(RelativeLayout)convertView.findViewById(R.id.rl__tb_o);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }
        task_get task = list.get(i);
        Log.e("TAG", task.getFinished() + "==========" + list.get(i).task_name);
        hodler.rl_tab_o.setPadding(paddingWidth,paddingWidth,paddingWidth,paddingWidth);
//        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) hodler.rl_tab_o.getLayoutParams();
//        params.width=LinearLayout.LayoutParams.FILL_PARENT;
//        params.height=LinearLayout.LayoutParams.FILL_PARENT;
//        params.set
//        hodler.rl_tab_o.setLayoutParams(params);
        if (task.getFinished().equals("Y")) {
          hodler.rb_tab_o.setSelected(true);
           hodler.rb_tab_o.setBackgroundResource(R.mipmap.task_undone);
        }
        else if(task.getFinished().equals("N"))
        {
           hodler.rb_tab_o.setBackgroundResource(R.mipmap.task_complete);
        }
        hodler.rb_tab_o.setText(task.task_name);

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

    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    public void setData(List<task_get> list){
        this.list=list;
    }
    class ViewHodler {
        private TextView rb_tab_o;

        private RelativeLayout rl_tab_o;
    }
}