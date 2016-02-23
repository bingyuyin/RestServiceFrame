package com.suiyu.web.model.push.impl.wsclient;

/**
 * Created by yinbing on 2/23/2016.
 */
public interface WebsocketMessageHandler {
    void handleMessage(String message);
}
