package com.suiyu.comet.model.push.impl.wsclient;

/**
 * Created by yinbing on 2/23/2016.
 */
public interface WebsocketLifeCycle {
    void onError(Throwable ex);
    void onClose(int statusCode, String reason);
}
