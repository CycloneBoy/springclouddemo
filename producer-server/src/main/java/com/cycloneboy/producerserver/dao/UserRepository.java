package com.cycloneboy.producerserver.dao;


import com.cycloneboy.producerserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
