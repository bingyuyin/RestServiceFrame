package com.suiyu.comet.model.push.impl.wsclient;

import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;

import javax.websocket.*;
import java.net.URI;

/**
 * Created by yinbing on 2/23/2016.
 */
public class TyrusWebsocketClient implements WebsocketClientInterface {
    private final WebsocketLifeCycle websocketLifeCycle;

    public TyrusWebsocketClient(WebsocketLifeCycle websocketLifeCycle){
        this.websocketLifeCycle = websocketLifeCycle;
    }

    @Override
    public SessionInterface connectSession(String proxyURL, URI serverURI, final WebsocketMessageHandler handler) {
        Session newSession;
        try {
            final ClientManager client = ClientManager.createClient();

            if(proxyURL != null && !proxyURL.trim().isEmpty()){
                client.getProperties().put(ClientProperties.PROXY_URI, proxyURL);
            }

            Endpoint endPoint = new Endpoint() {
                @Override
                public void onClose(Session session, CloseReason closeReason) {
                    super.onClose(session, closeReason);
                    websocketLifeCycle.onClose(closeReason.getCloseCode().getCode(), closeReason.getReasonPhrase());
                }

                @Override
                public void onError(Session session, Throwable thr) {
                    super.onError(session, thr);
                    websocketLifeCycle.onError(thr);
                }

                @Override
                public void onOpen(Session session, EndpointConfig config) {
                    session.addMessageHandler(new MessageHandler.Whole<String>(){
                        @Override
                        public void onMessage(String message) {
                            handler.handleMessage(message);
                        }
                    });
                }
            };

            newSession = client.connectToServer(endPoint, ClientEndpointConfig.Builder.create().build(), serverURI);
        }catch(Exception e){
            //connect to server failed
            return null;
        }
        return new TyrusSession(newSession);
    }
}
