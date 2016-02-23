package com.suiyu.web.model.push;

/**
 * Created by BingyuYin on 2016/2/21.
 */
public interface PushClientChannel {
    void send(String message);
    void send(PushMessage pushMessage);
    void disconnect();
}
