package com.tingyun.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Created by tingyun on 2017/6/26.
 */
@Aspect
@Component
@Order(-5)
public class WebLogAspect {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    /**
     * 定义一个切入点.
     * 解释下：
     *
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */

    @Pointcut("execution(public * com.*.controller..*.*(..))")
    //@Pointcut("execution(public * com.tingyun.*.web..*.*(..))")
    public void weblog(){

    }

    @Before("weblog()")
    public void doBefore(JoinPoint joinPoint){
        // 接收到请求，记录请求内容
        logger.info("WebLogAspect.doBefore()");
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=requestAttributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取所有的参数
        Enumeration<String> enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.err.println(paraName+": "+request.getParameter(paraName));
        }
        System.err.println("WebLogAspect.doBefore()");
    }

    @After("weblog()")
    public void doAfter(){
        // 处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()");
    }

    /*@Around("weblog()")
    public void doAAround(){
        // 处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()");
        System.err.println("WebLogAspect.doAround()");
    }*/

}
