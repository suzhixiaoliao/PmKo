package com.intentpumin.lsy.intentpumin.tools.data;

import com.intentpumin.lsy.intentpumin.tools.data.base.BcRequrstBaseMsg;

/**
 * Created by yang on 2016/5/19.
 */
public class LoginRequestMsg extends BcRequrstBaseMsg{

    private String phoneno;
    private String signature;
    private String pswd;

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
    public String toString() {
        return "LoginRequestMsg{" +
                "phoneno='" + phoneno + '\'' +
                ", signature='" + signature + '\'' +
                ", pswd='" + pswd + '\'' +
                '}';
    }
}
