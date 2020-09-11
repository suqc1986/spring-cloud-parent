package com.biancheng.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.biancheng.api.UserRemoteClient;
import com.biancheng.pojo.User;

@RestController
public class DemoController implements UserRemoteClient{

	public String getName() {
		return "zhangsan";
	}

	@Override
	public String getUserInfo(String name, int age) {
		return "name:"+name+",age:"+age;
	}

	@Override
	public String getUserDetail(Map<String, Object> param) {
		return param.toString();
	}

	@Override
	public String addUser(User user) {
		return user.toString();
	}

}
