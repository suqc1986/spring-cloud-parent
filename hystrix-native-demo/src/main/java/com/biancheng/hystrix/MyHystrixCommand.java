package com.biancheng.hystrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

public class MyHystrixCommand extends HystrixCommand<String> {
	private final String name;

	public MyHystrixCommand(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
		this.name = name;
	}

//	@Override
//	protected String run() {
//		return this.name + ":" + Thread.currentThread().getName();
//	}
	
	@Override
	protected String run() throws Exception {
//		try {
//            Thread.sleep(1000 * 10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
		System.err.println("get data");
        return this.name + ":" + Thread.currentThread().getName();
	}
	
	
    @Override
    protected String getFallback() {
        return " ß∞‹¡À ";
    }
    
    
    @Override
    protected String getCacheKey() {
    	return String.valueOf(this.name);
    }
    
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		String result = new ClearCacheHystrixCommand("zhangsan").execute();
		System.out.println(result);
		ClearCacheHystrixCommand.flushCache("zhangsan");
		Future<String> future = new ClearCacheHystrixCommand("zhangsan").queue();
		System.out.println(future.get());
	}
}