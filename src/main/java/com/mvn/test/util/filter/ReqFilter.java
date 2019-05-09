package com.mvn.test.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 创建时间: 2019年5月8日 创建人 zhb 说明: 过滤器
 *
 */
public class ReqFilter implements Filter{
    
    private Logger log = LoggerFactory.getLogger(ReqFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("==================过滤器==================");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
       
    }

}
