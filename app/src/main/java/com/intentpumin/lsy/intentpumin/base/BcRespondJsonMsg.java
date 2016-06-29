package com.intentpumin.lsy.intentpumin.base;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @param <T>
 */
public final class BcRespondJsonMsg<T extends BcRespondBaseMsg> {

    @SerializedName("message")
    private String mMessage;

    @SerializedName("result")
    private T mResult;

    @SerializedName("status")
    private boolean mStatus;

    @SerializedName("timeout")
    private int mTimeout;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public T getResult() {
        return mResult;
    }

    public void setResult(T result) {
        mResult = result;
    }

    public boolean isStatus() {
        return mStatus;
    }

    public void setStatus(boolean status) {
        mStatus = status;
    }

    public int getTimeout() {
        return mTimeout;
    }

    public void setTimeout(int timeout) {
        mTimeout = timeout;
    }

}
