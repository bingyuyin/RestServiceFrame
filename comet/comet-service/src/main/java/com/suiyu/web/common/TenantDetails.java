package com.suiyu.web.common;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by BingyuYin on 2016/1/30.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TenantDetails {
    private String tenantName;

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
}
