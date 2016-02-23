package com.suiyu.web.model.push.impl;

import com.suiyu.web.model.push.PushMessage;
import com.suiyu.web.model.push.PushMessageFactory;
import com.suiyu.web.service.PushMessageService;
import org.atmosphere.cpr.AtmosphereResource;

import java.io.IOException;

/**
 * Created by BingyuYin on 2016/2/22.
 */
public class AtmosphereResourceChannel extends AbstractPushClientChannel {
    private AtmosphereResource client;
    private PushMessageService pushMessageService;
    private PushMessageFactory pushMessageFactory;

    public AtmosphereResourceChannel(AtmosphereResource resource, PushMessageService pushMessageService, PushMessageFactory pushMessageFactory){
        super(resource.uuid());
        this.client = resource;
        this.pushMessageFactory = pushMessageFactory;
        this.pushMessageService  = pushMessageService;
    }
    @Override
    public void send(String message) {
        if(client.getRequest() != null){
            client.getResponse().write(message);
        }else{
            // report error
        }
    }

    @Override
    public void send(PushMessage pushMessage) {
        if(null == pushMessage){
            return ;
        }
        pushMessageService.onOutgoingMessage(pushMessage);
        send(pushMessageFactory.convertToJson(pushMessage).toString());
    }

    @Override
    public void disconnect() {
        if(client != null){
            try{
                client.close();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        client = null;
    }
}
