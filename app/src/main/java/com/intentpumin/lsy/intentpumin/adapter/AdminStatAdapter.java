package com.intentpumin.lsy.intentpumin.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.tools.allstat.stats_all;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 2016/5/16.
 */
public class AdminStatAdapter extends BaseAdapter {
    private SharedPreferences sp;
    private static final String TAG = AdminStatAdapter.class.getClass().getName();
    private List<stats_all> list;
    private Context context;
    private LayoutInflater inflater;//布局填充器。生成所对应的view对象，系统内置
    private List<stats_all> checkList = new ArrayList<stats_all>();
    public AdminStatAdapter(Context context, List<stats_all> list) {
        super();
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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
    public View getView(final int i, View convertView, ViewGroup parent) {
        ViewHodler hodler = null;
        if (convertView == null) {
            hodler = new ViewHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_admin_stat, null);
            hodler.tv_stat_xz= (CheckBox) convertView.findViewById(R.id.tv_stat_xz);
            hodler.tv_stat_xz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (!checkList.contains(list.get(i))) {
                            checkList.add(list.get(i));
                            System.out.println("checkList add state the id is " + list.get(i).getStat_id());
                        }

                    } else {
                        checkList.remove(checkList.indexOf(list.get(i)));
                    }
                }
            });
            hodler.tv_stat_sb = (TextView) convertView.findViewById(R.id.tv_stat_sb);
            convertView.setTag(hodler);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }
        hodler.tv_stat_sb.setText(list.get(i).getStat_name());
       // hodler.tv_stat_sb.setText(list.get(0).getStat_name());

        return convertView;
    }

    @Override
    public int getCount() {
        if (list!=null&&list.size()>0){
            System.out.println("`list的size是"+list.size());}else {
            System.out.println("`list的null");
        }
        return list.size();
    }

    public void setItems(List<stats_all> list) {
        this.list = list;
        notifyDataSetChanged();
        if (list!=null&&list.size()>0){
            System.out.println("list的size是"+list.size());}else {
            System.out.println("list的null");
        }
    }

    class ViewHodler {
        private CheckBox tv_stat_xz;
        private TextView tv_stat_sb;
    }
    public List<stats_all> getCheckList() {
        return checkList;
    }
}

