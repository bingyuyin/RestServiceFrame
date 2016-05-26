package com.suiyu.comet.service.impl;

import com.suiyu.comet.service.ExecutorService;
import com.suiyu.comet.utils.CometThreadFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yinbing on 5/26/2016.
 */
@Service
public class ExecutorServiceImpl implements ExecutorService {

    private java.util.concurrent.ExecutorService executorService = new ThreadPoolExecutor(0, 1000, 5L, TimeUnit.MINUTES, new SynchronousQueue<Runnable>(), new CometThreadFactory());

    @Override
    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
