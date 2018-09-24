package com.cycloneboy.producerserver.controlller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-09-16 15:17
 **/
@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String index(@RequestParam String name){
        return "Hello , " + name + " , this is producer 2 send first message";
    }
}
