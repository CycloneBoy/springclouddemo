package com.cycloneboy.shiromybatis.service.impl;

import com.cycloneboy.shiromybatis.entity.Role;
import com.cycloneboy.shiromybatis.mapper.RoleMapper;
import com.cycloneboy.shiromybatis.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author cycloneboy
 * @since 2018-10-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private  RoleMapper roleMapper;

    @Override
    public List<Role> queryRoleListWithSelectedUserId(Integer uid) {
        return roleMapper.queryRoleListWithSelectedUserId(uid);
    }
}
