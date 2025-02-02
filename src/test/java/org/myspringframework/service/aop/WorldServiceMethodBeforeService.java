package org.myspringframework.service.aop;

import org.myspringframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author zhuangzhihao
 * created 2025/2/2
 **/
public class WorldServiceMethodBeforeService implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before method:" + method.getName());
    }
}
