package com.pumin.lzl.pumin.fragment;

import android.content.Intent;
import android.location.GpsStatus;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.intentpumin.lsy.intentpumin.R;
import com.pumin.lzl.pumin.TaskList_alter;
import com.pumin.lzl.pumin.adapter.Alter_frag_adapter;
import com.pumin.lzl.pumin.bean.Alternate_object;
import com.pumin.lzl.pumin.utils.Url;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
*@author lzl
*created at 2016/6/8 12:48
* 历史任务
*/
public class Alternate_fragment extends Fragment {
    private String TAG = "Alternate_fragment";

    View view;
    Intent it;

    //json解析
    JSONObject jsonobject;
    JSONObject object;
    RequestQueue request;
    String info;
    Alternate_object alter_object;  //对象名
    Alternate_object alter;

    //这是传过来的设备编号
    String str;
    //日期选择
    private String end_time; //目前日期
    private String star;  //本月当前第一天
    //    分页
    int page = 1;  //页数
    int count = 5;  //数据量
    String items; //判断的条件

    //适配器的数据适配
    ArrayList<Alternate_object> alter_Array = new ArrayList<>();
    Alter_frag_adapter alter_adapter;
    private PullToRefreshListView alter_list;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            handler.sendMessageDelayed(null, 3000);
            super.handleMessage(msg);
        }
    };

    public Alternate_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        request = Volley.newRequestQueue(getContext());//得到volley对象
        view = View.inflate(getContext(), R.layout.alternate_fragment, null);
        initview();
        query();
        alter_list.setMode(Mode.BOTH);  //设定listview的刷新模式
        return view;
    }

    //初始化控件
    private void initview() {
        alter_list = (PullToRefreshListView) view.findViewById(R.id.alter_list);
    }


    //请求--网络加载
    public void query() {
        SimpleDateFormat sdateformat = new SimpleDateFormat("yyyy-MM-dd");
        end_time = sdateformat.format(new java.util.Date());

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DATE, 1);
        SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
        star = simpleFormate.format(calendar.getTime());
        System.out.print(star + "xxxxxxxxxxxxxxxxxxxxx");

        Bundle bundle1 = getArguments(); //获取main中传来的值
        String path = "";
        //把path转码
        try {
            str = bundle1.getString("set_url");
            //接口规范
            //表名：D_EXEC_M
//            http://app.pumintech.com:40000/api/user/?signature=1
//            http://10.16.1.201:40000/api/user/?signature=1
            path = Url.path + "get_mt_list_by_eqpt_id?" +
                    "signature=1&s_date=" + star + "&e_date=" + end_time + "&eqpt_id=" + str
                    + "&page=" + page + "&count=" + count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(path, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        // 成功获取数据后将数据显示在屏幕上
                        try {
                            info = response.toString();
                            // info = response.getString("UTF-8");
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Log.e("TAG", response + "" + "url");
                        initadapter(info);
                        System.out.println("这是alter_fragment中加载数据，数据加载成功" + info);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", error.getMessage(), error);
                System.out.println("不好意思，加载失败");
            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    JSONObject jsonObject = new JSONObject(
                            new String(response.data, "UTF-8"));
                    return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (Exception je) {
                    return Response.error(new ParseError(je));
                }
            }
        };
        request.add(jsonObjectRequest);
    }

    //数据适配器的适配
    private void initadapter(String info2) {
//        alter_Array = new ArrayList<>();
        try {
            jsonobject = new JSONObject(info2);
            String data1 = jsonobject.getString("data");

            jsonobject = new JSONObject(data1);
            items = jsonobject.getString("items");

            System.out.println(items + "xxxxxxxxxxxxxxxxxxxxx");
            JSONArray array = new JSONArray(items);
            if (array.length() > 0 && array != null) {
                for (int w = 0; w < array.length(); w++) {
                    object = array.getJSONObject(w);
                    alter_object = new Alternate_object(
                            object.getString("date"), object.getString("pmt_name")
                            , object.getString("smt_name"), object.getString("finished"));
                    alter_Array.add(alter_object);

                }
            } else {
                Toast.makeText(getContext(), "没有更多数据了~", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        alter_adapter = new Alter_frag_adapter(alter_Array, getContext());
        alter_list.getRefreshableView().setAdapter(alter_adapter);  //碎片中使用
        alter_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //刷新的操作
                //三个参数：启动任务执行的输入参数，后台任务执行的进度，后台计算结果的类型
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        page = 1;
                        alter_Array.clear();
                        alter_Array = new ArrayList<Alternate_object>();
                        as();
                    }
                }.execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //刷新的操作
                //三个参数：启动任务执行的输入参数，后台任务执行的进度，后台计算结果的类型
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        page++;
                        as();
                    }
                }.execute();
            }
        });


        //listview的点击事件
        //逻辑判断是否有数据，能否进入查看准确数据
        alter_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!alter_Array.isEmpty()) {
                    alter = alter_Array.get(position - 1);
                }
                it = new Intent(getContext(), TaskList_alter.class);
                it.putExtra("charge", str);
                it.putExtra("inputdate", alter.getStart_time());
                startActivity(it);
            }
        });
    }

    public void as() {
        query();
        alter_adapter.notifyDataSetChanged();
        alter_list.onRefreshComplete();
        alter_list.setSelected(true);
    }
}
