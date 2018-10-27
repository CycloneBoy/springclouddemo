package com.cycloneboy.shiroadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.cycloneboy.shiroadmin.mapper*")
public class ShiroAdminApplication {

    private static Logger logger = LoggerFactory.getLogger(ShiroAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ShiroAdminApplication.class, args);
        logger.info("================================启动成功！===============================");
    }
}
