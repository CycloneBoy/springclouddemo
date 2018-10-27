package com.cycloneboy.shiroadmin.web;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cycloneboy.shiroadmin.entity.dto.ExecuteDTO;
import com.cycloneboy.shiroadmin.entity.dto.PageQueryDTO;
import com.cycloneboy.shiroadmin.entity.dto.PageResultDTO;

import com.cycloneboy.shiroadmin.entity.User;
import com.cycloneboy.shiroadmin.service.UserService;


import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器 RESTful API
 * </p>
 *
 * @author cycloneboy
 * @since 2018-10-20
 */
@RestController
@RequestMapping("/shiroadmin/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 获取User 指定ID的实体
     */
    @ApiOperation(value = "获取指定User", notes = "获取指定User", httpMethod = "GET")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public User get(@PathVariable String id){
        logger.info("获取指定User："+id);
        return userService.getById(id);
    }


    /**
     * 保存User
     */
    @ApiOperation(value = "保存User", notes = "保存User", httpMethod = "POST")
    @PostMapping("save")
    public ExecuteDTO save(@RequestBody User entity){
        Boolean result=userService.save(entity);
        logger.info("保存User："+result);
        if(result==false){
            return new ExecuteDTO(false,"保存失败",entity.getId());
        }
        return new ExecuteDTO(true,"保存成功",entity.getId());
    }

    /**
     * 修改User
     */
    @ApiOperation(value = "修改User", notes = "修改User对象实体", httpMethod = "POST")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ExecuteDTO update(@RequestBody User entity){
        logger.info("修改User："+entity);
        User exitUser=userService.getById(entity.getId());
        if(exitUser==null){
            return new ExecuteDTO(false,"修改失败",entity.getId());
        }
        Boolean result=userService.updateById(entity);
        return new ExecuteDTO(true,"修改成功",entity.getId());
    }



    /**
     * 删除User
     */
    @ApiOperation(value = "删除User", notes = "删除用", httpMethod = "GET")
    @GetMapping("remove/{id}")
    public ExecuteDTO remove(@PathVariable Integer id){
        Boolean result=userService.removeById(id);
        logger.info("删除User：Id = "+id+" result: "+result);
        return new ExecuteDTO(true,"删除成功",id);
    }


    /**
     * 根据名称查询User
     */
    @ApiOperation(value = "根据名称查询User", notes = "查询用", httpMethod = "GET")
    @GetMapping("username/{username}")
    public ExecuteDTO findUsreByUsername(@PathVariable String username){
        logger.info("根据名称查询User：" + username);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);

        User entity = userService.getOne(queryWrapper,false);
        if(entity==null){
            return new ExecuteDTO(false,"没有用户名为" + username +"的用户",entity.getId());
        }

        return new ExecuteDTO(true,"用户名为"+ username +"的用户查询成功",entity);
    }
}

