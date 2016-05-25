package com.suiyu.comet.model.push.impl;

import com.rabbitmq.client.Connection;
import com.suiyu.comet.model.push.PushMessage;
import com.suiyu.comet.model.push.PushMessageFactory;
import com.suiyu.comet.model.push.impl.mq.TopicConsumer;
import com.suiyu.comet.model.push.impl.mq.TopicProducer;

import java.io.IOException;

/**
 * Created by yinbing on 5/24/2016.
 */
public class MessageQueueChannel extends AbstractPushClientChannel {
    private TopicProducer topicProducer;
    private TopicConsumer topicConsumer;
    private Connection connection;
    private PushMessageFactory pushMessageFactory;


    public MessageQueueChannel(Connection connection,
                               TopicProducer topicProducer,
                               TopicConsumer topicConsumer,
                               String clientId,
                               PushMessageFactory pushMessageFactory) {
        super(clientId);
        this.connection = connection;
        this.topicConsumer = topicConsumer;
        this.topicProducer = topicProducer;
        this.pushMessageFactory = pushMessageFactory;
        topicConsumer.start();
    }

    @Override
    public void send(String message) {
        try {
            topicProducer.publish(message);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void send(PushMessage pushMessage) {
        send(pushMessageFactory.convertToJson(pushMessage).toString());
    }

    @Override
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (topicConsumer != null) {
            topicConsumer.interrupt();
        }

        connection = null;
        topicConsumer = null;
        topicProducer = null;
    }
}
