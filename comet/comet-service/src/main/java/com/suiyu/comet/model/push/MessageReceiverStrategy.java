package com.suiyu.comet.model.push;

import java.util.concurrent.TimeUnit;

/**
 * Created by yinbing on 2017/5/24.
 */
public interface MessageReceiverStrategy<T> {
    MessageReceiverAction decide(T message);
    T getMessage();
    long getTimeout();
    TimeUnit getTimeoutUnit();
}
