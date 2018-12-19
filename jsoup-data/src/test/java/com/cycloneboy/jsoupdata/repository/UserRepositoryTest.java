package com.cycloneboy.jsoupdata.repository;

import com.cycloneboy.jsoupdata.entity.User;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAdd(){
        log.info("begin save");
        for (int i = 1; i < 100; i++) {
            userRepository.save(new User("testname"+String.valueOf(i),"admin","1234@qq.com","test"));
        }
        log.info("end save");
    }

}