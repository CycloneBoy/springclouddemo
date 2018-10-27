package com.cycloneboy.shiroadmin.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author cycloneboy
 * @since 2018-10-20
 */
@Data
@Accessors(chain = true)
@TableName("tbl_user")
public class User extends Model<User> {

private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
     @TableId(value = "id", type = IdType.AUTO)
     private Integer id;
    /**
     * 名称
     */
        private String username;
    /**
     * 密码
     */
        private String password;
    /**
     * 盐，用于加密
     */
        private String salt;
    /**
     * 状态, 1:可用, 0:不可用
     */
        private Integer state;
    /**
     * 描述
     */
        private String description;


@Override
protected Serializable pkVal() {
            return this.id;
        }

}
