package com.intentpumin.lsy.intentpumin.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.tools.alldevice.devices_all;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 2016/5/14.
 */
public class AdminEqptAdapter extends BaseAdapter {
    private SharedPreferences sp;
    private static final String TAG = "AdminEqptAdapter";
    private List<devices_all> list;
    private Context context;
    private LayoutInflater inflater;//布局填充器。生成所对应的view对象，系统内置
    private List<devices_all> checkList = new ArrayList<>();

    public AdminEqptAdapter(Context context, List<devices_all> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_admin_eqpt, null);
            hodler.tv_xz = (CheckBox) convertView.findViewById(R.id.tv_xz);
            hodler.tv_xz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (!checkList.contains(list.get(i))) {
                            checkList.add(list.get(i));
                            System.out.println("checkList add state the id is " + list.get(i).getEqpt_id());
                        }

                    } else {
                        checkList.remove(checkList.indexOf(list.get(i)));
                    }
                }
            });
            hodler.tv_sb = (TextView) convertView.findViewById(R.id.tv_sb);
            hodler.tv_tz = (TextView) convertView.findViewById(R.id.tv_tz);
            hodler.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
           /* hodler.tv_tz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AdminStatActivity.class);
                    context.startActivity(intent);
                }
            });*/

        } else {
            hodler = (ViewHodler) convertView.getTag();
        }
        hodler.tv_sb.setText(list.get(i).getEqpt_name());
        hodler.tv_id.setText(list.get(i).getEqpt_id());
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

    public void setItems(List<devices_all> list) {
        this.list = list;
        notifyDataSetChanged();
        if (list != null && list.size() > 0) {
            System.out.println("list的size是" + list.size());
        } else {
            System.out.println("list的null");
        }
    }

    class ViewHodler {
        private CheckBox tv_xz;
        private TextView tv_sb;
        private TextView tv_tz;
        private TextView tv_id;
    }
    public List<devices_all> getCheckList() {
        return checkList;
    }
}

