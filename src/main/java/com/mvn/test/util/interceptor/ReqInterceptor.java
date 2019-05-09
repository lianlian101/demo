package com.mvn.test.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * 创建时间: 2019年5月8日 创建人 zhb 说明: 拦截器
 *
 */
public class ReqInterceptor extends HandlerInterceptorAdapter {
    
    private Logger log = LoggerFactory.getLogger(ReqInterceptor.class);

    // @Autowired
    // private UserService userService;

    /**
     * 调用controller方法之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("******************** interceptor **********************");

        // 启动支持@Autowired注解
        WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
        //System.out.println("userService : " + userService);

        String uri = request.getRequestURI();
        log.debug("请求的地址  --> {}", uri);

        return true;
    }

}
