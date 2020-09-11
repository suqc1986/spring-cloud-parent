package com.biancheng.autoconfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.biancheng.annotation.MyLoadBalanced;
import com.biancheng.intercepter.MyLoadBalancerInterceptor;

@Configuration
public class MyLoadBalancerAutoConfiguration {
	
    @Autowired
    private LoadBalancerClient loadBalancer;
    
    @MyLoadBalanced
    @Autowired(required = false)
    private List<RestTemplate> restTemplates = Collections.emptyList();
    
    @Bean
    public MyLoadBalancerInterceptor myLoadBalancerInterceptor() {
        return new MyLoadBalancerInterceptor(loadBalancer);
    }
    
    @Bean
    public SmartInitializingSingleton myLoadBalancedRestTemplateInitializer() {
        return new SmartInitializingSingleton() {
          @Override
          public void afterSingletonsInstantiated() {
            for (RestTemplate restTemplate : MyLoadBalancerAutoConfiguration.this.restTemplates){
                List<ClientHttpRequestInterceptor> list = new ArrayList<>(restTemplate.getInterceptors());
                list.add(myLoadBalancerInterceptor());
                restTemplate.setInterceptors(list);
            }
          }
        };
    }
}