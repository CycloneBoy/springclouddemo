package com.cycloneboy.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-09-16 20:24
 **/
@RestController
public class HelloController {

    @Value("${from}")
    private String hello;

    @RequestMapping("/hello")
    public String from() {
        return "hello : " + this.hello;
    }

}
