package com.cycloneboy.shiromybatis.service.impl;

import com.cycloneboy.shiromybatis.entity.Role;
import com.cycloneboy.shiromybatis.mapper.RoleMapper;
import com.cycloneboy.shiromybatis.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
