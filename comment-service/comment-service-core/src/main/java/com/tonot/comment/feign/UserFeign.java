package com.tonot.comment.feign;

import com.tonot.api.UserApi;
import com.tonot.comment.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-test",path = "test",fallback = UserFeignHystric.class,configuration = FeignConfig.class)
public interface UserFeign extends UserApi {

}
