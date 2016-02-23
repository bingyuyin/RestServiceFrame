package com.suiyu.web.model.push.impl;

import com.suiyu.web.model.push.PushClientChannel;

/**
 * Created by BingyuYin on 2016/2/22.
 */
public abstract class AbstractPushClientChannel implements PushClientChannel{
    private String clientId;

    public AbstractPushClientChannel(String clientId){
        this.clientId = clientId;
    }

    public String getClientId(){
        return clientId;
    }
}
