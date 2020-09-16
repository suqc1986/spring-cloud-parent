package com.biancheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.biancheng.feign.UserRemoteClient;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class CallController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserRemoteClient userRemoteClient;
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@GetMapping("/callHello")
	@HystrixCommand(fallbackMethod = "defaultCallHello"/*, commandProperties = {
			@HystrixProperty(name = "hystrix.command.default.execution.isolation.strategy", value = "THREAD") }*/)
	public String callHello() {
		String result = restTemplate.getForObject("http://eureka-client-user-provider/provider/hello", String.class);
		return result;
	}

	@GetMapping("/hystrix/hello")
	public String sayHello() {
		return userRemoteClient.hello();
	}

	public String defaultCallHello() {
		return "fail";
	}
	
}
