package com.suiyu.comet.model.push;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by yinbing on 2/25/2016.
 */
public class PushMessageDeserializer implements JsonDeserializer<PushMessage>{
    @Override
    public PushMessage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if(json == null || !json.isJsonObject()){
            return null;
        }
        JsonObject jsonObject = json.getAsJsonObject();

        if(jsonObject == null) {
            return null;
        }

        String targetId = null;
        String sourceId = null;
        Object body = null;
        PushMessageType type = null;
        boolean isValid = false;
        if(jsonObject.has("targetId") && !jsonObject.get("targetId").isJsonNull()){
            targetId = jsonObject.get("targetId").getAsString();
            isValid = true;
        }
        if(jsonObject.has("sourceId") && !jsonObject.get("sourceId").isJsonNull()){
            sourceId = jsonObject.get("sourceId").getAsString();
            isValid = true;
        }
        if(jsonObject.has("body") && !jsonObject.get("body").isJsonNull()){
            body = jsonObject.get("body");
            isValid = true;
        }
        if(jsonObject.has("pushMessageType") && !jsonObject.get("pushMessageType").isJsonNull()){
            type = PushMessageType.valueOf(jsonObject.get("pushMessageType").getAsString());
            isValid = true;
        }

        if(!isValid){
            return null;
        }
        return  new PushMessage(targetId, sourceId, body, type);
    }
}
