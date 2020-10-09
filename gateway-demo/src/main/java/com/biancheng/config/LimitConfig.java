package com.biancheng.config;

import java.util.List;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
public class LimitConfig {
	@Bean // IP 限流的 Key 指定具体代码
	public KeyResolver ipKeyResolver() {
		return new KeyResolver() {
			@Override
			public Mono<String> resolve(ServerWebExchange exchange) {
				return Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
			}
		};
	}

	@Bean // 根据用户来做限流只需要获取当前请求的用户 ID 或者用户名
	KeyResolver userKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
	}

	@Bean // 获取请求地址的 uri 作为限流 Key
	KeyResolver apiKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getPath().value());
	}

	public static String getIpAddr(ServerHttpRequest request) {
		HttpHeaders headers = request.getHeaders();
		List<String> ips = headers.get("X-Forwarded-For");
		String ip = "192.168.1.1";
		if (ips != null && ips.size() > 0) {
			ip = ips.get(0);
		}
		return ip;
	}
}
