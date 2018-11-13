package com.cycloneboy.shiromybatis.mapper;

import com.cycloneboy.shiromybatis.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author cycloneboy
 * @since 2018-10-28
 */
public interface RoleMapper extends BaseMapper<Role> {

    public List<Role> queryRoleListWithSelectedUserId(Integer id);
}
