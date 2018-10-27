package com.cycloneboy.shiromybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cycloneboy.shiromybatis.entity.User;
import com.cycloneboy.shiromybatis.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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

//        Assert.assertTrue(mapper.insert(user) > 0);
        // 成功直接拿会写的 ID
        System.err.println("\n插入成功 ID 为：" + user.getId());
    }


//    @Test
//    public void bDelete() {
//        Assert.assertTrue(mapper.deleteById(3L) > 0);
//        Assert.assertTrue(mapper.delete(new QueryWrapper<User>()
//                .lambda().eq(User::getName, "Sandy")) > 0);
//    }
//
//
//    @Test
//    public void cUpdate() {
//        Assert.assertTrue(mapper.updateById(new User().setId(1L).setEmail("ab@c.c")) > 0);
//        Assert.assertTrue(mapper.update(new User().setName("mp"),
//                new UpdateWrapper<User>().lambda()
//                        .set(User::getAge, 3)
//                        .eq(User::getId, 2)) > 0);
//    }
//
//
//    @Test
//    public void dSelect() {
//        Assert.assertEquals("ab@c.c", mapper.selectById(1L).getEmail());
//        User user = mapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 2));
//        Assert.assertEquals("mp", user.getName());
//        Assert.assertTrue(3 == user.getAge());
//    }

}
