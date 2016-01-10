package com.suiyu.web.controller.impl;

import com.suiyu.web.controller.MainController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yinbing on 1/8/2016.
 */
@Controller
@RequestMapping("/")
public class MainControllerImpl implements MainController {
    @Override
    @RequestMapping
    @ResponseBody
    public ResponseEntity<Object> testForGet(){
        return new ResponseEntity<Object>("This is a test for GET Method", HttpStatus.OK);
    }
}
