package com.biancheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biancheng.async.AsyncTest;
import com.biancheng.config.MyConfig;
import com.biancheng.template.UserTemplate;

@RestController
public class HelloController {
	
	@Autowired
	private Environment env;
	@Autowired
	private UserTemplate userTemplate;
	
	@Value("${server.test}")
	private String serverTest;
	
	@Autowired
	private MyConfig myConfig;
	
	@Autowired
	private AsyncTest asyncTest;
	
    @GetMapping("/hello")
    public String hello() {
    	String port = env.getProperty("local.server.port");
    	System.out.println(env);
    	System.out.println(myConfig.getConfigName());
    	int a = 1/0;
        return port+":"+serverTest;
    }
    @GetMapping("/async")
    public String async() {
    	System.out.println(Thread.currentThread().getName());
    	return asyncTest.saveLog();
    }
    
    @GetMapping("/getName")
    public String getName() {
    	return userTemplate.getName();
    }
}