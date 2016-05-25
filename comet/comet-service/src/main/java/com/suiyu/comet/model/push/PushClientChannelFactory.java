package com.suiyu.comet.model.push;

import org.atmosphere.cpr.AtmosphereResource;

import java.net.URI;

/**
 * Created by BingyuYin on 2016/2/21.
 */
public interface PushClientChannelFactory {
    PushClientChannel create(AtmosphereResource resource, String clientId);
    PushClientChannel create(URI uri, String clientId);
    PushClientChannel create(String host, String clientId);
}
