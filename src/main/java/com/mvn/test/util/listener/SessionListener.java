package com.mvn.test.util.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 
 * 创建时间: 2019年5月8日 创建人 zhb 说明: session监听
 *
 */
public class SessionListener implements HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 需要session.setAttribute("","")方法，才能创建session
        System.out.println("################# 创建session ################");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("#################### 销毁session ###############");
    }

}
