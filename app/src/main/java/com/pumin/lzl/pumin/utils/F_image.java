package com.pumin.lzl.pumin.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.intentpumin.lsy.intentpumin.R;

/**
 * 作者：lzl on 2016/7/20 10:10
 * 邮箱：zhilin——comeon@163.com
 * 匹配图片的判断，图片的压缩。
 */
public abstract class F_image {

    //图片的压缩
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


    public static void image_s(String eqpt, ImageView img, Context con) {
        if (eqpt.equals("FA0101001")) {
            img.setImageBitmap(decodeSampledBitmapFromResource(con.getResources(), R.mipmap.abygtt, 100, 100));
        }
        if (eqpt.equals("FA0201001")) {
            img.setImageBitmap(decodeSampledBitmapFromResource(con.getResources(), R.mipmap.abbjq, 100, 100));
        }
        if (eqpt.equals("FA0301001")) {
            img.setImageBitmap(decodeSampledBitmapFromResource(con.getResources(), R.mipmap.abdh, 100, 100));
        }
        if (eqpt.equals("FA0401001")) {
            img.setImageBitmap(decodeSampledBitmapFromResource(con.getResources(), R.mipmap.abxhs, 100, 100));
        }
        if (eqpt.equals("FA0501001")) {
            img.setImageBitmap(decodeSampledBitmapFromResource(con.getResources(), R.mipmap.abpdpxg, 100, 100));
        }
        if (eqpt.equals("FA0601001")) {
            img.setImageBitmap(decodeSampledBitmapFromResource(con.getResources(), R.mipmap.abfj, 100, 100));
        }
        if (eqpt.equals("FA0701001")) {
            img.setImageBitmap(decodeSampledBitmapFromResource(con.getResources(), R.mipmap.abdzx, 100, 100));
        }
        if (eqpt.equals("FA0801001")) {
            img.setImageBitmap(decodeSampledBitmapFromResource(con.getResources(), R.mipmap.abxfsb, 100, 100));
        }
        if (eqpt.equals("FA0901001")) {
            img.setImageBitmap(decodeSampledBitmapFromResource(con.getResources(), R.mipmap.ablxb, 100, 100));
        }
    }
}
