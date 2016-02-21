package com.suiyu.web.model.push.impl;

import com.suiyu.web.model.push.PushClient;
import com.suiyu.web.model.push.PushClientChannel;
import com.suiyu.web.model.push.PushClientChannelFactory;
import org.atmosphere.cpr.AtmosphereResource;
import org.springframework.stereotype.Component;

/**
 * Created by BingyuYin on 2016/2/21.
 */
@Component
public class PushClientChannelFactoryImpl implements PushClientChannelFactory {
    @Override
    public PushClientChannel create(AtmosphereResource resource, String clientId) {
        return null;
    }
}
