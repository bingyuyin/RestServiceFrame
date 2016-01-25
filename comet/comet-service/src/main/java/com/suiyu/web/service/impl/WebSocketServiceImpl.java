package com.suiyu.web.service.impl;

import com.suiyu.web.common.JsonUtils;
import com.suiyu.web.service.WebSocketService;
import org.atmosphere.cpr.*;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by yinbing on 1/22/2016.
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {
//    @Inject
//    private BroadcasterFactory broadcasterFactory;

    @PostConstruct
    public void init() {
//        broadcasterFactory.get("global");
    }

    @Override
    public void subscribe(HttpServletRequest request) {
        AtmosphereResource atmosphereResource = (AtmosphereResource)request.getAttribute(FrameworkConfig.ATMOSPHERE_RESOURCE);
     //   broadcasterFactory.lookup("global").addAtmosphereResource(atmosphereResource);
    }

    @Override
    public void broadcastMessage(HttpServletRequest request) {
        AtmosphereResource atmosphereResource = (AtmosphereResource)request.getAttribute(FrameworkConfig.ATMOSPHERE_RESOURCE);
        AtmosphereRequest req = atmosphereResource.getRequest();
        try {
            String message = JsonUtils.readJson(req.getReader());
    //        broadcasterFactory.lookup("global").broadcast(message);
        } catch (IOException ioe){
            // exception
        }
    }
}
