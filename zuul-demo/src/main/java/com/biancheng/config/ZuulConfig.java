package com.biancheng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.biancheng.filter.ErrorFilter;
import com.biancheng.filter.IpFilter;
import com.biancheng.filter.PostFilter;
import com.biancheng.filter.ReceDataFilter;
import com.netflix.zuul.ZuulFilter;

@Configuration
public class ZuulConfig {
	@Bean
	public ZuulFilter ipFilter() {
		return new IpFilter();
	}

	@Bean
	public ZuulFilter receDataFilter() {
		return new ReceDataFilter();
	}

	@Bean
	public ZuulFilter errorFilter() {
		return new ErrorFilter();
	}
	
	@Bean
	public ZuulFilter postFilter() {
		return new PostFilter();
	}
}
