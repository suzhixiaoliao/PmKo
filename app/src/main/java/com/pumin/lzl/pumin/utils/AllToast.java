package com.pumin.lzl.pumin.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * Toast样式工具包
 *
 * 作者：lzl on 2016/6/8 14:17
 * 邮箱：zhilin——comeon@163.com
 *
 */
public  class AllToast {

    public static void alltoast(int xy,Context context,String string,int image){
        Toast toast=Toast.makeText(context,string,Toast.LENGTH_LONG);
        toast.setGravity(xy, 0, 0); //提示信息自定义位置
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(context);
        imageCodeProject.setImageResource(image);  //往toast中间加一个图片
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }
}
