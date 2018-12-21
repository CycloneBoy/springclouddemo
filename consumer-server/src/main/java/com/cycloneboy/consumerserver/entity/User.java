package com.cycloneboy.consumerserver.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: springclouddemo
 * @description:
 * @author : cycloneboy
 * @date:2018-12-21 00:05
 **/

@Data
public class User {

    private Long id;

    private String username;

    private String name;

    private Integer age;

    private BigDecimal balance;
}
