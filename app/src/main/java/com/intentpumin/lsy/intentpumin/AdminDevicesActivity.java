package com.intentpumin.lsy.intentpumin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.intentpumin.lsy.intentpumin.adapter.AdminEqptAdapter;
import com.intentpumin.lsy.intentpumin.http.HttpUtil;
import com.intentpumin.lsy.intentpumin.logic.MainLogic;
import com.intentpumin.lsy.intentpumin.network.LogUtils;
import com.intentpumin.lsy.intentpumin.tools.alldevice.devices_all;
import com.intentpumin.lsy.intentpumin.tools.allstat.stats_all;
import com.intentpumin.lsy.intentpumin.tools.alldevice.result_devices_all;
import com.intentpumin.lsy.intentpumin.tools.allstat.result_stats_all;
import com.intentpumin.lsy.intentpumin.tools.logindate.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cn.finalteam.okhttpfinal.RequestParams;
import cn.finalteam.okhttpfinal.StringHttpRequestCallback;

public class AdminDevicesActivity extends Activity {
    private ListView mlist;
    private AdminEqptAdapter adapter;
    private List<devices_all> mdata = new ArrayList<>();
    private List<stats_all> statList = new ArrayList<>();
    private SharedPreferences sp;
    private ExpandableListView eqptList;
    EqptListAdapter eqptListAdapter;
    TextView tv_admin_ss;
    Map<String, List<String>> eqpt_data = new LinkedHashMap<>();
    Map<devices_all, List<stats_all>> eqptData = new LinkedHashMap<>();
    Map<devices_all, List<stats_all>> resData = new LinkedHashMap<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindevices);
        mlist = (ListView) findViewById(R.id.list_adminss);
        init();
    }

    private void init() {

        eqptList = (ExpandableListView) findViewById(R.id.eqptList);
        eqptList.setAdapter(eqptListAdapter = new EqptListAdapter());
        eqptList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (eqptList.isGroupExpanded(groupPosition)) {
                    eqptList.collapseGroup(groupPosition);
                    mdata.get(groupPosition).setIsCheck(false);
                } else {
                    mdata.get(groupPosition).setIsCheck(true);
                    requestStaData(mdata.get(groupPosition));
                    eqptList.expandGroup(groupPosition);
                }
                return true;
            }
        });

        eqptList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                if (eqptData.get(mdata.get(groupPosition)).get(childPosition).isCheck()) {
                    eqptData.get(mdata.get(groupPosition)).get(childPosition).setIsCheck(false);

                } else {
                    eqptData.get(mdata.get(groupPosition)).get(childPosition).setIsCheck(true);
                }


                eqptListAdapter.notifyDataSetChanged();

                return true;
            }
        });

        //跳转
        tv_admin_ss = (TextView) findViewById(R.id.tv_admin_ss);
        tv_admin_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resData.clear();
                for (Map.Entry<devices_all, List<stats_all>> entry : eqptData.entrySet()) {
                    if (entry.getKey().isCheck() && entry.getValue().size() > 0) {

                        List<stats_all> statsalls = new ArrayList<stats_all>();
                        for (stats_all statsall : entry.getValue()) {
                            if (statsall.isCheck()) {
                                statsalls.add(statsall);
                            }
                        }
                        resData.put(entry.getKey(), statsalls);
                    }
                }
                Intent inter = getIntent();
                Bundle bun = new Bundle();
                bun.putSerializable("checkList", (Serializable) resData);
                inter.putExtra("ids", bun);
                setResult(101, inter);
                finish();


            }
        });
        mdata = new ArrayList<>();
        if (adapter == null) {
            adapter = new AdminEqptAdapter(this, mdata);
        }
        mlist.setAdapter(adapter);
        requestData();
    }

    private void requestData() {
        RequestParams params = new RequestParams();
        final login mlogin = (login) getIntent().getSerializableExtra("login");
        params.addFormDataPart("signature", "1");
        HttpUtil.getInstance().post(MainLogic.GET_EQPT, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(String s) {
                String eqpt_name = "";
                System.out.println("onSuccess");
                result_devices_all resulut = null;
                try {
                    if (!TextUtils.isEmpty(s)) {
                        Gson gson = new Gson();
                        resulut = gson.fromJson(s, result_devices_all.class);
                        if (resulut != null && resulut.getData() != null && resulut.getData().getItems() != null && resulut.getData().getItems().size() > 0) {
                            LogUtils.LOGE("qq", resulut.toString());
                            eqpt_name = resulut.getData().getItems().get(0).getEqpt_name();
                        }
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    System.out.println("解析异常");
                }
                System.out.println(s);
                devices_all items = new devices_all();
                items.setEqpt_name(eqpt_name);
                if (resulut != null && resulut.getData() != null && resulut.getData().getItems() != null && resulut.getData().getItems().size() > 0) {
                    mdata.addAll(resulut.getData().getItems());
                }
                adapter.setItems(mdata);


                for (devices_all eqpt : mdata) {
                    eqptData.put(eqpt, new LinkedList<stats_all>());
                }
                eqptListAdapter.notifyDataSetChanged();

                LogUtils.LOGD("login3", mdata.toString());
            }

            @Override
            public void onFinish() {
                //结束刷新
                System.out.println("完成");
            }
        });

    }


    private class EqptListAdapter extends BaseExpandableListAdapter {

        LayoutInflater inflater;

        public EqptListAdapter() {

            inflater = getLayoutInflater();
        }

        @Override
        public int getGroupCount() {
            return mdata.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return eqptData.containsKey(mdata.get(groupPosition)) ? eqptData.get(mdata.get(groupPosition)).size() : 0;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mdata.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {


            return eqptData.get(mdata.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }


        class GroupHolder {
            TextView ckEqName;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_group_eqpt, null);
                holder = new GroupHolder();
                holder.ckEqName = (TextView) convertView.findViewById(R.id.eqName);
                convertView.setTag(holder);
            } else {
                holder = (GroupHolder) convertView.getTag();
            }

            final devices_all eqpt = mdata.get(groupPosition);
            holder.ckEqName.setSelected(eqpt.isCheck());
            holder.ckEqName.setText(eqpt.getEqpt_name());


            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View con, ViewGroup parent) {
            TextView ckStat;
            if (con == null) {
                con = inflater.inflate(R.layout.item_child_stat, null);
                ckStat = (TextView) con.findViewById(R.id.eqStat);
                con.setTag(ckStat);
            } else {
                ckStat = (TextView) con.getTag();
            }


            ckStat.setSelected(eqptData.get(mdata.get(groupPosition)).get(childPosition).isCheck());
            ckStat.setText(eqptData.get(mdata.get(groupPosition)).get(childPosition).getStat_name());
            return con;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }


    private void requestStaData(final devices_all eqpt) {
        RequestParams params = new RequestParams();
        params.addFormDataPart("signature", "1");
        params.addFormDataPart("eqpt_id", eqpt.getEqpt_id());
        HttpUtil.getInstance().post(MainLogic.GET_EQPT_STAT, params, new StringHttpRequestCallback() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            protected void onSuccess(String s) {
                String stat_name = "";
                String stat_id = "";
                System.out.println("onSuccess");
                result_stats_all resulut = null;
                try {
                    if (!TextUtils.isEmpty(s)) {
                        Gson gson = new Gson();
                        resulut = gson.fromJson(s, result_stats_all.class);
                        //正常情况是用result.getData().getItems得到数据组，而不是直接去获取result.getData().getItems().get(0)
                        if (resulut != null && resulut.getData() != null && resulut.getData().getItems() != null && resulut.getData().getItems().size() > 0) {
                            LogUtils.LOGE("qq", resulut.toString());
                            stat_name = resulut.getData().getItems().get(0).getStat_name();
                        }
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    System.out.println("解析异常");
                }
                System.out.println(s);
                LogUtils.LOGD("login3", s.toString());
                stats_all items = new stats_all();
                items.setStat_name(stat_name);
                //  mdata.add(items);
                if (resulut != null && resulut.getData() != null && resulut.getData().getItems() != null && resulut.getData().getItems().size() > 0) {
                    eqptData.put(eqpt, resulut.getData().getItems());
                }
                eqptListAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFinish() {
            }
        });

    }


}
