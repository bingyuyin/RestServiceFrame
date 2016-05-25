package com.suiyu.comet.model.push.impl.wsclient;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by yinbing on 2/23/2016.
 */
public class JettyWebsocketClient implements WebsocketClientInterface {

    private static final int WS_WAIT_TIMEOUT_SECONDS = 10;
    // 600000 ms <=> 10 min utes
    private static final int IDLE_TIMEOUT = 600000;
    // 64 KB <=> 65536
    private static final int INPUT_BUFFER_SIZE = 65536;
    // 5 Megabyte <=> 5242880
    private static final int MAX_MESSAGE_SIZE = 5242880;

    private final WebSocketClient webSocketClient;

    public JettyWebsocketClient(){
        webSocketClient = new WebSocketClient();
        webSocketClient.getPolicy().setMaxTextMessageSize(MAX_MESSAGE_SIZE);
        webSocketClient.getPolicy().setMaxTextMessageBufferSize(INPUT_BUFFER_SIZE);
        webSocketClient.getPolicy().setMaxBinaryMessageBufferSize(INPUT_BUFFER_SIZE);
        webSocketClient.getPolicy().setInputBufferSize(INPUT_BUFFER_SIZE);
        webSocketClient.getPolicy().setIdleTimeout(IDLE_TIMEOUT);

    }

    @Override
    public SessionInterface connectSession(String proxyURL, URI serverURI, WebsocketMessageHandler handler) {
        Future<org.eclipse.jetty.websocket.api.Session> connectPromise = null;
        try {
            connectPromise = webSocketClient.connect(handler, serverURI, new ClientUpgradeRequest());
            Session newSession = connectPromise.get(WS_WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            return new JettySession(newSession);
        }catch(Exception e){
            if(connectPromise != null) {
                connectPromise.cancel(true);
            }
        }
        return null;

    }
}
