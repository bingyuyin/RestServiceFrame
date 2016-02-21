package com.suiyu.web.model.push.impl;

import com.suiyu.web.model.push.PushClient;
import com.suiyu.web.model.push.PushClientFactory;
import com.suiyu.web.model.push.PushMessage;
import com.suiyu.web.model.push.PushServer;
import org.atmosphere.cpr.AtmosphereResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BingyuYin on 2016/2/21.
 */
@Service
public class PushServerImpl implements PushServer {
    private Map<String, PushClient> clients = new ConcurrentHashMap<String, PushClient>();

    @Autowired
    PushClientFactory pushClientFactory;

    @Override
    public void subscribe(AtmosphereResource atmosphereResource, String pushClientId) {
        if(null == atmosphereResource){
            // report error
            return ;
        }
        if(pushClientId == null || pushClientId.isEmpty()){
            // report error
            return ;
        }
        if(exist(pushClientId)){
            return ;
        }
        PushClient pushClient = pushClientFactory.createPushClient(atmosphereResource, pushClientId);
        clients.put(pushClientId, pushClient);
    }

    @Override
    public void notifyMessage(PushMessage pushMessage) {

    }

    private boolean exist(String clientId){
        return clients.containsKey(clientId);
    }
}
