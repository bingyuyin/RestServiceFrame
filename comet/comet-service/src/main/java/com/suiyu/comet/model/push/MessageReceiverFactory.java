package com.suiyu.comet.model.push;

import com.google.gson.JsonObject;
import com.suiyu.comet.model.push.impl.JsonMessageReceiverStrategyImpl;
import com.suiyu.comet.model.push.impl.PushMessageReceiverStrategyImpl;

/**
 * Created by yinbing on 2017/5/25.
 */
public interface MessageReceiverFactory {
    PushMessage sendAndReceive(PushClient pushClient, PushMessage sendMessage);
    PushMessage sendAndReceive(PushClient pushClient, PushMessageReceiverStrategyImpl messageReceiverStrategy);
    JsonObject sendAndReceive(PushClient pushClient, JsonObject sendMessage);
    JsonObject sendAndReceive(PushClient pushCLient, JsonMessageReceiverStrategyImpl messageReceiverStrategy);
    MessageReceiver<PushMessage> create(PushClient pushClient, PushMessage sendMessage);
    MessageReceiver<JsonObject> create(PushClient pushClient, JsonObject sendMessage);

    void receive(PushMessage pushMessage);
    void receive(JsonObject jsonObject);
}
