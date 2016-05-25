package com.suiyu.comet.model.push.impl.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.suiyu.comet.model.push.MessageHandler;

import java.io.IOException;

/**
 * Created by yinbing on 5/24/2016.
 */
public class TopicConsumer extends Thread {

    private Channel channel;
    private String queueName;
    private MessageHandler messageHandler;

    public TopicConsumer(Channel channel,
                         String exchangeName,
                         String routingKey,
                         MessageHandler messageHandler) throws IOException {
        this.channel = channel;
        channel.exchangeDeclare(exchangeName, "topic");
        this.queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchangeName, routingKey);
        this.messageHandler = messageHandler;
        setDaemon(true);
    }

    @Override
    public void run() {
        QueueingConsumer consumer = new QueueingConsumer(channel);
        try {
            channel.basicConsume(queueName, true, consumer);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return ;
        }

        while (!isInterrupted()) {
            try {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody(), "UTF-8");
                String routingKey = delivery.getEnvelope().getRoutingKey();
                String clientId = routingKey.substring(0, routingKey.lastIndexOf("."));
                doHandle(message, clientId);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doHandle(String message, String clientId) {
        messageHandler.handleMessage(message, clientId);
    }
}
