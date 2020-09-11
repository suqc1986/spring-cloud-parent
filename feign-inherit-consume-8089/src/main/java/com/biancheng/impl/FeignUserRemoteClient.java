package com.biancheng.impl;

import org.springframework.cloud.openfeign.FeignClient;

import com.biancheng.api.UserRemoteClient;

@FeignClient("feign-inherit-provide")
public interface FeignUserRemoteClient extends UserRemoteClient {

}
