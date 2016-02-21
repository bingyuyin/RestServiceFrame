package com.suiyu.web.model.push.impl;

import com.suiyu.web.model.push.PushClient;
import com.suiyu.web.model.push.PushClientChannel;
import com.suiyu.web.model.push.PushClientChannelFactory;
import com.suiyu.web.model.push.PushClientFactory;
import org.atmosphere.cpr.AtmosphereResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by BingyuYin on 2016/2/21.
 */
@Component
public class PushClientFactoryImpl implements PushClientFactory{
    @Autowired(required = false)
    PushClientChannelFactory pushClientChannelFactory;

    @Override
    public PushClient createPushClient(AtmosphereResource resource, String clientId) {
        if(pushClientChannelFactory != null){
            PushClientChannel channel = pushClientChannelFactory.create(resource, clientId);
            if(channel != null){
                return new PushClientImpl(channel, clientId);
            }
        }
        return null;
    }
}
