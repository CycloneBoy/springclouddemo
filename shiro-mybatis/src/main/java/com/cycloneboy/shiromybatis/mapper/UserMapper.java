package com.cycloneboy.shiromybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cycloneboy.shiromybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author cycloneboy
 * @since 2018-10-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
