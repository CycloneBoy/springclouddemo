package com.cycloneboy.jsoupdata.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.regex.PatternSyntaxException;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-12-11 23:08
 **/
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private String description;

    public User(String username, String password, String email, String description) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.description = description;
    }
}
