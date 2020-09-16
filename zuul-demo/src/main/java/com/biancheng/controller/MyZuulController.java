package com.biancheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.zuul.context.RequestContext;

@RestController
public class MyZuulController {
	@Autowired
	private RestTemplate restTemplate;
	
    @GetMapping("/local/{id}")
    public String local(@PathVariable String id) {
    	RequestContext ctx = RequestContext.getCurrentContext();
        return id;
    }
    
    
	@GetMapping("/ld/callHello")
	public String callHello() {
		String result = restTemplate.getForObject("http://eureka-client-user-provider/provider/hello", String.class);
		return result;
	}
}
