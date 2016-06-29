package com.pumin.lzl.pumin.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intentpumin.lsy.intentpumin.R;


/*
*@author lzl
*created at 2016/6/8 12:49
*趋势图表-lzl
*/
public class Historicaltask_fragment extends Fragment {

    View view;

    public Historicaltask_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = View.inflate(getContext(), R.layout.historicaltask_fragment, null);
        return view;
    }

}
