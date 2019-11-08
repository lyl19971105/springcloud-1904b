package com.jk.fiter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.RequestContent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulFilter extends com.netflix.zuul.ZuulFilter {
    @Override
    public String filterType() {
        System.out.println("第一个过滤请求的类型。。" + System.currentTimeMillis());
        return "pre";
    }

    @Override
    public int filterOrder() {
        System.out.println("过滤的顺序");
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("是否要过滤");
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("业务逻辑处理");
        RequestContext requestContext =RequestContext.getCurrentContext();
        HttpServletRequest request=requestContext.getRequest();
        return null;
    }
}
