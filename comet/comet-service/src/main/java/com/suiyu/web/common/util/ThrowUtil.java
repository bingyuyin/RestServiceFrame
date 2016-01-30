package com.suiyu.web.common.util;

/**
 * Created by BingyuYin on 2016/1/30.
 */
public final class ThrowUtil {
    private ThrowUtil(){

    }

    public static void verifyArgumentNotNull(Object obj){
        if(null == obj){
            throw new IllegalArgumentException("Argument can not be null");
        }
    }

}
