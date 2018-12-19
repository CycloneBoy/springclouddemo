package com.cycloneboy.jsoupdata.entity.moive;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-12-12 23:44
 **/
@NoArgsConstructor
@Data
@Entity
public class MoiveUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;

    public MoiveUrl(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
