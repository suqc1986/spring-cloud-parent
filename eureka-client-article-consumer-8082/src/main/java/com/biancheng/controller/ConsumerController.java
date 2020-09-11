package com.biancheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.biancheng.api.UserRemoteClient;

@RestController
public class ConsumerController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoadBalancerClient loadBalancer;
	@Autowired
	private UserRemoteClient userRemoteClient;
	
	@GetMapping("/consumer/callhello")
	public String hello() {
//		return  restTemplate.getForObject("http://eureka-client-user-provider/provider/hello", String.class);
		return userRemoteClient.hello();
	}
	
	@GetMapping("/consumer/choose")
	public Object chooseUrl() {
	    ServiceInstance instance = loadBalancer.choose("eureka-client-user-provider");
	    return instance;
	}
	
}