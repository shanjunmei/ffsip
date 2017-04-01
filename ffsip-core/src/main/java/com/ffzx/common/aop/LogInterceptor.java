package com.ffzx.common.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/3/21.
 */
public class LogInterceptor implements MethodInterceptor {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        long t = System.currentTimeMillis();

        Object ret = invocation.proceed();

        t = System.currentTimeMillis() - t;
        String methodName = getMethodName(invocation);
        logger.info("method [{}] excuete  {} ms", methodName, t);
        return ret;
    }

    /**
     * 获得方法名
     * @param invocation
     * @return
     */
    public String getMethodName(MethodInvocation invocation) {
        String methodName = invocation.getThis().toString();
        methodName = methodName.substring(0, methodName.indexOf("@"));
        methodName = methodName + "." + invocation.getMethod().getName();
        return methodName;
    }


    /**
     * mybatis Mapper 拦截
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public Object logger(ProceedingJoinPoint joinPoint) throws Throwable {
        long t = System.currentTimeMillis();
        Object ret = joinPoint.proceed();
        t = System.currentTimeMillis() - t;
        String methodName = getMapperMethodName(joinPoint);
        logger.info("method [{}] excuete  {} ms", methodName, t);
        return ret;
    }

    /**
     * 获取方法名
     * @param joinPoint
     * @return
     */
    public String getMapperMethodName(ProceedingJoinPoint joinPoint) {
        Class<?> target=joinPoint.getThis().getClass().getInterfaces()[0];
        String methodName = target.getName();

        methodName = methodName + "." + joinPoint.getSignature().getName();
        return methodName;
    }
}
