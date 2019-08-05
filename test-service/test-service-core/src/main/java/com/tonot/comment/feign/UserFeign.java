package com.tonot.comment.feign;

import com.tonot.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-test",path = "test",fallback = UserFeignHystric.class)
public interface UserFeign extends UserApi {
}
