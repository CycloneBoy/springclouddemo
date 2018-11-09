package com.cycloneboy.shiromybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cycloneboy.shiromybatis.entity.User;
import com.cycloneboy.shiromybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroMybatisApplicationTests {

    @Resource
    private UserMapper mapper;

    @Test
    public void aInsert() {
        User user = new User();
        user.setUsername("test");
        user.setId(3);
        user.setPassword("123");
        user.setState(1);
        user.setSalt("12345");

        mapper.insert(user);

        Assert.assertTrue(mapper.insert(user) > 0);
        // 成功直接拿会写的 ID
        System.err.println("\n插入成功 ID 为：" + user.getId());
    }


    @Test
    public void bDelete() {
//        Assert.assertTrue(mapper.deleteById(3) > 0);
        Assert.assertTrue(mapper.delete(new QueryWrapper<User>()
                .lambda().eq(User::getUsername, "lisi")) > 0);
    }


    @Test
    public void cUpdate() {
        User user = mapper.selectById(6);
        log.info(user.toString());
        user.setDescription("test user test");
        Assert.assertTrue(mapper.updateById(user) > 0);

        user.setUsername("lisi");
//        Assert.assertTrue(mapper.update(user,
//                new UpdateWrapper<User>().lambda()
//                        .set(User::getSalt, "123456")
//                        .eq(User::getId, 2)) > 0);
    }


    @Test
    public void dSelect() {
        Assert.assertEquals("admin", mapper.selectById(1L).getUsername());
        User user = mapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 2));
//        Assert.assertEquals("lisi", user.getUsername());
//        Assert.assertTrue("123" == user.getPassword());
    }

    @Test
    public void getPassword(){
        int hashIterations = 2;//加密的次数
        Object salt = "8d78869f470951332959580424d4bf4f";//盐值(博主这里的salt是 username+salt（一般是用户名加一个随机字符串）, 这里以字符串“admin”为例)
        Object credentials = "123456";//密码
        String hashAlgorithmName = "MD5";//加密方式
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println("加密后的值----->" + simpleHash);
    }

    @Test
    public void testGetPassword(){
        String hashAlgorithName = "MD5";
        String password = "123456";
        int hashIterations = 2;//加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes("admin8d78869f470951332959580424d4bf4f");
        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        System.out.println("Md5加密后的密码：" + obj);
    }
}
