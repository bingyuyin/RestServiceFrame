package com.suiyu.web.service.impl;

import com.suiyu.web.service.WebSocketService;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEventListenerAdapter;
import org.atmosphere.cpr.FrameworkConfig;
import org.atmosphere.websocket.WebSocketEventListener;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yinbing on 1/22/2016.
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {

    @Override
    public void subscribe(HttpServletRequest request) {
        AtmosphereResource atmosphereResource = (AtmosphereResource)request.getAttribute(FrameworkConfig.ATMOSPHERE_RESOURCE);
    }

    @Override
    public void broadcastMessage(HttpServletRequest request) {

    }
}
