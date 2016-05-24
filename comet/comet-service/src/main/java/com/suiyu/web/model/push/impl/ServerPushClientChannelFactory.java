package com.suiyu.web.model.push.impl;

import com.rabbitmq.client.Connection;
import com.suiyu.web.model.push.MessageHandler;
import com.suiyu.web.model.push.PushClientChannel;
import com.suiyu.web.model.push.PushMessageFactory;
import com.suiyu.web.model.push.impl.mq.TopicConsumer;
import com.suiyu.web.model.push.impl.mq.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

/**
 * Created by yinbing on 2/24/2016.
 */
@Component
public class ServerPushClientChannelFactory extends AbstractPushClientChannelFactory {

    @Autowired
    private MessageHandler handler;

    @Autowired
    private PushMessageFactory pushMessageFactory;


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


    @Override
    public PushClientChannel create(String host, String clientId) {
        try {
            Connection connection = newMessageQueueConnection(host);
            TopicConsumer topicConsumer = new TopicConsumer(connection.createChannel(),
                    topicExchangeName,
                    clientId +".incoming",
                    handler);
            TopicProducer topicProducer = new TopicProducer(connection.createChannel(),
                    topicExchangeName,
                    clientId +".outgoing");

            return new MessageQueueChannel(connection,
                    topicProducer,
                    topicConsumer,
                    clientId,
                    pushMessageFactory);
        } catch (IOException ioe) {
            ioe.printStackTrace();
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
