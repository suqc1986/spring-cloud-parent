package com.biancheng.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.biancheng.fallback.UserRemoteClientFallbackFactory;
//如果需要查看回退原因，需配置fallbackfatory属性，回调时回传递异常信息
@FeignClient(value = "eureka-client-user-service", /* fallback = UserRemoteClientFallback.class, */ fallbackFactory = UserRemoteClientFallbackFactory.class)
public interface UserRemoteClient {
	@GetMapping("/user/hello")
	String hello();
}
