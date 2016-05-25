package com.suiyu.comet.model.push.impl;

import com.suiyu.comet.model.push.PushClient;
import com.suiyu.comet.model.push.PushClientChannel;
import com.suiyu.comet.model.push.PushMessage;

/**
 * Created by BingyuYin on 2016/2/21.
 */
public class PushClientImpl implements PushClient {
    private PushClientChannel channel;
    private String clientId;

    public PushClientImpl(PushClientChannel channel, String clientId){
        this.channel = channel;
        this.clientId = clientId;
    }

    @Override
    public void send(PushMessage pushMessage) {
        channel.send(pushMessage);
    }

    @Override
    public void send(String message) {
        channel.send(message);
    }
}
