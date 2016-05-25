package com.suiyu.comet.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by BingyuYin on 2016/1/30.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusDetails {
    private String message;
    private int errorCode;
    private boolean isError;
    private Object data;

    public StatusDetails(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isError() {
        return isError;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
