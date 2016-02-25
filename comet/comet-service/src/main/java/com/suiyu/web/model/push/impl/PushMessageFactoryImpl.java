package com.suiyu.web.model.push.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.suiyu.web.common.JsonUtils;
import com.suiyu.web.model.push.PushMessage;
import com.suiyu.web.model.push.PushMessageDeserializer;
import com.suiyu.web.model.push.PushMessageFactory;
import com.suiyu.web.model.push.PushMessageSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by BingyuYin on 2016/2/22.
 */
@Component
public class PushMessageFactoryImpl implements PushMessageFactory {
    private Gson gson;

    @PostConstruct
    public void init(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(PushMessage.class, new PushMessageDeserializer());
        builder.registerTypeAdapter(PushMessage.class, new PushMessageSerializer());
        gson = builder.create();
    }

    @Override
    public JsonObject convertToJson(PushMessage pushMessage) {
        return gson.toJsonTree(pushMessage, PushMessage.class).getAsJsonObject();
    }

    @Override
    public PushMessage createFromJsonString(String json) {
        JsonObject jsonObject = JsonUtils.convertStringToJsonObject(json);
        return createFromJson(jsonObject);
    }

    @Override
    public PushMessage createFromJson(JsonObject jsonObject){
        return gson.fromJson(jsonObject, PushMessage.class);
    }


}
