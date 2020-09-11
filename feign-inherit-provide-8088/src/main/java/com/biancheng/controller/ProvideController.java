package com.biancheng.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.biancheng.api.UserRemoteClient;
import com.biancheng.pojo.User;

@RestController
public class ProvideController implements UserRemoteClient{

	public String getName() {
		return "zhangsan";
	}

	@Override
	public String getUserInfo(String name, int age) {
		return "name:"+name+",age:"+age;
	}

	@Override
	public Object getUserDetail(@RequestBody Map<String, Object> param) {
		return param;
	}

	@Override
	public Object addUser(@RequestBody User user) {
		return user;
	}

}
