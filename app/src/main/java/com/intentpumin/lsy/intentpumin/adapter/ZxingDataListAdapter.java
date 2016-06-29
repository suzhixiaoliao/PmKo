package com.intentpumin.lsy.intentpumin.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.tools.DataTask;

import java.util.List;

/**
 * Created by yang on 2016/4/5.
 */
public class ZxingDataListAdapter extends BaseAdapter {
    private SharedPreferences sp;
    private final String TAG = ZxingDataListAdapter.this.getClass().getSimpleName();
    private List<DataTask> mlist;
    private Context context;

    public ZxingDataListAdapter(Context context, List<DataTask> mlist) {
        super();
        this.context = context;
        this.mlist = mlist;
    }


    @Override
    public Object getItem(int i) {
        if (mlist != null && mlist.size() > 0) {
            return mlist.get(i);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        if (mlist != null && mlist.size() > 0) {
            return i;
        } else {
            return 0;
        }

    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
      final ViewHodler hodler;
        if (convertView == null) {
            hodler = new ViewHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_zxing_task_slidingdrawer, null);
            hodler.et_wendu = (EditText) convertView.findViewById(R.id.et_wendu);
            hodler.tv_wendu = (TextView) convertView.findViewById(R.id.tv_wendu);
            hodler.tv_wendushe = (TextView) convertView.findViewById(R.id.tv_wendushe);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }
        final DataTask task = mlist.get(i);
        Log.e("TAG", task.getFinished() + "==========" + mlist.get(i).getR_value());
        hodler.et_wendu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                task.setR_value(s.toString());
            }
        });
        hodler.et_wendu.setText(task.getR_value());
        hodler.tv_wendu.setText(task.getStat_name());
        hodler.tv_wendushe.setText(task.getUnit());
        convertView.setTag(hodler);
        return convertView;
    }

    @Override
    public int getCount() {
        //这儿返回的int类型代表的是要去加载多少个数据，不能只返回0
        if (mlist != null && mlist.size() > 0) {
            System.out.println("`list的size是" + mlist.size());
            return mlist.size();
        } else {
            System.out.println("`list的null");
            return 0;
        }

    }

    public void setItems(List<DataTask> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
        if (mlist != null && mlist.size() > 0) {
            System.out.println("list的size是" + mlist.size());
        } else {
            System.out.println("list的null");
        }
    }

    class ViewHodler {
        private TextView tv_wendu;
        private TextView tv_wendushe;
        private EditText et_wendu;
    }



}