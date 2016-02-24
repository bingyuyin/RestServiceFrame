package com.suiyu.web.model.push.impl;

import com.suiyu.web.model.push.MessageHandler;
import com.suiyu.web.model.push.PushClientChannel;
import com.suiyu.web.model.push.PushMessageFactory;
import com.suiyu.web.service.PushMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * Created by yinbing on 2/24/2016.
 */
@Component
public class ServerPushClientChannelFactory extends AbstractPushClientChannelFactory {

    @Autowired
    private MessageHandler handler;

    private String proxyHost = "";

    private boolean useJetty = false;

    @Override
    public PushClientChannel create(URI uri, String clientId) {
        AbstractWebSocketChannel channel = createWebSocketChannel(uri, clientId);
        if(channel != null){
            if(channel.connect()){
                return channel;
            }else{
                channel.disconnect();
            }
        }
        return null;
    }

    private AbstractWebSocketChannel createWebSocketChannel(URI uri, String clientId){
        if(uri == null){
            return null;
        }
        return new ServerWebSocketChannel(handler, pushMessageService, pushMessageFactory, uri, clientId, pushServer, getProxyHost(), useJetty);
    }

    public String getProxyHost(){
        return this.proxyHost;
    }


}
