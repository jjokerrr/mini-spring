package org.myspringframework.aop;

import java.lang.reflect.Method;

/**
 * @author zhuangzhihao
 * created 2025/2/2
 **/
public interface MethodBeforeAdvice extends BeforeAdvice {

    void before(Method method, Object[] args, Object target) throws Throwable;
}
