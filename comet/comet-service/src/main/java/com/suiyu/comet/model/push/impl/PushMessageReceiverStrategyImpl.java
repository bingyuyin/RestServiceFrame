package com.suiyu.comet.model.push.impl;

import com.suiyu.comet.model.push.MessageReceiverAction;
import com.suiyu.comet.model.push.MessageReceiverStrategy;
import com.suiyu.comet.model.push.PushMessage;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by yinbing on 2017/5/25.
 */
public class PushMessageReceiverStrategyImpl extends AbstractMessageReceiverStrategy<PushMessage> {
    public PushMessageReceiverStrategyImpl(PushMessage message, long timeout, TimeUnit timeoutUnit) {
        super(message, timeout, timeoutUnit);
        if (message.getOriginalId() == null) {
            message.setOriginalId(UUID.randomUUID().toString());
        }
    }
    @Override
    public MessageReceiverAction decide(PushMessage message) {
        if (getMessage().getOriginalId().equals(message.getOriginalId())) {
            return MessageReceiverAction.Receive;
        }
        return MessageReceiverAction.Ignore;
    }
}
