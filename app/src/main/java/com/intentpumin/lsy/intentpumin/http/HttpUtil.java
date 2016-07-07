package com.intentpumin.lsy.intentpumin.http;


import android.content.Context;


import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.CookieJar;

public class HttpUtil {


    private static HttpUtil httpUtil;
    private static Context mContext;
    private static OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();


    public static void init(Context context) {
        mContext = context;
        OkHttpFinal.getInstance().init(getConfig().build());
    }

    public static synchronized HttpUtil getInstance() {
        if (httpUtil == null) {
            httpUtil = new HttpUtil();
        }
        return httpUtil;
    }



    public void post(String url,RequestParams params, BaseHttpRequestCallback callback) {

        HttpRequest.post(url, params, callback);
    }


    public void get(String url, BaseHttpRequestCallback callback) {
        HttpRequest.get(url, callback);
    }

    public static OkHttpFinalConfiguration.Builder getConfig() {
        builder.setRetryOnConnectionFailure(false)
                .setCookieJar(CookieJar.NO_COOKIES)
                .setTimeout(10000)
                .setDebug(true);
        return builder;
    }


}
