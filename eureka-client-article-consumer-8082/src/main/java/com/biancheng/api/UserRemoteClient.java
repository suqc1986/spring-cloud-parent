package com.biancheng.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.biancheng.config.FeignConfiguration;

@FeignClient(value = "eureka-client-user-provider",configuration = FeignConfiguration.class)
public interface UserRemoteClient {
    @GetMapping("/provider/hello")
    String hello();
}