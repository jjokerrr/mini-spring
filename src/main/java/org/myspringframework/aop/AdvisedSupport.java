package org.myspringframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author zhuangzhihao
 * created 2025/1/25
 **/
public class AdvisedSupport {
    /**
     * 代理对象
     */
    private TargetSource targetSource;

    /**
     * 切点匹配
     */
    private MethodMatcher methodMatcher;

    /**
     * 是否使用cglib代理
     */
    private boolean proxyTargetClass = false;

    /**
     * 方法增强
     */
    private MethodInterceptor methodInterceptor;

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}
