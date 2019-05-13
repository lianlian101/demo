package com.mvn.test.util.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mvn.test.util.RequestParamUtil;

/**
 * 
 * 创建时间: 2019年5月13日 创建人 zhb 说明: 切面拦截具体实现
 *
 */
@Aspect
@Component
public class AspectPoint {
    
    private static final Logger log = LoggerFactory.getLogger(AspectPoint.class);
    
    @Autowired
    private RequestParamUtil requestParamUtil;
    
    // 切点
    @Pointcut("@annotation(com.mvn.test.util.aop.AspectLog)")
    public void aspectLog() {}
  
    /**
     * 前置通知
     * @param joinPoint 切点
     */
    @Before("aspectLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            log.debug(">>>>>>>>>>>>前置通知开始>>>>>>>>>>>>");
            Map<String, Object> map = requestParamUtil.getKeyValue();
            System.out.println("参数值：" + map);
            System.out.println("注释内容：" + getAnnotationData(joinPoint));
            log.debug("<<<<<<<<<<<<前置通知结束<<<<<<<<<<<<");
        } catch (Exception e) {
            log.error("前置通知异常:", e);
        }
        
    }

    /**
     * 返回通知
     * @param joinPoint
     * @param retVal
     */
    /*@AfterReturning(value = "aspectLog()",argNames = "retVal",returning = "retVal")  
    public void logInfo(JoinPoint joinPoint,Object retVal) {  
        System.out.println("=========后置通知开始=======");
        System.out.println(retVal);
        System.out.println("=========后置通知结束=======");
    } */ 
    
    /**
     * 环绕通知（Around advice）
     * 
     * @param joinPoint
     */
    /*@Around("aspectLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("=====环绕通知开始=====");
        Object obj = joinPoint.proceed();
        System.out.println(obj);
        System.out.println("=====环绕通知结束=====");
        return obj;
    }*/
    
    /**
     * 异常通知
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(pointcut = "aspectLog()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        try {
            log.debug("！！！！！！！！！！！！！！！异常通知开始！！！！！！！！！！！！！！！");
            String value = getAnnotation(joinPoint).value();
            log.debug(value);
            log.error("异常：" + e.getMessage());
            log.debug("！！！！！！！！！！！！！！！异常通知结束！！！！！！！！！！！！！！！");
        } catch (Exception ex) {
            log.error("异常通知异常",ex);
        }

    }

    /**
     * 获取注解中定义的数据
     * 
     * @param joinPoint
     * @return  map
     * @throws Exception
     */
    public static Map<Object,Object> getAnnotationData(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        Map<Object,Object> map = new HashMap<Object,Object>();
        String value = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                @SuppressWarnings("rawtypes")
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    value = method.getAnnotation(AspectLog.class).value();
                    map.put("value", value);
                    break;
                }
            }
        }
        return map;
    }
    
    /**
     * 获得注解
     *          
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private static AspectLog getAnnotation(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(AspectLog.class);
        }
        return null;
    }

}
