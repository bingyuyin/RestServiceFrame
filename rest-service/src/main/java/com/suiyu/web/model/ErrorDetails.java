package com.suiyu.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by BingyuYin on 2016/1/8.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorDetails {
    private String errorMessage;
    private int errorCode;

    public ErrorDetails(){
        errorMessage = null;
        errorCode = 0;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
