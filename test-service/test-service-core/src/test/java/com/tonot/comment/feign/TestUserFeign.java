package com.tonot.comment.feign;

import com.tonot.comment.TestServiceApplicationTests;
import com.tonot.comment.api.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestUserFeign extends TestServiceApplicationTests {

    @Autowired
    UserFeign userFeign;

    @Test
    public void getUser(){
        User user = userFeign.getUser();
        System.out.println(user);

    }
}
