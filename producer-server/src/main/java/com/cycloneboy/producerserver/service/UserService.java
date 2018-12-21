package com.cycloneboy.producerserver.service;

import com.cycloneboy.producerserver.dao.UserRepository;
import com.cycloneboy.producerserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @date:2018-12-21 00:41
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        User user = userRepository.findById(id).orElse(new User());
        return user;
    }

}
