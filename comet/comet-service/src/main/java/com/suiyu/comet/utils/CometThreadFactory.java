package com.suiyu.comet.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yinbing on 5/26/2016.
 */
public class CometThreadFactory implements ThreadFactory {

    private static AtomicInteger threadCount = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        Thread res = new Thread(r, "Comet Service Thread " + threadCount.getAndIncrement());
        res.setDaemon(false);
        return res;
    }
}
