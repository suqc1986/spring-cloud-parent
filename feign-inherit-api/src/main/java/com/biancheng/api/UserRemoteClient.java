package com.biancheng.api;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.biancheng.pojo.User;

public interface UserRemoteClient {
	@GetMapping("/user/name")
	String getName();

	@GetMapping("/user/info")
	String getUserInfo(@RequestParam("name") String name, @RequestParam("age") int age);

	@RequestMapping(value = "/user/detail", method = RequestMethod.POST)
	Object getUserDetail(@RequestBody Map<String, Object> param);

	@PostMapping("/user/add")
	Object addUser(@RequestBody User user);
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           