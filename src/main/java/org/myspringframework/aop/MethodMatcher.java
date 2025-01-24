package org.myspringframework.aop;

import java.lang.reflect.Method;

/**
 * @author zhuangzhihao
 * created 2025/1/24
 **/
public interface MethodMatcher {
    boolean matches(Method method, Class<?> targetClass);
}
