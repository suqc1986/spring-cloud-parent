package com.biancheng.fallback;

import org.springframework.stereotype.Component;

import com.biancheng.feign.UserRemoteClient;
@Component
public class UserRemoteClientFallback implements UserRemoteClient{

	@Override
	public String hello() {
		return "hystrix fallback [fail hello]";
	}

}
