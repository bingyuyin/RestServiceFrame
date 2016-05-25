package com.suiyu.comet.service;

import com.suiyu.comet.model.push.PushMessage;

/**
 * Created by BingyuYin on 2016/2/22.
 */
public interface PushMessageService {
    void onIncomingMessage(PushMessage pushMessage);
    void onOutgoingMessage(PushMessage pushMessage);
}
