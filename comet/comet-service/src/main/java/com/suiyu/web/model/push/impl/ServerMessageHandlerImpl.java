package com.suiyu.web.model.push.impl;

import com.suiyu.web.common.JsonUtils;
import com.suiyu.web.model.push.MessageHandler;
import com.suiyu.web.model.push.PushMessage;
import com.suiyu.web.model.push.PushMessageFactory;
import org.atmosphere.cpr.AtmosphereResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by yinbing on 2/24/2016.
 */
@Component
public class ServerMessageHandlerImpl implements MessageHandler {
    @Autowired
    private PushMessageFactory pushMessageFactory;

    @Override
    public void handleMessage(AtmosphereResource resource) {
        try {
            String message = JsonUtils.readJson(resource.getRequest().getReader());
            handleMessage(message, resource.uuid());
        }catch (IOException ioe){
            // failed to handler message
        }
    }


    @Override
    public void handleMessage(String message, String clientId) {
        PushMessage pushMessage = pushMessageFactory.createFromJsonString(message);
        if(pushMessage != null){
            handleMessage(pushMessage, clientId);
        }
    }
    @Override
    public void handleMessage(PushMessage pushMessage, String clientId) {

    }

}
