package com.biancheng.template;

import com.biancheng.config.UserProperties;


public class UserTemplate {

	private UserProperties userProperties;
	
	public UserTemplate() {
	}
	public UserTemplate(UserProperties userProperties) {
		this.userProperties = userProperties;
	}
	
	public String getName() {
		return userProperties.getName();
	}
}
