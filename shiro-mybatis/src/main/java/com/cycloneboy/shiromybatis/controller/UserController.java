package com.cycloneboy.shiromybatis.controller;


import com.cycloneboy.shiromybatis.common.dto.ExecuteDTO;
import com.cycloneboy.shiromybatis.entity.User;
import com.cycloneboy.shiromybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author cycloneboy
 * @since 2018-10-28
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户查询.
     * @return
     */
    @GetMapping(value = "/userList")
//    @RequiresPermissions("userInfo:view")//权限管理;
    public ExecuteDTO userInfo(){
        log.info("begin userInfo");
        List<User>  userList = userService.list(null);
        if(userList == null){
            return new ExecuteDTO(0,"用户列表为空",0,null);
        }

        ExecuteDTO executeDTO = new ExecuteDTO();
        executeDTO.setCode(0);
        executeDTO.setMsg("用户列表");
        executeDTO.setCount(userList.size());
        executeDTO.setData(userList);
        log.info("end userInfo");
        return executeDTO;
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(){
        return "userAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){
        return "userDel";
    }
}

