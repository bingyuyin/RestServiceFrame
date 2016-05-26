package com.suiyu.comet.service.impl;

import com.suiyu.comet.model.push.PushMessage;
import com.suiyu.comet.service.PushMessageService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by BingyuYin on 2016/2/22.
 */
@Service
public class PushMessageServiceImpl implements PushMessageService {
    @Override
    public void onOutgoingMessage(PushMessage pushMessage) {

    }

    @Override
    public void onIncomingMessage(PushMessage pushMessage) {

    }
}
