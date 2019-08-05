package com.tonot.comment.controller;

import com.tonot.api.UserApi;
import com.tonot.comment.api.domain.User;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

    @Override
    public User getUser() {
        return new User("username");
    }
}
