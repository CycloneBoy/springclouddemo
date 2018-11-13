package com.cycloneboy.shiromybatis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cycloneboy.shiromybatis.common.dto.ExecuteDTO;
import com.cycloneboy.shiromybatis.common.dto.Ids;
import com.cycloneboy.shiromybatis.common.dto.PageResultDTO;

import com.cycloneboy.shiromybatis.entity.User;
import com.cycloneboy.shiromybatis.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/shiromybatis/user")
@Api("User相关api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户添加;
     * @return
     */
    @ApiOperation(value = "添加User", notes = "添加User对象实体", httpMethod = "POST")
    @PostMapping("/")
//    @RequiresPermissions("userInfo:add")//权限管理;
    public ExecuteDTO userCreate(@RequestBody User entity){
        log.info("begin userCreate");
        System.out.println("Md5加密前的密码：" + entity.getPassword());
        // 当前日期时间戳
        entity.setSalt(String.valueOf(System.currentTimeMillis()));
        Object obj = new SimpleHash("MD5", entity.getPassword(), ByteSource.Util.bytes(entity.getCredentialsSalt()), 2);
        System.out.println("Md5加密后的密码：" + obj);
        entity.setPassword(obj.toString());

        Boolean result=userService.save(entity);
        log.info("保存User："+result + " " + entity.getId() );

        log.info("end userCreate");
        return new ExecuteDTO(result,result ? "保存成功":"保存失败",entity);

    }

    /**
     * 用户删除;
     * @return
     */
    @ApiOperation(value = "删除User", notes = "删除User对象实体", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
//    @RequiresPermissions("userInfo:del")//权限管理;
    public ExecuteDTO userRemove(@PathVariable int id){
        log.info("begin userRemove");
        Boolean result=userService.removeById(id);
        log.info("删除User：Id = "+id+" result: "+result);
        return new ExecuteDTO(result,result ? "删除成功":"删除失败",id);
    }

    /**
     * 用户批量删除;
     * @return
     */
    @ApiOperation(value = "批量删除User", notes = "批量删除User对象实体", httpMethod = "DELETE")
    @DeleteMapping("/")
//    @RequiresPermissions("userInfo:del")//权限管理;
    public ExecuteDTO userRemoveByIdList(@RequestBody Ids ids){
        log.info("begin userRemoveByIdList");

        log.info("ids: " + ids.toString() );

        Boolean result=userService.removeByIds(ids.getIds());

        log.info("end userRemoveByIdList");
        return new ExecuteDTO(result,result ? "批量删除成功":"批量删除失败",ids);
    }


    /**
     * 用户批量删除;
     * @return
     */
    @ApiOperation(value = "批量修改User的角色", notes = "批量修改User的角色", httpMethod = "DELETE")
    @PutMapping("/{userId}/role/")
//    @RequiresPermissions("userInfo:del")//权限管理;
    public ExecuteDTO userAddRolesByIdList(@PathVariable Integer userId, @RequestBody Ids ids){
        log.info("begin userAddRoleByIdList");

        log.info("userId: " + String.valueOf(userId ));
        log.info("ids: " + ids.toString() );

        boolean result = true;

        log.info("end userAddRoleByIdList");
        return new ExecuteDTO(result,result ? "批量修改User的角色":"批量修改User的角色",ids);
    }


    /**
     * 修改用户
     */
    @ApiOperation(value = "修改User", notes = "修改User对象实体", httpMethod = "PUT")
    @PutMapping(value = "/{id}")
    public ExecuteDTO userUpdate(@PathVariable Integer id,@RequestBody User entity){
        log.info("修改用户："+entity);
        User exitEntity=userService.getById(id);
        if(exitEntity==null){
            return new ExecuteDTO(false,"修改失败:实体不存在",entity.getId());
        }

        entity.setId(id);
        Boolean result=userService.updateById(entity);
        log.info("end userUpdate");
        return new ExecuteDTO(result,result?"修改成功":"修改失败",entity);
    }

    /**
     * 根据ID用户查询
     */
    @ApiOperation(value = "获取指定User", notes = "获取指定User", httpMethod = "GET")
    @GetMapping(value = "/{id}")
    public ExecuteDTO userGetById(@PathVariable Integer id){
        log.info("获取指定User："+id);
        User entity =  userService.getById(id);
        log.info("end userGetById");
        return new ExecuteDTO(entity != null,entity != null ? "查询成功":"查询失败",entity);
    }


    /**
     * 用户列表查询.
     * @return
     */
    @ApiOperation(value = "获取指定User列表", notes = "获取指定User列表", httpMethod = "GET")
    @GetMapping(value = "/list")
//    @RequiresPermissions("userInfo:view")//权限管理;
    public PageResultDTO userList(@RequestParam(name = "page") int page,
                                  @RequestParam(name = "limit") int limit,
                                  @RequestParam(name = "name",defaultValue ="") String name  ){
        log.info("begin userList");

        Page<User> page1 =  new Page(page, limit);

        QueryWrapper<User> queryWrapper = new QueryWrapper();
        if (name != ""){
            queryWrapper.like("name",name);
            log.info("search name: " + name);
        }

        IPage userPage = userService.page(page1, queryWrapper);

//        log.info("分页查询： " + page1.getTotal() + " " + page1.getRecords());
//        log.info("查询结果： " + userPage.getTotal() + " " + userPage.getCurrent() + " " + userPage.getPages());
        PageResultDTO pageResultDTO = new PageResultDTO(200,"用户列表", page1.getTotal(),
                userPage.getRecords());

        log.info("end userList");
        return pageResultDTO;
    }


}

