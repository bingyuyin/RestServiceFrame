package com.suiyu.web.model.push.impl;

import com.suiyu.web.model.push.MessageHandler;
import com.suiyu.web.model.push.PushMessage;
import com.suiyu.web.model.push.PushMessageFactory;
import com.suiyu.web.model.push.PushServer;
import com.suiyu.web.service.PushMessageService;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.net.URI;

/**
 * Created by yinbing on 2/23/2016.
 */
@WebSocket
public class ServerWebSocketChannel extends AbstractWebSocketChannel {

    private PushMessageService pushMessageService;
    private PushMessageFactory pushMessageFactory;
    private MessageHandler messageHandler;

    public ServerWebSocketChannel(MessageHandler messageHandler,
                                  PushMessageService pushMessageService,
                                  PushMessageFactory pushMessageFactory,
                                  URI uri, String clientId, PushServer pushServer, String proxyUrl, boolean useJetty){
        super(uri, clientId, pushServer, proxyUrl, useJetty);
        this.pushMessageService = pushMessageService;
        this.pushMessageFactory = pushMessageFactory;
        this.messageHandler = messageHandler;
    }

   @Override
    public void send(PushMessage pushMessage) {
        if(pushMessage == null){
            return ;
        }
       pushMessageService.onOutgoingMessage(pushMessage);
       send(pushMessageFactory.convertToJson(pushMessage).toString());
    }

    @Override
    public void handleMessage(String message) {
        try{
            messageHandler.handleMessage(message, getClientId());
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
