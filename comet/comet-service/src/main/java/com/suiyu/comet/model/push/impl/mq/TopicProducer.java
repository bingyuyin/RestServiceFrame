package com.suiyu.comet.model.push.impl.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by yinbing on 5/24/2016.
 */
public class TopicProducer {
    private Channel channel;
    private String exchangeName;
    private String routingKey;

    public TopicProducer(Channel channel, String exchangeName, String routingKey) throws IOException{
        this.channel = channel;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        channel.exchangeDeclare(exchangeName, "topic");
    }

    public void publish(String message) throws IOException{

        String correlationId = UUID.randomUUID().toString();
        AMQP.BasicProperties pros = new AMQP.BasicProperties.Builder()
                .correlationId(correlationId)
                .build();
        channel.basicPublish(exchangeName, routingKey, pros, message.getBytes("UTF-8"));
    }
}
