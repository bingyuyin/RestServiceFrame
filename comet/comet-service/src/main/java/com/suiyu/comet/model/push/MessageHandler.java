package com.suiyu.comet.model.push;

import org.atmosphere.cpr.AtmosphereResource;

/**
 * Created by yinbing on 2/24/2016.
 */
public interface MessageHandler {
    void handleMessage(AtmosphereResource resource);
    void handleMessage(PushMessage pushMessage, String clientId);
    void handleMessage(String message, String clientId);
}
