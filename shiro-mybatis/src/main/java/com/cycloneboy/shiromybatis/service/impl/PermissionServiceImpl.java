package com.cycloneboy.shiromybatis.service.impl;

import com.cycloneboy.shiromybatis.entity.Permission;
import com.cycloneboy.shiromybatis.mapper.PermissionMapper;
import com.cycloneboy.shiromybatis.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author cycloneboy
 * @since 2018-10-28
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
