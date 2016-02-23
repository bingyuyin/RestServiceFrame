package com.suiyu.web.model.push;

import org.atmosphere.cpr.AtmosphereResource;

/**
 * Created by BingyuYin on 2016/2/21.
 */
public interface PushServer {
    void subscribe(AtmosphereResource atmosphereResource, String pushClientId);
    void notifyMessage(PushMessage pushMessage);
    void unsubscribe(String pushClientId);

}
