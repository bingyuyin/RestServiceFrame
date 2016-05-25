package com.suiyu.comet.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yinbing on 1/22/2016.
 */
public interface WebSocketService {
    void subscribe(HttpServletRequest httpServletRequest);
    void broadcastMessage(HttpServletRequest httpServletRequest);
}
