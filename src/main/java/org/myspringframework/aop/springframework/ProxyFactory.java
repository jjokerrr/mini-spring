package org.myspringframework.aop.springframework;

import org.myspringframework.aop.AdvisedSupport;

/**
 * @author zhuangzhihao
 * created 2025/1/27
 * 代理工厂
 **/
public class ProxyFactory {
    private final AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    private AopProxy createAopProxy() {
        boolean proxyTargetClass = advisedSupport.isProxyTargetClass();
        if (proxyTargetClass) {
            return new JdkDynamicAopProxy(advisedSupport);
        }
        return new CglibDynamicAopProxy(advisedSupport);
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

}
