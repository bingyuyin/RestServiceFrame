package com.suiyu.comet.model.push;

/**
 * Created by yinbing on 2017/5/24.
 */
public interface MessageReceiver<T> {
    void send(T sendMessage);
    T sendAndReceive();
    boolean receive(T message);
    void finish();
}
