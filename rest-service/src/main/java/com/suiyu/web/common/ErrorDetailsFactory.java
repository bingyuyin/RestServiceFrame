package com.suiyu.web.common;

import com.suiyu.web.model.ErrorDetails;

/**
 * Created by BingyuYin on 2016/1/8.
 */
public class ErrorDetailsFactory {

    public ErrorDetailsFactory(){

    }

    public static ErrorDetails createErrorDetailsFromErrorCode(int errorCode){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorCode(errorCode);
        return errorDetails;
    }
}
