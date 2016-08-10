package com.intentpumin.lsy.intentpumin.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.tools.repairlist.RepairList;
import com.intentpumin.lsy.intentpumin.tools.repairlist.Repair_item;
import com.intentpumin.lsy.intentpumin.util.Stats_icon;

import java.util.List;


/**
 * Created by yang on 2016/8/9.
 */
public class RepairListAdapter extends BaseAdapter{
    private SharedPreferences sp;
    private List<Repair_item> list;
    private Context context;
    private LayoutInflater inflater;//布局填充器。生成所对应的view对象，系统内置

    public RepairListAdapter(Context context, List<Repair_item> list) {
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
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHodler hodler = null;
        if (convertView == null) {
            hodler = new ViewHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_repair_lv, null);
            hodler.tv_time = (TextView) convertView.findViewById(R.id.tv_repair_time);
            hodler.tv_device = (TextView) convertView.findViewById(R.id.tv_repair_device);
            hodler.tv_Location = (TextView) convertView.findViewById(R.id.tv_repair_Location);
            hodler.img_repair= (ImageView) convertView.findViewById(R.id.repair_dot);
            hodler.tv_repair_phone= (TextView) convertView.findViewById(R.id.tv_repair_phone);

        } else {
            hodler = (ViewHodler) convertView.getTag();
        }
        List<Repair_item> s=list;
        String subDate=s.get(i).getAlm_sent_datetime().substring(0, 10);//这里将会获得Hello
        hodler.tv_time.setText(subDate);
        hodler.tv_device.setText(list.get(i).getEqpt_name());
        hodler.tv_Location.setText(list.get(i).getLoct_name());
        hodler.tv_repair_phone.setText(list.get(i).getRepair_sent_user_phoneno());
        int resId = Stats_icon.getStatIcon(list.get(i).getProcess_flag());
        hodler.img_repair.setImageResource(resId);
        convertView.setTag(hodler);
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

    public void setItems(List<Repair_item> list) {
        this.list = list;
        notifyDataSetChanged();
        if (list!=null&&list.size()>0){
            System.out.println("list的size是"+list.size());}else {
            System.out.println("list的null");
        }
    }
    public void addDate(List<Repair_item> mlist){
        if(null!= mlist){
            this.list.addAll(mlist);
        }
    }

    class ViewHodler {
        private TextView tv_time;
        private TextView tv_device;
        private TextView tv_Location;
        private ImageView img_repair;
        private TextView tv_repair_phone;
    }
}

