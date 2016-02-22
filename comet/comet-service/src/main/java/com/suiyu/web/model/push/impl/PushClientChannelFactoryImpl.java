package com.suiyu.web.model.push.impl;

import com.suiyu.web.model.push.PushClientChannel;
import com.suiyu.web.model.push.PushClientChannelFactory;
import com.suiyu.web.model.push.PushMessageFactory;
import com.suiyu.web.service.PushMessageService;
import org.atmosphere.cpr.AtmosphereResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by BingyuYin on 2016/2/21.
 */
@Component
public class PushClientChannelFactoryImpl implements PushClientChannelFactory {

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private PushMessageFactory pushMessageFactory;

    @Override
    public PushClientChannel create(AtmosphereResource resource, String clientId) {
        return new AtmosphereResourceChannel(resource, pushMessageService, pushMessageFactory);
    }
}
