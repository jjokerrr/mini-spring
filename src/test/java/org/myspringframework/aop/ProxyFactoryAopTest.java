package org.myspringframework.aop;

import org.junit.Test;
import org.myspringframework.aop.aspectj.AspectJExpressionPointcut;
import org.myspringframework.aop.springframework.ProxyFactory;
import org.myspringframework.common.WorldServiceInterceptor;
import org.myspringframework.service.WorldService;
import org.myspringframework.service.WorldServiceImpl;

/**
 * @author zhuangzhihao
 * created 2025/1/27
 **/
public class ProxyFactoryAopTest {
    @Test
    public void testProxyFactory() {
        AdvisedSupport advisedSupport = new AdvisedSupport();
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut("execution(* org.myspringframework.service.WorldService.*(..))");
        WorldService worldService = new WorldServiceImpl();
        WorldServiceInterceptor worldServiceInterceptor = new WorldServiceInterceptor();
        advisedSupport.setMethodMatcher(aspectJExpressionPointcut);
        advisedSupport.setTargetSource(new TargetSource(worldService));
        advisedSupport.setMethodInterceptor(worldServiceInterceptor);
        advisedSupport.setProxyTargetClass(true);
        ProxyFactory proxyFactory = new ProxyFactory(advisedSupport);
        WorldService proxy = (WorldService) proxyFactory.getProxy();
        proxy.explode();
    }
}
