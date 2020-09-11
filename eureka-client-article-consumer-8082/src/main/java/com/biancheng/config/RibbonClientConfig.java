package com.biancheng.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

@RibbonClient(name = "eureka-client-user-provider", configuration = BeanConfiguration.class)
public class RibbonClientConfig {
	
}