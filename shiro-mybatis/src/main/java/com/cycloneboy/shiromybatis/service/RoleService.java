package com.cycloneboy.shiromybatis.service;

import com.cycloneboy.shiromybatis.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author cycloneboy
 * @since 2018-10-28
 */
public interface RoleService extends IService<Role> {

    public List<Role> queryRoleListWithSelectedUserId(Integer uid);
}
