package com.suiyu.comet.model.push.impl;

import com.suiyu.comet.common.JsonUtils;
import com.suiyu.comet.model.push.MessageHandler;
import com.suiyu.comet.model.push.PushMessage;
import com.suiyu.comet.model.push.PushMessageFactory;
import com.suiyu.comet.model.push.PushMessageType;
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
        if(pushMessage != null){
            PushMessageType messageType = pushMessage.getType();
            if(messageType == PushMessageType.NOTIFICATION){

            }else if(messageType == PushMessageType.REQUEST) {

            }else if(messageType == PushMessageType.RESPONSE){

            }
        }

    }

}
