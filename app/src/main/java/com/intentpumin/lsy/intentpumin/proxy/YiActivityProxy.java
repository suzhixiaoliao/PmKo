package com.intentpumin.lsy.intentpumin.proxy;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Handler;
import android.view.View;

import com.intentpumin.lsy.intentpumin.util.StringUtil;

public class YiActivityProxy {
    // Toast代理
    private YiToastProxy mToastProxy = null;

    // 对话框代理
    private YiDialogProxy mDialogProxy = null;

    private Context mContext;

    private Handler mHandler = new Handler();

    public YiActivityProxy(Context context) {
        mContext = context;
    }

    public void onDestroy() {
        if (mDialogProxy != null) {
            mDialogProxy.cancelMsgDialog();
            mDialogProxy.cancelProgressDialog();
        }
    }

    /*******************************************************************************
     * ToastProxy
     *******************************************************************************/
    public void showToast(final int resourceId) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                initToastProxy();
                mToastProxy.showToast(resourceId);
            }
        });
    }

    public void showToast(final String text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                initToastProxy();
                mToastProxy.showToast(text);
            }
        });
    }

    protected void initToastProxy() {
        if (mToastProxy == null) {
            mToastProxy = new YiToastProxy(mContext);
        }
    }

    /*******************************************************************************
     * DialogProxy
     *******************************************************************************/
    protected void initDialogProxy() {
        if (mDialogProxy == null) {
            mDialogProxy = new YiDialogProxy(mContext);
        }
    }

    public void showMsgDialog() {
        // TODO Auto-generated method stub
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                initDialogProxy();
                mDialogProxy.showMsgDialog();
            }
        });
    }

    public void showMsgDialogWithSize(final int width, final int height) {
        // TODO Auto-generated method stub
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                initDialogProxy();
                mDialogProxy.showMsgDialogWithSize(width, height);
            }
        });
    }

    public void showProgressDialog() {
        // TODO Auto-generated method stub
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                initDialogProxy();
                mDialogProxy.showProgressDialog();
            }
        });
    }

    public void cancelProgressDialog() {
        // TODO Auto-generated method stub
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                initDialogProxy();
                mDialogProxy.cancelProgressDialog();
            }
        });
    }

    public YiDialogProxy getDialogProxy() {
        // TODO Auto-generated method stub
        initDialogProxy();
        return mDialogProxy;
    }

    public void cancelMsgDialog() {
        // TODO Auto-generated method stub
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                initDialogProxy();
                mDialogProxy.cancelMsgDialog();
            }
        });
    }

    /*******************************************************************************
     * DialogProxy expand
     *******************************************************************************/
    public void showMsgDialog(final String title, final String detials,
                              final String btnLeft, final String btnRight,
                              final View.OnClickListener btnLeftListener,
                              final View.OnClickListener btnRightListener) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                YiDialogProxy dialogProxy = getDialogProxy();
                if (!StringUtil.isStringInvalid(title)) {
                    dialogProxy.showMsgDialogTitle();
                    dialogProxy.setMsgDialogTitle(title);
                } else {
                    dialogProxy.hideMsgDialogTitle();
                }

                if (!StringUtil.isStringInvalid(detials)) {
                    dialogProxy.showMsgDialogDetailMsg();
                    dialogProxy.setMsgDialogDetailMsg(detials);
                } else {
                    dialogProxy.hideMsgDialogDetailMsg();
                }

                if (!StringUtil.isStringInvalid(btnLeft)) {
                    dialogProxy.showMsgDialogBtnLeft();
                    dialogProxy.setMsgDialogBtnLeftText(btnLeft);
                } else {
                    dialogProxy.hideMsgDialogBtnLeft();
                }

                if (!StringUtil.isStringInvalid(btnRight)) {
                    dialogProxy.showMsgDialogBtnRight();
                    dialogProxy.setMsgDialogBtnRightText(btnRight);
                } else {
                    dialogProxy.hideMsgDialogBtnRight();
                }
                dialogProxy.setMsgDialogCanceledOnTouchOutside(true);
                dialogProxy.setMsgDialogBtnLeftClickListener(btnLeftListener);
                dialogProxy.setMsgDilaogBtnRightClickListener(btnRightListener);
                dialogProxy.showMsgDialog();
            }
        });
    }

    public void showProgressDialog(final String msg,
                                   final OnCancelListener listener, final boolean cancelable) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                YiDialogProxy dialogProxy = getDialogProxy();

                if (!StringUtil.isStringInvalid(msg)) {
                    dialogProxy.showProgressDialogMsg();
                    dialogProxy.setProgressDialogMsgText(msg);
                } else {
                    dialogProxy.hideProgressDialogMsg();
                }

                dialogProxy.setProgressDialogCancelable(cancelable);
                dialogProxy.setProgressDialogCancelListener(listener);

                dialogProxy.showProgressDialog();
            }
        });
    }
}
