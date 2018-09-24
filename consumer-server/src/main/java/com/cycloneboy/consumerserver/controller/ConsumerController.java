package com.cycloneboy.consumerserver.controller;

import com.cycloneboy.consumerserver.service.HelloRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-09-16 15:43
 **/
@RestController
public class ConsumerController {

    private final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private HelloRemote helloRemote;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name){
        return helloRemote.index(name);
    }
}
