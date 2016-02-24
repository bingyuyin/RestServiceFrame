package com.suiyu.web.model.push.impl;

import com.suiyu.web.model.push.PushServer;
import com.suiyu.web.model.push.impl.wsclient.*;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.net.URI;

/**
 * Created by BingyuYin on 2016/2/22.
 */
@WebSocket
public abstract class AbstractWebSocketChannel extends AbstractPushClientChannel implements WebsocketLifeCycle, WebsocketMessageHandler {

    private static final int MAX_WAIT_COUNT = 10;
    private static final int WAIT_TIME_INTERVAL = 1000;

    private final WebsocketClientInterface websocketClient;
    private URI serverURI;
    private String proxyURL;
    private PushServer pushServer;
    private SessionInterface session;

    protected AbstractWebSocketChannel(URI uri, String clientId, PushServer pushServer, String proxyUrl, boolean useJetty){
        super(clientId);
        this.serverURI = uri;
        this.pushServer = pushServer;
        this.proxyURL = proxyUrl;
        WebSocketClientFactory factory = new WebSocketClientFactory();
        this.websocketClient = factory.createWebSocketClient(proxyUrl, this, useJetty);
    }

    @OnWebSocketMessage
    public void doHandle(String message){
        handleMessage(message);
    }

    @OnWebSocketError
    public void onError(Throwable ex){
        // error, exception is ex
        disconnect();
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason){
        this.session = null;
        disconnect();
    }

    @Override
    public void send(String text){
        try {
            if(session != null){
                session.sendText(text);
            }else{
                // session is null, cant send message
                disconnect();
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    @Override
    public void disconnect(){
        try {
            if(session != null){
                // this should call onClose that should unregister this web socket
                session.close();
            }else{
                pushServer.unsubscribe(getClientId());
            }
            session = null;
        }catch(Exception e){
            // disconnect error
            pushServer.unsubscribe(getClientId());
        }
    }

    public boolean connect(){
        try{
            return connectBlocking();
        }catch(Exception e){
            // connect failed
            disconnect();
            return false;
        }
    }

    private boolean connectBlocking() throws InterruptedException{
        int count = 0;
        session = websocketClient.connectSession(getProxyURL(), serverURI, this);
        if(session == null){
            return false;
        }
        while(!session.isOpen() && count < MAX_WAIT_COUNT){
            Thread.sleep(WAIT_TIME_INTERVAL);
            count++;
        }
        return session.isOpen();
    }

    public String getProxyURL(){
        return this.proxyURL;
    }
}
