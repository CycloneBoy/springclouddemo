package com.cycloneboy.shiromybatis.service.impl;

import com.cycloneboy.shiromybatis.entity.User;
import com.cycloneboy.shiromybatis.mapper.UserMapper;
import com.cycloneboy.shiromybatis.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author cycloneboy
 * @since 2018-10-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
