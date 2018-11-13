package com.cycloneboy.shiromybatis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cycloneboy.shiromybatis.common.dto.ExecuteDTO;
import com.cycloneboy.shiromybatis.common.dto.Ids;
import com.cycloneboy.shiromybatis.common.dto.PageResultDTO;

import com.cycloneboy.shiromybatis.entity.Role;
import com.cycloneboy.shiromybatis.entity.UserRole;
import com.cycloneboy.shiromybatis.service.RoleService;


import com.cycloneboy.shiromybatis.service.UserRoleService;
import com.cycloneboy.shiromybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 前端控制器 RESTful API
 * </p>
 *
 * @author cycloneboy
 * @since 2018-11-10
 */
@Slf4j
@RestController
@RequestMapping("/shiromybatis/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 角色表添加;
     * @return
     */
    @ApiOperation(value = "添加Role", notes = "添加Role对象实体", httpMethod = "POST")
    @PostMapping("/")
    //    @RequiresPermissions("role:add")//权限管理;
    public ExecuteDTO roleCreate(@RequestBody Role entity){
        log.info("begin roleCreate");

        Boolean result=roleService.save(entity);
        log.info("保存Role："+result + " " + entity.getId() );

        log.info("end roleCreate");
        return new ExecuteDTO(result,result ? "保存成功":"保存失败",entity);

    }

    /**
     * 角色表删除;
     * @return
     */
    @ApiOperation(value = "删除Role", notes = "删除Role对象实体", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    //    @RequiresPermissions("roleInfo:del")//权限管理;
    public ExecuteDTO roleRemove(@PathVariable int id){
        log.info("begin roleRemove");
        Boolean result=roleService.removeById(id);
        log.info("删除Role：Id = "+id+" result: "+result);
        return new ExecuteDTO(result,result ? "删除成功":"删除失败",id);
    }

    /**
     * 角色表批量删除;
     * @return
     */
    @ApiOperation(value = "批量删除Role", notes = "批量删除Role对象实体", httpMethod = "DELETE")
    @DeleteMapping("/")
    //    @RequiresPermissions("roleInfo:del")//权限管理;
    public ExecuteDTO roleRemoveByIdList(@RequestBody Ids ids){
        log.info("begin roleRemoveByIdList");

        log.info("ids: " + ids.toString() );

        Boolean result=roleService.removeByIds(ids.getIds());

        log.info("end roleRemoveByIdList");
        return new ExecuteDTO(result,result ? "批量删除成功":"批量删除失败",ids);
    }



    /**
     * 角色表修改
     */
    @ApiOperation(value = "修改Role", notes = "修改Role对象实体", httpMethod = "PUT")
    @PutMapping(value = "/{id}")
    public ExecuteDTO roleUpdate(@PathVariable Integer id,@RequestBody Role entity){
        log.info("修改角色表："+entity);
        Role exitEntity=roleService.getById(id);
        if(exitEntity==null){
        return new ExecuteDTO(false,"修改失败:实体不存在",entity.getId());
        }

        entity.setId(id);
        Boolean result=roleService.updateById(entity);
        log.info("end roleUpdate");
        return new ExecuteDTO(result,result?"修改成功":"修改失败",entity);
    }

    /**
     * 根据ID角色表查询
     */
    @ApiOperation(value = "获取指定Role", notes = "获取指定Role", httpMethod = "GET")
    @GetMapping(value = "/{id}")
    public ExecuteDTO roleGetById(@PathVariable Integer id){
            log.info("获取指定Role："+id);
            Role entity =  roleService.getById(id);
            log.info("end roleGetById");
            return new ExecuteDTO(entity != null,entity != null ? "查询成功":"查询失败",entity);
    }


    /**
     * 角色表列表查询.
     * @return
     */
    @ApiOperation(value = "获取指定Role列表", notes = "获取指定Role列表", httpMethod = "GET")
    @GetMapping(value = "/list")
    //    @RequiresPermissions("roleInfo:view")//权限管理;
    public PageResultDTO roleList(@RequestParam(name = "page") int page,
    @RequestParam(name = "limit") int limit,
    @RequestParam(name = "role",defaultValue ="") String role  ){
        log.info("begin roleList");

        Page<Role> page1 =  new Page(page, limit);

        QueryWrapper<Role> queryWrapper = new QueryWrapper();
        if (role != ""){
        queryWrapper.like("role",role);
        log.info("search role: " + role);
        }

        IPage rolePage = roleService.page(page1, queryWrapper);

//        log.info("分页查询： " + page1.getTotal() + " " + page1.getRecords());
//        log.info("查询结果： " + rolePage.getTotal() + " " + rolePage.getCurrent() + " " + rolePage.getPages());
        PageResultDTO pageResultDTO = new PageResultDTO(200,"角色表列表", page1.getTotal(),
        rolePage.getRecords());

        log.info("end roleList");
        return pageResultDTO;
    }

    @ApiOperation(value = "根据用户ID获取指定Role列表", notes = "根据用户IDRole列表", httpMethod = "POST")
    @PostMapping("/rolesWithSelected")
    public List<Role> rolesWithSelected(Integer uid){
        log.info("begin rolesWithSelected");
        List<UserRole> userRoleList =
                userRoleService.list(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId,uid));

        Set roleIdSet = new HashSet();
        userRoleList.forEach((role) -> roleIdSet.add(role.getRoleId()));

        List<Role> roleList = roleService.list(null);
        roleList.forEach((role) -> {
            if(roleIdSet.contains(role.getId())){
                role.setAvailable(1);
            }else{
                role.setAvailable(0);
            }
        });

        log.info("end rolesWithSelected");
        return roleList;
    }

}

