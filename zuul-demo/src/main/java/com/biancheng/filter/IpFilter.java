package com.biancheng.filter;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.biancheng.pojo.ResponseCode;
import com.biancheng.pojo.ResponseData;
import com.biancheng.util.IpUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class IpFilter extends  ZuulFilter{
    // IP黑名单列表
    private List<String> blackIpList = Arrays.asList("127.0.0.1");
    public IpFilter() {
        super();
    }
    @Override
    public boolean shouldFilter() {
        return true;
    }
    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 1;
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("msg", "向下一个过滤器携带数据，通过order排序");
        
//        ctx.get("msg");
        String ip = IpUtils.getIpAddress(ctx.getRequest());
        // 在黑名单中禁用
        if (StringUtils.isNotBlank(ip) && blackIpList.contains(ip)) {
        	//本地controller 还可以继续执行，后台其他服务的contrller 不会在调用了
            ctx.setSendZuulResponse(false);
            //针对拦截本地转发的请求
            ctx.set("sendForwardFilter.ran",true);
            ctx.set("isSuccess",false);
            ResponseData data = ResponseData.fail("非法请求 ", ResponseCode.NO_AUTH_CODE.getCode());
            ctx.setResponseBody(JSONObject.toJSONString(data));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            int a = 1/0;
            return null;
        }
        return null;
    }
}
