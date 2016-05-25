package com.suiyu.comet.model.push;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by yinbing on 2/25/2016.
 */
public class PushMessageSerializer implements JsonSerializer<PushMessage> {

    @Override
    public JsonElement serialize(PushMessage src, Type typeOfSrc, JsonSerializationContext context) {
        if(src == null){
            return null;
        }

        JsonObject result = new JsonObject();
        result.addProperty("targetId", src.getTargetId());
        result.addProperty("sourceId", src.getSourceId());
        Object body = src.getBody();
        if(body instanceof  JsonElement){
            result.add("body", (JsonElement)body);
        }else{
            try {
                JsonParser parser = new JsonParser();
                result.add("body", parser.parse(body.toString()));
            }catch(Exception e){
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("body", body.toString());
                result.add("body", jsonObject);
            }
        }

        if(src.getType() != null){
            result.addProperty("pushMessageType", src.getType().name());
        }
        return result;
    }
}
