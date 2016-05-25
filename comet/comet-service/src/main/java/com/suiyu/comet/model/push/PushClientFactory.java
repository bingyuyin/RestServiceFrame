package com.suiyu.comet.model.push;

import org.atmosphere.cpr.AtmosphereResource;

import java.net.URI;

/**
 * Created by BingyuYin on 2016/2/21.
 */
public interface  PushClientFactory {
    PushClient create(AtmosphereResource resource, String clientId);
    PushClient create(URI uri, String host, String clientId);
    PushClient create(String host, String clientId);
}
