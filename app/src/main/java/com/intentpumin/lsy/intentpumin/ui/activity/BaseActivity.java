package com.intentpumin.lsy.intentpumin.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.intentpumin.lsy.intentpumin.ui.PrefUtil;
import com.intentpumin.lsy.intentpumin.ui.swipeback.SwipeBackActivity;
import com.intentpumin.lsy.intentpumin.ui.swipeback.SwipeBackLayout;

import butterknife.ButterKnife;


/**
 * Created by laucherish on 16/3/15.
 */
public abstract class BaseActivity extends SwipeBackActivity {

    protected SwipeBackLayout mSwipeBackLayout;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            scrollToFinishActivity();
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(PrefUtil.getThemeRes());
        setContentView(getLayoutId());
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        ButterKnife.bind(this);
        afterCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);
}
