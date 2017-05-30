package com.suiyu.comet.model.push.impl;

import com.suiyu.comet.model.push.MessageReceiverStrategy;

import java.util.concurrent.TimeUnit;

/**
 * Created by yinbing on 2017/5/25.
 */
abstract public class AbstractMessageReceiverStrategy<T> implements MessageReceiverStrategy<T>{
    private long timeout;
    private TimeUnit timeoutUnit;
    protected T message;
    public AbstractMessageReceiverStrategy(T message, long timeout, TimeUnit timeoutUnit) {
        this.timeout = timeout;
        this.timeoutUnit = timeoutUnit;
        this.message = message;
    }

    @Override
    public T getMessage() {
        return this.message;
    }
    @Override
    public long getTimeout() {
        return this.timeout;
    }

    @Override
    public TimeUnit getTimeoutUnit() {
        return this.timeoutUnit;
    }
}
