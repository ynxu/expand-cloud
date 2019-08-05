package com.tonot.comment.feign;

import com.tonot.comment.api.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserFeignHystric implements UserFeign {
    @Override
    public User getUser() {
        return new User("not found");
    }
}
