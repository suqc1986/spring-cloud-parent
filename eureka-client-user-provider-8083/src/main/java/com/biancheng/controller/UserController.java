package com.biancheng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@GetMapping("/provider/hello")
	public String hello() {
		return "eureka-client-user-provider-8083 /provider/hello-8083...";
	}
}