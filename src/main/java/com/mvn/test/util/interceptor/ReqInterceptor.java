package com.mvn.test.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mvn.test.service.UserService;

public class ReqInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    /**
     * 调用controller方法之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("******************** interceptor **********************");

        // 启动支持@Autowired注解
        WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
        System.out.println("userService : " + userService);

        String uri = request.getRequestURI();
        System.out.println("uri : " + uri);

        return true;
    }

}
