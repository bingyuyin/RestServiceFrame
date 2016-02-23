package com.suiyu.web.model.push.impl;

import com.google.gson.JsonObject;
import com.suiyu.web.model.push.PushMessage;
import com.suiyu.web.model.push.PushMessageFactory;
import org.springframework.stereotype.Component;

/**
 * Created by BingyuYin on 2016/2/22.
 */
@Component
public class PushMessageFactoryImpl implements PushMessageFactory {
    @Override
    public JsonObject convertToJson(PushMessage pushMessage) {
        return null;
    }
}
