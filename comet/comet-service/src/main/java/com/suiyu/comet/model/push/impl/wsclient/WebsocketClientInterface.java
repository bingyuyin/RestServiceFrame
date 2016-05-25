package com.suiyu.comet.model.push.impl.wsclient;

import java.net.URI;

/**
 * Created by yinbing on 2/23/2016.
 */
public interface WebsocketClientInterface {
    SessionInterface connectSession(String proxyURL, URI serverURI, WebsocketMessageHandler messageHandler);
}
