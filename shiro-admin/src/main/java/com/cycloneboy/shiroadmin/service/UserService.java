package com.cycloneboy.shiroadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cycloneboy.shiroadmin.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author cycloneboy
 * @since 2018-10-20
 */
public interface UserService extends IService<User> {

     IPage<User> selectUserPage(Page<User> page, Integer state) ;


}
