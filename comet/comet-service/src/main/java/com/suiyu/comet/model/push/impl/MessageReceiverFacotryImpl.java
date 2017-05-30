package com.suiyu.comet.model.push.impl;

import com.google.gson.JsonObject;
import com.suiyu.comet.model.push.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by yinbing on 2017/5/25.
 */
@Component
public class MessageReceiverFacotryImpl implements MessageReceiverFactory {
    private final List<PushMessageReceiverImpl> pushMessageReceivers = new CopyOnWriteArrayList<>();
    private final List<JsonMessageReceiverImpl> jsonMessageReceivers = new CopyOnWriteArrayList<>();

    @Override
    public MessageReceiver<PushMessage> create(PushClient pushClient, PushMessage sendMessage) {
        return new PushMessageReceiverImpl(pushClient, sendMessage);
    }

    @Override
    public MessageReceiver<JsonObject> create(PushClient pushClient, JsonObject sendMessage) {
        return new JsonMessageReceiverImpl(pushClient, sendMessage);
    }

    @Override
    public PushMessage sendAndReceive(PushClient pushClient, PushMessage sendMessage) {
        return create(pushClient, sendMessage).sendAndReceive();
    }

    @Override
    public PushMessage sendAndReceive(PushClient pushClient, PushMessageReceiverStrategyImpl messageReceiverStrategy) {
        return new PushMessageReceiverImpl(pushClient, messageReceiverStrategy).sendAndReceive();
    }
    @Override
    public JsonObject sendAndReceive(PushClient pushClient, JsonObject sendMessage) {
        return null;
    }

    @Override
    public JsonObject sendAndReceive(PushClient pushClient, JsonMessageReceiverStrategyImpl messageReceiverStrategy) {
        return new JsonMessageReceiverImpl(pushClient, messageReceiverStrategy).sendAndReceive();
    }

    @Override
    public void receive(PushMessage pushMessage) {
        for (PushMessageReceiverImpl var: pushMessageReceivers) {
            var.receive(pushMessage);
        }
    }

    @Override
    public void receive(JsonObject jsonObject) {
        for (JsonMessageReceiverImpl var: jsonMessageReceivers) {
            var.receive(jsonObject);
        }
    }

    private class PushMessageReceiverImpl extends AbstractMessageReceiver<PushMessage> {
        public PushMessageReceiverImpl(PushClient pushClient, PushMessage pushMessage) {
            this(pushClient, new PushMessageReceiverStrategyImpl(pushMessage, 300L, TimeUnit.SECONDS));
        }

        public PushMessageReceiverImpl(PushClient pushClient, MessageReceiverStrategy<PushMessage> messageReceiverStrategy) {
            super(pushClient, messageReceiverStrategy);
            pushMessageReceivers.add(this);
        }
        @Override
        public void send(PushMessage sendMessage) {
            pushClient.send(sendMessage);
        }

        @Override
        public void finish() {
            pushMessageReceivers.remove(this);
        }
    }

    private class JsonMessageReceiverImpl extends AbstractMessageReceiver<JsonObject> {
        public JsonMessageReceiverImpl(PushClient pushClient, JsonObject sendMessage) {
            this(pushClient, new JsonMessageReceiverStrategyImpl(sendMessage, 300L, TimeUnit.SECONDS));
        }

        public JsonMessageReceiverImpl(PushClient pushClient, MessageReceiverStrategy<JsonObject> messageReceiverStrategy) {
            super(pushClient, messageReceiverStrategy);
            jsonMessageReceivers.add(this);
        }
        @Override
        public void send(JsonObject sendMessage) {
            pushClient.send(sendMessage.toString());
        }

        @Override
        public void finish() {
            jsonMessageReceivers.remove(this);
        }
    }

}
