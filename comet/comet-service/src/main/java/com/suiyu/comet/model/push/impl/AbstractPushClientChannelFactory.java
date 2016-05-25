package com.suiyu.comet.model.push.impl;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.suiyu.comet.model.push.*;
import com.suiyu.comet.service.PushMessageService;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.websocket.WebSocketEventListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by BingyuYin on 2016/2/21.
 */
public abstract class AbstractPushClientChannelFactory implements PushClientChannelFactory {
    protected static final String topicExchangeName = "TOPIC-EXCHANGE";

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

    protected Connection newMessageQueueConnection(String host) throws IOException{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        return connectionFactory.newConnection();
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
