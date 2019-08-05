package com.tonot.comment.controller;

import com.tonot.comment.feign.UserFeign;
import com.tonot.comment.api.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UserFeign userFeign;

    @GetMapping("/user")
    public User get(){
        return userFeign.getUser();
    }
}
