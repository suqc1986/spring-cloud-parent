package com.biancheng.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.biancheng.feign.UserRemoteClient;
import feign.hystrix.FallbackFactory;

/**
 * 看回退原因用factory
 * 
 * @author suqc
 *
 */
@Component
public class UserRemoteClientFallbackFactory implements FallbackFactory<UserRemoteClient> {
	
	private Logger logger = LoggerFactory.getLogger(UserRemoteClientFallbackFactory.class);
	
	@Autowired
	private UserRemoteClientFallback fallback;

	@Override
	public UserRemoteClient create(final Throwable cause) {
		logger.error("UserRemoteClient回退：", cause);
		System.out.println(cause);
		return fallback;
	}
}