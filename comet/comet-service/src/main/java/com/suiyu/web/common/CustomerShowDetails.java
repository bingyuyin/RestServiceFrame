package com.suiyu.web.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by BingyuYin on 2016/1/30.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerShowDetails {
    private String id;
    private String name;
    private boolean isAdmin;
    private List<Object> roles;

    public CustomerShowDetails(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<Object> getRoles() {
        return roles;
    }

    public void setRoles(List<Object> roles) {
        this.roles = roles;
    }
}
