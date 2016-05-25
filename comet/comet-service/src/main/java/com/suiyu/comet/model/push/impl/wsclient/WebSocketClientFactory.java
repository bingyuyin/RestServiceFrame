package com.suiyu.comet.model.push.impl.wsclient;

import org.springframework.util.StringUtils;

/**
 * Created by yinbing on 2/23/2016.
 */
public class WebSocketClientFactory {
    public WebsocketClientInterface createWebSocketClient(String proxyURL, WebsocketLifeCycle websocketLifeCycle, boolean useJetty){
        if(!useJetty || StringUtils.hasText(proxyURL)){
            return new TyrusWebsocketClient(websocketLifeCycle);
        }else {
            return new JettyWebsocketClient();
        }
    }
}
