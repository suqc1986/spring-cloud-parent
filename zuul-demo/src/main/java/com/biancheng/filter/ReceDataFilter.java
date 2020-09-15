package com.biancheng.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class ReceDataFilter extends ZuulFilter{
	
	@Override
	public boolean shouldFilter() {
	    RequestContext ctx = RequestContext.getCurrentContext();
	    Object success = ctx.get("isSuccess");
	    return success == null ? true : Boolean.parseBoolean(success.toString());
	}
	
	@Override
	public Object run() throws ZuulException {
		System.out.println("-----------ReceDataFilter-----------");
		return null;
	}

	@Override
	public String filterType() {
		 return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 2;
	}

}
