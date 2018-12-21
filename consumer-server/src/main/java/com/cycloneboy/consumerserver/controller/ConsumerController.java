package com.cycloneboy.consumerserver.controller;

import com.cycloneboy.consumerserver.entity.User;
import com.cycloneboy.consumerserver.service.HelloRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;

    @Autowired
    private HelloRemote helloRemote;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name){
        return helloRemote.index(name);
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        logger.info("get user from remote: {}",id);
        return restTemplate.getForObject("http://localhost:9003/" + id ,User.class);
    }
}
