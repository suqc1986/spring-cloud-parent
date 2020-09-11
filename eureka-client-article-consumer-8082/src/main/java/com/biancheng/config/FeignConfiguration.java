package com.biancheng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignConfiguration {
	/**
	 * 日志级别
	 *  NONE：不输出日志。
		BASIC：只输出请求方法的 URL 和响应的状态码以及接口执行的时间。
		HEADERS：将 BASIC 信息和请求头信息输出。
		FULL：输出完整的请求信息
	 */
	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}
	
	//spring Cloud 中使用原生的注解方式来定义客户端也是可以的，通过配置契约来改变这个配置
//    @Bean
//    public Contract feignContract() {
//        return new feign.Contract.Default();
//    }
    
	//通常我们调用的接口都是有权限控制的，很多时候可能认证的值是通过参数去传递的，还有就是通过请求头去传递认证信息，
	//比如 Basic 认证方式。在 Feign 中我们可以直接配置 Basic 认证，代码如下所示。
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }
    
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }

}
