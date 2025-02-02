package org.myspringframework.service.aop;

import org.myspringframework.aop.AfterAdvice;

import java.lang.reflect.Method;

/**
 * @author zhuangzhihao
 * created 2025/1/28
 **/
public class WorldServiceAfterService implements AfterAdvice {
    @Override
    public void after(Method method, Object[] args, Object target) {
        System.out.println("after method");
    }
}
