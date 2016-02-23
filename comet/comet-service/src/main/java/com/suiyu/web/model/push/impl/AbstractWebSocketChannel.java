package com.suiyu.web.model.push.impl;

import com.suiyu.web.model.push.PushMessage;
import com.suiyu.web.model.push.PushServer;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.net.URI;

/**
 * Created by BingyuYin on 2016/2/22.
 */
@WebSocket
public abstract class AbstractWebSocketChannel extends AbstractPushClientChannel {

    private PushServer pushServer;

    protected AbstractWebSocketChannel(URI uri, String clientId, PushServer pushServer, String proxyUrl, boolean useJettySession){
        super(clientId);
    }

    @Override
    public void send(PushMessage pushMessage) {

    }

    @Override
    public void send(String message) {

    }

    @Override
    public void disconnect(){

    }
}
