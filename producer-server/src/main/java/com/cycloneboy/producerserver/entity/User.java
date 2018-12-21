package com.cycloneboy.producerserver.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @date:2018-12-21 00:05
 **/
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private BigDecimal balance;
}
