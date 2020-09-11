package com.biancheng.hystrix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

public class MyHystrixCollapser extends HystrixCollapser<List<String>, String, String> {
	private final String name;

	public MyHystrixCollapser(String name) {
		this.name = name;
	}

	@Override
	public String getRequestArgument() {
		return name;
	}

	@Override
	protected HystrixCommand<List<String>> createCommand(final Collection<CollapsedRequest<String, String>> requests) {
		return new BatchCommand(requests);
	}

	@Override
	protected void mapResponseToRequests(List<String> batchResponse,
			Collection<CollapsedRequest<String, String>> requests) {
		int count = 0;
		for (CollapsedRequest<String, String> request : requests) {
			request.setResponse(batchResponse.get(count++));
		}
	}

	private static final class BatchCommand extends HystrixCommand<List<String>> {
		private final Collection<CollapsedRequest<String, String>> requests;

		private BatchCommand(Collection<CollapsedRequest<String, String>> requests) {
			super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
					.andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey")));
			this.requests = requests;
		}

		@Override
		protected List<String> run() {
			System.out.println(" 真正执行请求......");
			ArrayList<String> response = new ArrayList<String>();
			for (CollapsedRequest<String, String> request : requests) {
				response.add(" 返回结果 : " + request.getArgument());
			}
			return response;
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
	    HystrixRequestContext context = HystrixRequestContext.initializeContext();
	    Future<String> f1 = new MyHystrixCollapser("zhangsan").queue();
	    Future<String> f2 = new MyHystrixCollapser("zhangsan333").queue();
	    System.out.println(f1.get() + "=" + f2.get());
	    context.shutdown();
	}
}