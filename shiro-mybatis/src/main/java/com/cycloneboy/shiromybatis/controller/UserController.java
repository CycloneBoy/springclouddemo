package com.cycloneboy.shiromybatis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cycloneboy.shiromybatis.common.dto.ExecuteDTO;
import com.cycloneboy.shiromybatis.common.dto.PageResultDTO;
import com.cycloneboy.shiromybatis.entity.User;
import com.cycloneboy.shiromybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Book;
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
@Api("User相关api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户添加;
     * @return
     */
    @ApiOperation(value = "添加User", notes = "添加User对象实体", httpMethod = "POST")
    @PostMapping("/{id}")
    @RequiresPermissions("userInfo:add")//权限管理;
    public ExecuteDTO userCreate(@RequestBody User entity){
        Boolean result=userService.save(entity);
        log.info("保存User："+result + " " + entity.getId() );

        return new ExecuteDTO(result,result ? "保存成功":"保存失败",entity.getId());

    }

    /**
     * 用户删除;
     * @return
     */
    @ApiOperation(value = "删除User", notes = "删除User对象实体", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    @RequiresPermissions("userInfo:del")//权限管理;
    public ExecuteDTO userRemove(@PathVariable int id){
        Boolean result=userService.removeById(id);
        log.info("删除User：Id = "+id+" result: "+result);
        return new ExecuteDTO(result,result ? "删除成功":"删除失败",id);
    }

    /**
     * 修改用户
     */
    @ApiOperation(value = "修改User", notes = "修改User对象实体", httpMethod = "PUT")
    @PutMapping(value = "/{id}")
    public ExecuteDTO update(@PathVariable Integer id,@RequestBody User entity){
        log.info("修改用户："+entity);
        User exitEntity=userService.getById(id);
        if(exitEntity==null){
            return new ExecuteDTO(false,"修改失败:实体不存在",entity.getId());
        }

        entity.setId(id);
        Boolean result=userService.updateById(entity);
        return new ExecuteDTO(result,result?"修改成功":"修改失败",entity);
    }

    /**
     * 根据ID用户查询
     */
    @ApiOperation(value = "获取指定User", notes = "获取指定User", httpMethod = "GET")
    @GetMapping(value = "/{id}")
    public ExecuteDTO getUser(@PathVariable Integer id){
        log.info("获取指定User："+id);
        User entity =  userService.getById(id);
        return new ExecuteDTO(entity != null,entity != null ? "查询成功":"查询失败",entity);
    }


    /**
     * 用户列表查询.
     * @return
     */
    @ApiOperation(value = "获取指定User列表", notes = "获取指定User列表", httpMethod = "GET")
    @GetMapping(value = "/list")
//    @RequiresPermissions("userInfo:view")//权限管理;
    public PageResultDTO userList(@RequestParam(name = "page") int page,@RequestParam(name = "limit") int limit  ){
        log.info("begin userInfo");

        Page<User> page1 =  new Page(page, limit);
        IPage userPage = userService.page(page1, null);

        PageResultDTO pageResultDTO = new PageResultDTO(0,"用户列表",userPage.getSize(),userPage.getRecords());

        log.info("end userInfo");
        return pageResultDTO;
    }
}

