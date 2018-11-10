package com.cycloneboy.shiromybatis.mapper;

import com.cycloneboy.shiromybatis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void aInsert() {
        User user = new User();

        for (int i = 20; i < 30; i++) {
            user.setUsername("test"+i);
            user.setPassword("123456");
            user.setName(user.getUsername());
            user.setState(1);
            user.setSalt(String.valueOf(System.currentTimeMillis()));
            user.setDescription(user.getUsername());
            System.out.println("Md5加密前的密码：" + user.getPassword());
            Object obj = new SimpleHash("MD5", user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), 2);
            System.out.println("Md5加密后的密码：" + obj);
            user.setPassword(obj.toString());

//            userMapper.insert(user);
        }

        // 成功直接拿会写的 ID
        System.err.println("\n插入成功 ID 为：" + user.getId());
    }

}