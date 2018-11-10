package com.cycloneboy.shiromybatis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cycloneboy.shiromybatis.common.dto.ExecuteDTO;
import com.cycloneboy.shiromybatis.common.dto.Ids;
import com.cycloneboy.shiromybatis.common.dto.PageResultDTO;

import com.cycloneboy.shiromybatis.entity.RolePermission;
import com.cycloneboy.shiromybatis.service.RolePermissionService;


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
 * 角色权限表 前端控制器 RESTful API
 * </p>
 *
 * @author cycloneboy
 * @since 2018-11-10
 */
@Slf4j
@RestController
@RequestMapping("/shiromybatis/rolePermission")
    public class RolePermissionController {


    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 角色权限表添加;
     * @return
     */
    @ApiOperation(value = "添加RolePermission", notes = "添加RolePermission对象实体", httpMethod = "POST")
    @PostMapping("/")
    //    @RequiresPermissions("rolePermissionInfo:add")//权限管理;
    public ExecuteDTO rolePermissionCreate(@RequestBody RolePermission entity){
        log.info("begin rolePermissionCreate");

        Boolean result=rolePermissionService.save(entity);
        log.info("保存RolePermission："+result + " " + entity.toString() );

        log.info("end rolePermissionCreate");
        return new ExecuteDTO(result,result ? "保存成功":"保存失败",entity);

    }

    /**
     * 角色权限表删除;
     * @return
     */
    @ApiOperation(value = "删除RolePermission", notes = "删除RolePermission对象实体", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    //    @RequiresPermissions("rolePermissionInfo:del")//权限管理;
    public ExecuteDTO rolePermissionRemove(@PathVariable int id){
        log.info("begin rolePermissionRemove");
        Boolean result=rolePermissionService.removeById(id);
        log.info("删除RolePermission：Id = "+id+" result: "+result);
        return new ExecuteDTO(result,result ? "删除成功":"删除失败",id);
    }

    /**
     * 角色权限表批量删除;
     * @return
     */
    @ApiOperation(value = "批量删除RolePermission", notes = "批量删除RolePermission对象实体", httpMethod = "DELETE")
    @DeleteMapping("/")
    //    @RequiresPermissions("rolePermissionInfo:del")//权限管理;
    public ExecuteDTO rolePermissionRemoveByIdList(@RequestBody Ids ids){
        log.info("begin rolePermissionRemoveByIdList");

        log.info("ids: " + ids.toString() );

        Boolean result=rolePermissionService.removeByIds(ids.getIds());

        log.info("end rolePermissionRemoveByIdList");
        return new ExecuteDTO(result,result ? "批量删除成功":"批量删除失败",ids);
    }



    /**
     * 角色权限表修改
     */
    @ApiOperation(value = "修改RolePermission", notes = "修改RolePermission对象实体", httpMethod = "PUT")
    @PutMapping(value = "/{id}")
    public ExecuteDTO rolePermissionUpdate(@PathVariable Integer id,@RequestBody RolePermission entity){
        log.info("修改角色权限表："+entity);
        RolePermission exitEntity=rolePermissionService.getById(id);
        if(exitEntity==null){
            return new ExecuteDTO(false,"修改失败:实体不存在",entity.toString());
        }

        Boolean result=rolePermissionService.updateById(entity);
        log.info("end rolePermissionUpdate");
        return new ExecuteDTO(result,result?"修改成功":"修改失败",entity);
    }

    /**
     * 根据ID角色权限表查询
     */
    @ApiOperation(value = "获取指定RolePermission", notes = "获取指定RolePermission", httpMethod = "GET")
    @GetMapping(value = "/{id}")
    public ExecuteDTO rolePermissionGetById(@PathVariable Integer id){
            log.info("获取指定RolePermission："+id);
            RolePermission entity =  rolePermissionService.getById(id);
            log.info("end rolePermissionGetById");
            return new ExecuteDTO(entity != null,entity != null ? "查询成功":"查询失败",entity);
    }


    /**
     * 角色权限表列表查询.
     * @return
     */
    @ApiOperation(value = "获取指定RolePermission列表", notes = "获取指定RolePermission列表", httpMethod = "GET")
    @GetMapping(value = "/list")
    //    @RequiresPermissions("rolePermissionInfo:view")//权限管理;
    public PageResultDTO rolePermissionList(@RequestParam(name = "page") int page,
    @RequestParam(name = "limit") int limit,
    @RequestParam(name = "roleId",required = false) Integer roleId  ){
        log.info("begin rolePermissionList");

        Page<RolePermission> page1 =  new Page(page, limit);

        QueryWrapper queryWrapper = new QueryWrapper();
        if (roleId != null){
            queryWrapper.eq("role_id",roleId);
        log.info("search roleId: " + roleId);
        }

        IPage rolePermissionPage = rolePermissionService.page(page1, queryWrapper);

        log.info("分页查询： " + page1.getTotal() + " " + page1.getRecords());
        log.info("查询结果： " + rolePermissionPage.getTotal() + " " + rolePermissionPage.getCurrent() + " " + rolePermissionPage.getPages());
        PageResultDTO pageResultDTO = new PageResultDTO(200,"角色权限表列表", page1.getTotal(),
        rolePermissionPage.getRecords());

        log.info("end rolePermissionList");
        return pageResultDTO;
    }

    

}

