package com.intentpumin.lsy.intentpumin.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.network.BcToastDialogProxy;
import com.intentpumin.lsy.intentpumin.proxy.YiActivityProxy;
import com.intentpumin.lsy.intentpumin.proxy.YiDialogProxy;


/**
 * Created by yang on 2016/7/5.
 */
public class BaseActivity extends AppCompatActivity implements BcToastDialogProxy {
    // 页面无 Navigation 的模式
    public static final int MODE_NO_NAVIGATION = 0;
    // 页面有返回 Navigation 的模式
    public static final int MODE_BACK_NAVIGATION = 1;

    protected Toolbar mToolbar;
    protected TextView mPageTitle;
    protected YiActivityProxy mActivityProxy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupData();
    }

    // 布局数据初始化
    protected void setupData() {
        mActivityProxy = new YiActivityProxy(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    // 重载 setContentView
    public void setContentView(@LayoutRes int layoutResID, int resTitleID, int pageMODE) {
        super.setContentView(layoutResID);
        setToolbarStatus();
        mToolbar = $(R.id.id_toolbar);
        mPageTitle = $(R.id.id_pageTitle);
        mPageTitle.setText(resTitleID);
        setSupportActionBar(mToolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        switch (pageMODE) {
            case MODE_NO_NAVIGATION:
                // 页面没有返回箭头
                break;

            case MODE_BACK_NAVIGATION:
                // 页面有返回剪头
                mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_18dp);
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                break;
            // 其它
        }
    }

    // 设置状态栏颜色
    protected void setToolbarStatus() {
        ToolbarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    @SuppressWarnings("unchecked")
    public <T> T $(int resID) {
        return (T) findViewById(resID);
    }

    @Override
    public void showProgressDialog(String msg, DialogInterface.OnCancelListener listener, boolean cancelable) {
        mActivityProxy.showProgressDialog(msg, listener, cancelable);
    }

    @Override
    public void showProgressDialog(String msg) {
        showProgressDialog(msg, null, true);
    }

    @Override
    public void showProgressDialog(int resid) {
        showProgressDialog(getString(resid), null, true);
    }

    @Override
    public void showMsgDialog(String title, String detials, String btnLeft, String btnRight, View.OnClickListener btnLeftListener, View.OnClickListener btnRightListener) {
        mActivityProxy.showMsgDialog(title, detials, btnLeft, btnRight, btnLeftListener, btnRightListener);
    }

    @Override
    public void showMsgDialog(String detials, String btnLeft, View.OnClickListener btnLeftListener) {
        showMsgDialog(null, detials, btnLeft, null, btnLeftListener, null);
    }

    @Override
    public void showMsgDialog(String title, String detials, String btnLeft) {
        showMsgDialog(title, detials, btnLeft, null, null, null);
    }

    @Override
    public void showMsgDialog(String detials, String btnLeft) {
        showMsgDialog(null, detials, btnLeft, null, null, null);
    }

    @Override
    public void showMsgDialog(String detials) {
        showMsgDialog(null, detials, getString(R.string.ok), null, null, null);
    }

    @Override
    public void showMsgDialog(int res) {
        showMsgDialog(getString(res));
    }

    @Override
    public void showMsgDialog() {
        mActivityProxy.showMsgDialog();
    }

    @Override
    public void showMsgDialogWithSize(int width, int height) {
        mActivityProxy.showMsgDialogWithSize(width, height);
    }

    @Override
    public YiDialogProxy getDialogProxy() {
        return mActivityProxy.getDialogProxy();
    }

    @Override
    public void cancelMsgDialog() {
        mActivityProxy.cancelMsgDialog();
    }

    @Override
    public void showProgressDialog() {
        mActivityProxy.showProgressDialog();
    }

    @Override
    public void cancelProgressDialog() {
        mActivityProxy.cancelProgressDialog();
    }

    @Override
    public void showToast(int resourceId) {
        mActivityProxy.showToast(resourceId);
    }

    @Override
    public void showToast(String text) {
        mActivityProxy.showToast(text);
    }
}