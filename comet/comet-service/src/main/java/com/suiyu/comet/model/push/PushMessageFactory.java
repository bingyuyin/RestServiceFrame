package com.suiyu.comet.model.push;

import com.google.gson.JsonObject;

/**
 * Created by BingyuYin on 2016/2/22.
 */
public interface PushMessageFactory {
    JsonObject convertToJson(PushMessage pushMessage);
    PushMessage createFromJsonString(String json);
    PushMessage createFromJson(JsonObject jsonObject);
}
