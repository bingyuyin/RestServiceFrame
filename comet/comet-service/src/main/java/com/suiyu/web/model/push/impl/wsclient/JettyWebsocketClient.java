package com.suiyu.web.model.push.impl.wsclient;

import java.net.URI;

/**
 * Created by yinbing on 2/23/2016.
 */
public class JettyWebsocketClient implements WebsocketClientInterface {
    @Override
    public SessionInterface connectSession(String proxyURL, URI serverURI, WebsocketMessageHandler messageHandler) {
        return null;
    }
}
