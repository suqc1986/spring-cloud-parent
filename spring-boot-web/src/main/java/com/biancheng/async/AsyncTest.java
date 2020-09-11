package com.biancheng.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTest {
	@Async
	public String saveLog() {
		System.out.println(Thread.currentThread().getName());
	   return Thread.currentThread().getName();
	}
}
