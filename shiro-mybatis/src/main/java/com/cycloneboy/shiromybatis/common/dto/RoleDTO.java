package com.cycloneboy.shiromybatis.common.dto;

import com.cycloneboy.shiromybatis.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-11-11 14:21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO extends Role {

    private Integer selected;
}
