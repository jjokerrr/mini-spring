package org.myspringframework.aop.springframework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.myspringframework.aop.BeforeAdvice;

/**
 * @author zhuangzhihao
 * created 2025/2/2
 **/
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private BeforeAdvice advice;

    public MethodBeforeAdviceInterceptor(BeforeAdvice advice) {
        this.advice = advice;
    }

    public MethodBeforeAdviceInterceptor() {
    }

    public void setAdvice(BeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        advice.before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return invocation.proceed();
    }
}
