package com.biancheng.cuspredicate;

import java.util.function.Predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

public class CheckAuthRoutePredicateFactory
		extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {

	public CheckAuthRoutePredicateFactory(Class<Config> configClass) {
		super(configClass);
	}

	public static class Config {
		private String name;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	@Override
	public Predicate<ServerWebExchange> apply(Config config) {
//		 在 apply 方法中可以通过exchange.getRequest() 拿到 ServerHttpRequest 对象，从而可以获取到请求的参数、请求方式、请求头等信息
//	        return exchange -> {
//	            System.err.println("进入了CheckAuthRoutePredicateFactory\t" + config.getName());
//	            if (config.getName().equals("zhangsan")) {
//	                return true;
//	            }
//	            return false;
//	        };
		return new Predicate<ServerWebExchange>() {

			@Override
			public boolean test(ServerWebExchange exchange) {
				ServerHttpRequest request = exchange.getRequest();
				
				System.err.println("进入了CheckAuthRoutePredicateFactory\t" + config.getName());
				if (config.getName().equals("zhangsan")) {
					return true;
				}
				return false;
			}
		};
	}
}
