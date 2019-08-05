package com.tonot.api;

import com.tonot.comment.api.domain.User;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserApi {

    @GetMapping(value = "/user")
    User getUser();

}
