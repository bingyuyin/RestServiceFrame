package com.suiyu.comet.controller;

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
    // this controller is only used to test the framework work or not
    @RequestMapping(value = "get", method = RequestMethod.GET)
    ResponseEntity<Object> getTest(){
        return new ResponseEntity<Object>("This is a response of GET request", HttpStatus.OK);
    }
}
