package com.example.springsecurity.controller;

import com.example.springsecurity.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final List<User> lists = Arrays.asList(
            new User(1,"Rufet"),
            new User(2,"Ugur"),
            new User(3,"Alsu"),
            new User(4,"Kerim")
    );

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Integer id){
        return lists.stream()
                .filter(user -> user.getId().equals(id)).findFirst()
                .orElseThrow(()->new IllegalArgumentException("User is not fount"));
    }
}
