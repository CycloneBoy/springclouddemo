package com.cycloneboy.jsoupdata.entity.moive;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-12-12 00:23
 **/
@Entity
@Data
public class Moive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieId;
    //电影链接
    private String movieUrl;
    //电影名
    private String name;
    //导演
    private String director;
    //编剧
    private String scenarist;
    //主演
    private String actors;
    //类型
    private String type;
    //制片国家/地区
    private String country;
    //语言
    private String language;
    // 上映日期
    private String releaseDate;
    // 片长
    private String runtime;
    //豆瓣评分
    private String ratingNum;
    //标签
    private String tags;
    //评价数
    private String voteNum;
}
