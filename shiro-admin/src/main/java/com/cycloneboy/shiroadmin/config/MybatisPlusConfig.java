package com.cycloneboy.shiroadmin.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import java.beans.ConstructorProperties;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-10-20 22:43
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("com.cycloneboy.shiroadmin.mapper")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        return  new PerformanceInterceptor();
    }
}
