package org.myspringframework.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author zhuangzhihao
 * created 2025/1/25
 **/
public class WorldServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("WorldServiceInterceptor before invoke");
        Object result = invocation.proceed();
        System.out.println("WorldServiceInterceptor after invoke");
        return result;
    }
}
