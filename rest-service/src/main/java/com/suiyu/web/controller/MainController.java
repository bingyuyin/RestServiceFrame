package com.suiyu.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yinbing on 1/8/2016.
 */
@RequestMapping("/")
public interface MainController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Object> testForGet();
}
