package com.suiyu.web.model.push.impl;

import com.suiyu.web.model.push.*;
import com.suiyu.web.service.PushMessageService;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.websocket.WebSocketEventListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by BingyuYin on 2016/2/21.
 */
public abstract class AbstractPushClientChannelFactory implements PushClientChannelFactory {
    @Autowired
    protected PushServer pushServer;

    @Autowired
    protected PushMessageService pushMessageService;

    @Autowired
    protected PushMessageFactory pushMessageFactory;

    @Override
    public PushClientChannel create(AtmosphereResource resource, String clientId) {
        if(resource != null) {
            resource.addEventListener(new PushServerAtmosphereResourceEventListener(clientId));
            return new AtmosphereResourceChannel(resource, pushMessageService, pushMessageFactory);
        }
        return null;
    }

    private class PushServerAtmosphereResourceEventListener extends WebSocketEventListenerAdapter {

        private String clientId;

        public PushServerAtmosphereResourceEventListener(String clientId){
            this.clientId = clientId;
        }

        @Override
        public void onConnect(WebSocketEvent event){

        }

        @Override
        public void onClose(WebSocketEvent event){
            pushServer.unsubscribe(clientId);
        }

        @Override
        public void onDisconnect(AtmosphereResourceEvent event){
            pushServer.unsubscribe(clientId);
        }

    }
}
