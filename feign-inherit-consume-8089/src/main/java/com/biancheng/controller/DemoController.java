package com.biancheng.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biancheng.api.UserRemoteClient;

@RestController
public class DemoController  {
	@Autowired
    private UserRemoteClient userRemoteClient;
	
    @GetMapping("/call")
    public String callHello() {
        String result = userRemoteClient.getName();
        System.out.println("getName调用结果：" + result);
        return result;
    }
    
    @GetMapping("/callInfo")
    public String callInfo() {
    	return userRemoteClient.getUserInfo("zangyue", 18);
    }
    
    @PostMapping("/callDetail")
    public Object callDetail() {
    	Map<String,Object> param = new HashMap<>();
    	param.put("name", "suqc");
    	param.put("no", "yf888");
    	return userRemoteClient.getUserDetail(param);
    }
}