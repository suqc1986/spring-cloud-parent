package com.biancheng.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.ribbon.RibbonHttpResponse;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.alibaba.fastjson.util.IOUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PostFilter extends ZuulFilter {

	private Logger log = LoggerFactory.getLogger(PostFilter.class);

	@Override
	public String filterType() {
		return FilterConstants.POST_TYPE;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		//处理请求数据
		HttpServletRequest req = (HttpServletRequest) RequestContext.getCurrentContext().getRequest();
		System.err.println("REQUEST:: " + req.getScheme() + " " + req.getRemoteAddr() + ":" + req.getRemotePort());
		StringBuilder params = new StringBuilder("?");
		// 获取URL参数
		Enumeration<String> names = req.getParameterNames();
		if (req.getMethod().equals("GET")) {
		    while (names.hasMoreElements()) {
		        String name = (String) names.nextElement();
		        params.append(name);
		        params.append("=");
		        params.append(req.getParameter(name));
		        params.append("&");
		    }
		}
		if (params.length() > 0) {
		    params.delete(params.length() - 1, params.length());
		}
		System.err.println(
		        "REQUEST:: > " + req.getMethod() + " " + req.getRequestURI() + params + " " + req.getProtocol());
		Enumeration<String> headers = req.getHeaderNames();
		while (headers.hasMoreElements()) {
		    String name = (String) headers.nextElement();
		    String value = req.getHeader(name);
		    System.err.println("REQUEST:: > " + name + ":" + value);
		}
		final RequestContext ctx = RequestContext.getCurrentContext();
		// 获取请求体参数
		if (!ctx.isChunkedRequestBody()) {
		    ServletInputStream inp = null;
		    try {
		        inp = ctx.getRequest().getInputStream();
		        String body = null;
		        if (inp != null) {
		            body = new BufferedReader(new InputStreamReader(inp)).readLine();
		            System.err.println("REQUEST:: > " + body);
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		//-------------------------------------------------------------
		//处理响应数据
		//方式一
		try {
		    Object zuulResponse = RequestContext.getCurrentContext().get("zuulResponse");
		    if (zuulResponse != null) {
		        RibbonHttpResponse resp = (RibbonHttpResponse) zuulResponse;
		        String body = new BufferedReader(new InputStreamReader(resp.getBody())).readLine();
		        System.err.println("RESPONSE:: > " + body);
		        resp.close();
		        RequestContext.getCurrentContext().setResponseBody(body);
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
		//方式二
//		InputStream stream = RequestContext.getCurrentContext().getResponseDataStream();
//	    try {
//	        if (stream != null) {
//	            String body = new BufferedReader(new InputStreamReader(stream)).readLine();
//	            System.err.println("RESPONSE:: > " + body);
//	            RequestContext.getCurrentContext().setResponseBody(body);
//	        }
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
		
		
		
		
		return null;
	}
}
