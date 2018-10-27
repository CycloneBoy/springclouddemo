package com.cycloneboy.shiromybatis.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cycloneboy.shiromybatis.entity.User;
import com.cycloneboy.shiromybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author cycloneboy
 * @since 2018-10-24
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/show")
    public JSONObject testEnum() {
        List<User> users = userService.list(null);
        JSONObject result = new JSONObject();
        result.put("users", users);

        return result;
    }

}

