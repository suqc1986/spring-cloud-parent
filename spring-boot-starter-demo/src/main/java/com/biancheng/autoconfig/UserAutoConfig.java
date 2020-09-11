package com.biancheng.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.biancheng.config.UserProperties;
import com.biancheng.template.UserTemplate;

@Configuration
@EnableConfigurationProperties(UserProperties.class)
public class UserAutoConfig {
	@Bean
	@ConditionalOnProperty(prefix = "spring.user",value = "enable", havingValue = "true")
	public UserTemplate userClient(UserProperties userProperties) {
		return new UserTemplate(userProperties);
	}
}
