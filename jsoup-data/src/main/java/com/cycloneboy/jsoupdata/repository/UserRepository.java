package com.cycloneboy.jsoupdata.repository;

import com.cycloneboy.jsoupdata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-12-11 23:15
 **/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
