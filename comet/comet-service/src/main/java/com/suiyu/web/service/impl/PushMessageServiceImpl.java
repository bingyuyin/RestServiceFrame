package com.suiyu.web.service.impl;

import com.suiyu.web.model.push.PushMessage;
import com.suiyu.web.service.PushMessageService;
import org.springframework.stereotype.Component;

/**
 * Created by BingyuYin on 2016/2/22.
 */
@Component
public class PushMessageServiceImpl implements PushMessageService {
    @Override
    public void onOutgoingMessage(PushMessage pushMessage) {

    }

    @Override
    public void onIncomingMessage(PushMessage pushMessage) {

    }
}
