package com.suiyu.web.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by BingyuYin on 2016/1/30.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestfulStatusDetails {
    private String errorMessage;
    private int errorCode;

    public RestfulStatusDetails(){

    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
