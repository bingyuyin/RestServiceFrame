package com.suiyu.web.service;

import com.suiyu.web.model.push.PushMessage;

/**
 * Created by BingyuYin on 2016/2/22.
 */
public interface PushMessageService {
    void onIncomingMessage(PushMessage pushMessage);
    void onOutgoingMessage(PushMessage pushMessage);
}
