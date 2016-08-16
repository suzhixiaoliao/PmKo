package com.intentpumin.lsy.intentpumin.util;

/**
 * Created by Seven1979 on 2015/10/15.
 */
public class StringUtil {

    public static boolean isStringInvalid(String str) {
        if (str == null || str.length() < 1) {
            return true;
        }
        return false;
    }
}
