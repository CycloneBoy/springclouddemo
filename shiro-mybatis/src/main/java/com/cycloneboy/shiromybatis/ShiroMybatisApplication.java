package com.cycloneboy.shiromybatis;


import com.cycloneboy.shiromybatis.entity.User;
import com.cycloneboy.shiromybatis.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan("com.cycloneboy.shiromybatis.mapper*")
@RestController
@SpringBootApplication
public class ShiroMybatisApplication {

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/test")
    public String test(){
        return "test";
    }

    @GetMapping(value = "/hello")
    public User getHello(){
        User user = new User();
//       user.setId(10);
       user.setUsername("test");
       user.setPassword("123456");
       user.setSalt("123");
       user.setDescription("test");
       user.setState(1);
        userMapper.insert(user);

        System.out.println("插入user： " + user.getId());

       return user;

    }

    public static void main(String[] args) {
        SpringApplication.run(ShiroMybatisApplication.class, args);
    }
}
