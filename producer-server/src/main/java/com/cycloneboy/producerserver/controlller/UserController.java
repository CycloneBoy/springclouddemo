package com.cycloneboy.producerserver.controlller;

import com.cycloneboy.producerserver.domain.BaseResponse;
import com.cycloneboy.producerserver.entity.User;
import com.cycloneboy.producerserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * create by CycloneBoy on 2018-12-21 00:48
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable(name = "id") String id){
        log.info("get user by id {}",id);
        return userService.findById(Long.valueOf(id));
    }

    @GetMapping("/test")
    public BaseResponse test(){
        log.info("测试");
        return new BaseResponse("1","测试");
    }

    @GetMapping("/test2")
    public BaseResponse test2(){
        log.info("测试2");
        return new BaseResponse("2","测试2");
    }
}
