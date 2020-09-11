package com.biancheng.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.biancheng.irule.MyRule;

@Configuration
public class BeanConfiguration {
	
    @Bean
    @LoadBalanced
    //@MyLoadBalanced //负载均衡原理
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    
    
    @Bean
    public MyRule rule() {
        return new MyRule();
    }
}