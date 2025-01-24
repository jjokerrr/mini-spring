package org.myspringframework.aop;

/**
 * @author zhuangzhihao
 * created 2025/1/24
 **/
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
