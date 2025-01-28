package org.myspringframework.service.aop;

import org.myspringframework.aop.BeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author zhuangzhihao
 * created 2025/1/28
 **/
public class WorkServiceBeforeService implements BeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("before method");
    }
}
