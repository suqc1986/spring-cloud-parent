package com.biancheng.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.user")
public class UserProperties {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
