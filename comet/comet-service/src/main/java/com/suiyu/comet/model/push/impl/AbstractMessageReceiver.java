package com.suiyu.comet.model.push.impl;

import com.suiyu.comet.common.util.ThrowUtil;
import com.suiyu.comet.model.push.MessageReceiver;
import com.suiyu.comet.model.push.MessageReceiverAction;
import com.suiyu.comet.model.push.MessageReceiverStrategy;
import com.suiyu.comet.model.push.PushClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by yinbing on 2017/5/24.
 */
abstract class AbstractMessageReceiver<T> implements MessageReceiver<T> {
    private Logger logger = LoggerFactory.getLogger(AbstractMessageReceiver.class);
    protected final  PushClient pushClient;
    protected final  MessageReceiverStrategy<T> messageReceiverStrategy;
    private CountDownLatch countDownLatch;
    private T receiveMessage;
    public AbstractMessageReceiver(PushClient pushClient, MessageReceiverStrategy<T> messageReceiverStrategy) {
        ThrowUtil.verifyArgumentNotNull(pushClient);
        this.pushClient = pushClient;
        this.messageReceiverStrategy = messageReceiverStrategy;
    }


    @Override
    public T sendAndReceive() {
        countDownLatch = new CountDownLatch(1);
        send(messageReceiverStrategy.getMessage());
        try {
            countDownLatch.await(messageReceiverStrategy.getTimeout(), messageReceiverStrategy.getTimeoutUnit());
            return this.receiveMessage;
        } catch (InterruptedException e) {
            logger.error("message receive exception: {}", e.getMessage());
        } finally {
            finish();
        }
        return null;
    }

    @Override
    public boolean receive(T message) {
        if (countDownLatch != null) {
            if (messageReceiverStrategy.decide(message).equals(MessageReceiverAction.Receive)) {
                countDownLatch.countDown();
                this.receiveMessage = message;
                return true;
            }
        }
        this.receiveMessage = null;
        return false;
    }
}
