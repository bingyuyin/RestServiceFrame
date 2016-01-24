package com.suiyu.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yinbing on 1/22/2016.
 */
@RequestMapping(value = "/")
public interface WebSocketController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    void subscribe(HttpServletRequest request) throws Exception;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    void broadcastMessage(HttpServletRequest request) throws Exception;

    @RequestMapping(value = "{clientId:\\d+}", method = RequestMethod.GET)
    @ResponseBody
    void subscribeClient(HttpServletRequest request);


    @RequestMapping(value = "{clientId:\\d+}", method = RequestMethod.POST)
    @ResponseBody
    void broadcastClientMessage(HttpServletRequest request);
}
