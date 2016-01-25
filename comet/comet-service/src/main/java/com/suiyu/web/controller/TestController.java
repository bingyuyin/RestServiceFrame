package com.suiyu.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by BingyuYin on 2016/1/25.
 */
@Controller
@RequestMapping("/test/")
public class TestController {
    @RequestMapping(value = "get", method = RequestMethod.GET)
    ResponseEntity<Object> getTest(){
        return new ResponseEntity<Object>("This is a response of get request", HttpStatus.OK);
    }
}
