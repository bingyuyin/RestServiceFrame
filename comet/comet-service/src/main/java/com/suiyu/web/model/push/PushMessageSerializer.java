package com.suiyu.web.model.push;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by yinbing on 2/25/2016.
 */
public class PushMessageSerializer implements JsonSerializer<PushMessage> {
    @Override
    public JsonElement serialize(PushMessage src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
