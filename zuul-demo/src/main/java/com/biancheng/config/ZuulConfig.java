package com.biancheng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.biancheng.filter.IpFilter;
import com.netflix.zuul.ZuulFilter;

@Configuration
public class ZuulConfig {
	@Bean
	public ZuulFilter ipFilter() {
		return new IpFilter();
	}
}
