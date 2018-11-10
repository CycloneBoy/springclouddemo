package com.cycloneboy.shiromybatis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cycloneboy.shiromybatis.common.dto.ExecuteDTO;
import com.cycloneboy.shiromybatis.common.dto.Ids;
import com.cycloneboy.shiromybatis.common.dto.PageResultDTO;

import com.cycloneboy.shiromybatis.entity.UserRole;
import com.cycloneboy.shiromybatis.service.UserRoleService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户角色表 前端控制器 RESTful API
 * </p>
 *
 * @author cycloneboy
 * @since 2018-11-10
 */
@Slf4j
@RestController
@RequestMapping("/shiromybatis/userRole")
    public class UserRoleController {


    @Autowired
    private UserRoleService userRoleService;

    /**
     * 用户角色表添加;
     * @return
     */
    @ApiOperation(value = "添加UserRole", notes = "添加UserRole对象实体", httpMethod = "POST")
    @PostMapping("/")
    //    @RequiresPermissions("userRoleInfo:add")//权限管理;
    public ExecuteDTO userRoleCreate(@RequestBody UserRole entity){
        log.info("begin userRoleCreate");

        Boolean result=userRoleService.save(entity);
        log.info("保存UserRole："+result + " " + entity.toString() );

        log.info("end userRoleCreate");
        return new ExecuteDTO(result,result ? "保存成功":"保存失败",entity);

    }

    /**
     * 用户角色表删除;
     * @return
     */
    @ApiOperation(value = "删除UserRole", notes = "删除UserRole对象实体", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    //    @RequiresPermissions("userRoleInfo:del")//权限管理;
    public ExecuteDTO userRoleRemove(@PathVariable int id){
        log.info("begin userRoleRemove");
        Boolean result=userRoleService.removeById(id);
        log.info("删除UserRole：Id = "+id+" result: "+result);
        return new ExecuteDTO(result,result ? "删除成功":"删除失败",id);
    }

    /**
     * 用户角色表批量删除;
     * @return
     */
    @ApiOperation(value = "批量删除UserRole", notes = "批量删除UserRole对象实体", httpMethod = "DELETE")
    @DeleteMapping("/")
    //    @RequiresPermissions("userRoleInfo:del")//权限管理;
    public ExecuteDTO userRoleRemoveByIdList(@RequestBody Ids ids){
        log.info("begin userRoleRemoveByIdList");

        log.info("ids: " + ids.toString() );

        Boolean result=userRoleService.removeByIds(ids.getIds());

        log.info("end userRoleRemoveByIdList");
        return new ExecuteDTO(result,result ? "批量删除成功":"批量删除失败",ids);
    }



    /**
     * 用户角色表修改
     */
    @ApiOperation(value = "修改UserRole", notes = "修改UserRole对象实体", httpMethod = "PUT")
    @PutMapping(value = "/{id}")
    public ExecuteDTO userRoleUpdate(@PathVariable Integer id,@RequestBody UserRole entity){
        log.info("修改用户角色表："+entity);
        UserRole exitEntity=userRoleService.getById(id);
        if(exitEntity==null){
        return new ExecuteDTO(false,"修改失败:实体不存在",entity.toString());
        }

        Boolean result=userRoleService.updateById(entity);
        log.info("end userRoleUpdate");
        return new ExecuteDTO(result,result?"修改成功":"修改失败",entity);
    }

    /**
     * 根据ID用户角色表查询
     */
    @ApiOperation(value = "获取指定UserRole", notes = "获取指定UserRole", httpMethod = "GET")
    @GetMapping(value = "/{id}")
    public ExecuteDTO userRoleGetById(@PathVariable Integer id){
            log.info("获取指定UserRole："+id);
            UserRole entity =  userRoleService.getById(id);
            log.info("end userRoleGetById");
            return new ExecuteDTO(entity != null,entity != null ? "查询成功":"查询失败",entity);
    }


    /**
     * 用户角色表列表查询.
     * @return
     */
    @ApiOperation(value = "获取指定UserRole列表", notes = "获取指定UserRole列表", httpMethod = "GET")
    @GetMapping(value = "/list")
    //    @RequiresPermissions("userRoleInfo:view")//权限管理;
    public PageResultDTO userRoleList(@RequestParam(name = "page") int page,
    @RequestParam(name = "limit") int limit,
    @RequestParam(name = "userId" ,required = false) Integer userId  ){
        log.info("begin userRoleList");

        Page<UserRole> page1 =  new Page(page, limit);

        QueryWrapper queryWrapper = new QueryWrapper();
        if (userId != null){
        queryWrapper.eq("user_id",userId);
        log.info("search userId: " + userId);
        }

        IPage userRolePage = userRoleService.page(page1, queryWrapper);

        log.info("分页查询： " + page1.getTotal() + " " + page1.getRecords());
        log.info("查询结果： " + userRolePage.getTotal() + " " + userRolePage.getCurrent() + " " + userRolePage.getPages());
        PageResultDTO pageResultDTO = new PageResultDTO(200,"用户角色表列表", page1.getTotal(),
        userRolePage.getRecords());

        log.info("end userRoleList");
        return pageResultDTO;
    }

    

}

