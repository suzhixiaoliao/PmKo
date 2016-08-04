package com.intentpumin.lsy.intentpumin.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.network.BcToastDialogProxy;
import com.intentpumin.lsy.intentpumin.proxy.YiActivityProxy;
import com.intentpumin.lsy.intentpumin.proxy.YiDialogProxy;

/**
 * Created by yang on 2016/8/4.
 */
public class BcBaseActivity extends Activity implements BcToastDialogProxy {

    protected YiActivityProxy mActivityProxy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityProxy = new YiActivityProxy(this);
    }

    public void showProgressDialog(String msg, DialogInterface.OnCancelListener listener, boolean cancelable) {
        mActivityProxy.showProgressDialog(msg, listener, cancelable);
    }

    public void showProgressDialog(String msg) {
        showProgressDialog(msg, null, true);
    }

    public void showProgressDialog(int resid) {
        showProgressDialog(getString(resid), null, true);
    }

    public void showMsgDialog(String title, String detials, String btnLeft, String btnRight, View.OnClickListener btnLeftListener, View.OnClickListener btnRightListener) {
        mActivityProxy.showMsgDialog(title, detials, btnLeft, btnRight, btnLeftListener, btnRightListener);
    }

    public void showMsgDialog(String detials, String btnLeft, View.OnClickListener btnLeftListener) {
        showMsgDialog(null, detials, btnLeft, null, btnLeftListener, null);
    }

    public void showMsgDialog(String title, String detials, String btnLeft) {
        showMsgDialog(title, detials, btnLeft, null, null, null);
    }

    public void showMsgDialog(String detials, String btnLeft) {
        showMsgDialog(null, detials, btnLeft, null, null, null);
    }

    @Override
    public void showMsgDialog(String detials) {
        showMsgDialog(null, detials, getString(R.string.ok), null, null, null);
    }

    public void showMsgDialog(int res) {
        showMsgDialog(getString(res));
    }

    public void showMsgDialog() {
        mActivityProxy.showMsgDialog();
    }
    public void showMsgDialogWithSize(int width, int height) {
        mActivityProxy.showMsgDialogWithSize(width, height);
    }

    public YiDialogProxy getDialogProxy() {
        return mActivityProxy.getDialogProxy();
    }

    public void cancelMsgDialog() {
        mActivityProxy.cancelMsgDialog();
    }

    public void showProgressDialog() {
        mActivityProxy.showProgressDialog();
    }


    public void cancelProgressDialog() {
        mActivityProxy.cancelProgressDialog();
    }

    public void showToast(int resourceId) {
        mActivityProxy.showToast(resourceId);
    }


    public void showToast(String text) {
        mActivityProxy.showToast(text);
    }
}

