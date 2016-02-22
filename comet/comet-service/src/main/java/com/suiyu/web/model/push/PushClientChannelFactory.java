package com.suiyu.web.model.push;

import org.atmosphere.cpr.AtmosphereResource;

/**
 * Created by BingyuYin on 2016/2/21.
 */
public interface PushClientChannelFactory {
    PushClientChannel create(AtmosphereResource resource, String clientId);
}
