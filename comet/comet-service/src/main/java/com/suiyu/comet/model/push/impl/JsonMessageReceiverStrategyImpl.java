package com.suiyu.comet.model.push.impl;

import com.google.gson.JsonObject;
import com.suiyu.comet.common.JsonUtils;
import com.suiyu.comet.model.push.MessageReceiverAction;

import java.util.concurrent.TimeUnit;

/**
 * Created by yinbing on 2017/5/25.
 */
public class JsonMessageReceiverStrategyImpl extends AbstractMessageReceiverStrategy<JsonObject> {
    public JsonMessageReceiverStrategyImpl(JsonObject sendMessage, long timeout, TimeUnit timeoutUnit) {
        super(sendMessage, timeout, timeoutUnit);
    }

    @Override
    public MessageReceiverAction decide(JsonObject message) {
        return MessageReceiverAction.Reject;
    }
}
