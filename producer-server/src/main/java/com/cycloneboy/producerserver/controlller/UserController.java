package com.cycloneboy.producerserver.controlller;

import com.cycloneboy.producerserver.entity.User;
import com.cycloneboy.producerserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * create by CycloneBoy on 2018-12-21 00:48
 */
@RequestMapping(name = "/user")
@RestController()
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable(name = "id") Long id){
        return userService.findById(id);
    }

}
