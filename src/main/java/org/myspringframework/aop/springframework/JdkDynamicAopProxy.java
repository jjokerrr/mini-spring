package org.myspringframework.aop.springframework;

import org.aopalliance.intercept.MethodInterceptor;
import org.myspringframework.aop.AdviceSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhuangzhihao
 * created 2025/1/25
 **/
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdviceSupport advised;

    public JdkDynamicAopProxy(AdviceSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(this.advised.getTargetSource().getTarget(), method, args));
        }
        return method.invoke(advised.getTargetSource().getTarget(), args);
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(this.advised.getClass().getClassLoader(),
                this.advised.getTargetSource().getTargetClass(), this);
    }
}
