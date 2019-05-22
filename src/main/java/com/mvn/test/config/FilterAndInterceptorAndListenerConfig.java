package com.mvn.test.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mvn.test.util.filter.ReqFilter;
import com.mvn.test.util.interceptor.ReqInterceptor;
import com.mvn.test.util.listener.SessionListener;

@Configuration
public class FilterAndInterceptorAndListenerConfig extends WebMvcConfigurerAdapter {

    /**
     * 设置默认首页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:index.jsp");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
    
    /**
     * 配置静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*
         * springboot默认情况下，resources目录的resources/static/public文件下的文件，
         * 不用加resources/static/public路径可直接访问，如果加了resources/static/
         * public路径就访问不了了；
         * 但是，如果想加resources/static/public路径也能访问，需要配置以下配置；加入配置的路径，可以使用配置路径直接访问，
         * 相当于静态文件(/file/**)
         */
        // 当访问路径中包含handler中的路径的时候，resource handler就会去locations中的目录下访问了
        String[] handler = { "/META-INF/resources/**", "/resources/**", "/static/**", "/public/**", "/file/**" };
        String[] locations = { "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/", "classpath:/file/" };
        registry.addResourceHandler(handler).addResourceLocations(locations);
        super.addResourceHandlers(registry);
    }

    /**
     * 添加拦截请求和排除拦截请求
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ReqInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/login/**");
        super.addInterceptors(registry);
    }

    /**
     * 注入过滤器对象
     * 
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean(new ReqFilter());
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    /**
     * session监听
     * 
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<SessionListener> listenerRegist() {
        ServletListenerRegistrationBean<SessionListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new SessionListener());
        return registrationBean;
    }

}
