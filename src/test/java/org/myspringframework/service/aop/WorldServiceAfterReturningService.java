package org.myspringframework.service.aop;

import org.myspringframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author zhuangzhihao
 * created 2025/1/28
 **/
public class WorldServiceAfterReturningService implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) {
        System.out.println("afterReturning method:" + method);
        System.out.println("afterReturning returnValue:" + returnValue);
    }
}
