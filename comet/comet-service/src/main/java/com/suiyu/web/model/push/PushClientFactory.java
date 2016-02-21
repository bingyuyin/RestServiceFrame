package com.suiyu.web.model.push;


import org.atmosphere.cpr.AtmosphereResource;
import org.springframework.stereotype.Component;

/**
 * Created by BingyuYin on 2016/2/21.
 */
@Component
public interface  PushClientFactory {
    PushClient createPushClient(AtmosphereResource resource, String clientId);
}
