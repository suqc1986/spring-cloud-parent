package com.biancheng.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biancheng.api.UserRemoteClient;
import com.biancheng.pojo.User;

@RestController
public class ConsumeController  {
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
    
    @RequestMapping(value = "/callDetail", method = RequestMethod.POST)
    public Object callDetail(@RequestParam Map<String,Object> param) {
    	param.put("cus", new Date());
    	return userRemoteClient.getUserDetail(param);
    }
    
    @PostMapping("/callAddUser")
    public Object callAddUser() {
    	User user = new User();
    	user.setName("user");
    	user.setAge(30);
    	return userRemoteClient.addUser(user);
    }
}