package com.cycloneboy.shiromybatis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cycloneboy.shiromybatis.common.dto.ExecuteDTO;
import com.cycloneboy.shiromybatis.common.dto.Ids;
import com.cycloneboy.shiromybatis.common.dto.PageResultDTO;

import com.cycloneboy.shiromybatis.entity.Permission;
import com.cycloneboy.shiromybatis.service.PermissionService;


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
 * 权限表 前端控制器 RESTful API
 * </p>
 *
 * @author cycloneboy
 * @since 2018-11-10
 */
@Slf4j
@RestController
@RequestMapping("/shiromybatis/permission")
    public class PermissionController {


    @Autowired
    private PermissionService permissionService;

    /**
     * 权限表添加;
     * @return
     */
    @ApiOperation(value = "添加Permission", notes = "添加Permission对象实体", httpMethod = "POST")
    @PostMapping("/")
    //    @RequiresPermissions("permissionInfo:add")//权限管理;
    public ExecuteDTO permissionCreate(@RequestBody Permission entity){
        log.info("begin permissionCreate");

        Boolean result=permissionService.save(entity);
        log.info("保存Permission："+result + " " + entity.getId() );

        log.info("end permissionCreate");
        return new ExecuteDTO(result,result ? "保存成功":"保存失败",entity);

    }

    /**
     * 权限表删除;
     * @return
     */
    @ApiOperation(value = "删除Permission", notes = "删除Permission对象实体", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    //    @RequiresPermissions("permissionInfo:del")//权限管理;
    public ExecuteDTO permissionRemove(@PathVariable int id){
        log.info("begin permissionRemove");
        Boolean result=permissionService.removeById(id);
        log.info("删除Permission：Id = "+id+" result: "+result);
        return new ExecuteDTO(result,result ? "删除成功":"删除失败",id);
    }

    /**
     * 权限表批量删除;
     * @return
     */
    @ApiOperation(value = "批量删除Permission", notes = "批量删除Permission对象实体", httpMethod = "DELETE")
    @DeleteMapping("/")
    //    @RequiresPermissions("permissionInfo:del")//权限管理;
    public ExecuteDTO permissionRemoveByIdList(@RequestBody Ids ids){
        log.info("begin permissionRemoveByIdList");

        log.info("ids: " + ids.toString() );

        Boolean result=permissionService.removeByIds(ids.getIds());

        log.info("end permissionRemoveByIdList");
        return new ExecuteDTO(result,result ? "批量删除成功":"批量删除失败",ids);
    }



    /**
     * 权限表修改
     */
    @ApiOperation(value = "修改Permission", notes = "修改Permission对象实体", httpMethod = "PUT")
    @PutMapping(value = "/{id}")
    public ExecuteDTO permissionUpdate(@PathVariable Integer id,@RequestBody Permission entity){
        log.info("修改权限表："+entity);
        Permission exitEntity=permissionService.getById(id);
        if(exitEntity==null){
        return new ExecuteDTO(false,"修改失败:实体不存在",entity.getId());
        }

        entity.setId(id);
        Boolean result=permissionService.updateById(entity);
        log.info("end permissionUpdate");
        return new ExecuteDTO(result,result?"修改成功":"修改失败",entity);
    }

    /**
     * 根据ID权限表查询
     */
    @ApiOperation(value = "获取指定Permission", notes = "获取指定Permission", httpMethod = "GET")
    @GetMapping(value = "/{id}")
    public ExecuteDTO permissionGetById(@PathVariable Integer id){
            log.info("获取指定Permission："+id);
            Permission entity =  permissionService.getById(id);
            log.info("end permissionGetById");
            return new ExecuteDTO(entity != null,entity != null ? "查询成功":"查询失败",entity);
    }


    /**
     * 权限表列表查询.
     * @return
     */
    @ApiOperation(value = "获取指定Permission列表", notes = "获取指定Permission列表", httpMethod = "GET")
    @GetMapping(value = "/list")
    //    @RequiresPermissions("permissionInfo:view")//权限管理;
    public PageResultDTO permissionList(@RequestParam(name = "page") int page,
    @RequestParam(name = "limit") int limit,
    @RequestParam(name = "name",defaultValue ="") String name  ){
        log.info("begin permissionList");

        Page<Permission> page1 =  new Page(page, limit);

        QueryWrapper<Permission> queryWrapper = new QueryWrapper();
        if (name != ""){
        queryWrapper.like("name",name);
        log.info("search name: " + name);
        }

        IPage permissionPage = permissionService.page(page1, queryWrapper);

        log.info("分页查询： " + page1.getTotal() + " " + page1.getRecords());
        log.info("查询结果： " + permissionPage.getTotal() + " " + permissionPage.getCurrent() + " " + permissionPage.getPages());
        PageResultDTO pageResultDTO = new PageResultDTO(200,"权限表列表", page1.getTotal(),
        permissionPage.getRecords());

        log.info("end permissionList");
        return pageResultDTO;
    }

    

}

