package org.myspringframework.aop;

import org.junit.Test;
import org.myspringframework.aop.aspectj.AspectJExpressionPointcut;
import org.myspringframework.aop.springframework.CglibDynamicAopProxy;
import org.myspringframework.common.WorldServiceInterceptor;
import org.myspringframework.service.WorldService;
import org.myspringframework.service.WorldServiceImpl;

/**
 * @author zhuangzhihao
 * created 2025/1/25
 **/
public class CglibDynamicAopProxyTest {
    @Test
    public void testJdkDynamicAopProxy() {
        AdvisedSupport advisedSupport = new AdvisedSupport();
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut("execution(* org.myspringframework.service.WorldService.*(..))");
        WorldService worldService = new WorldServiceImpl();
        WorldServiceInterceptor worldServiceInterceptor = new WorldServiceInterceptor();
        advisedSupport.setMethodMatcher(aspectJExpressionPointcut);
        advisedSupport.setTargetSource(new TargetSource(worldService));
        advisedSupport.setMethodInterceptor(worldServiceInterceptor);
        WorldService proxy = (WorldService) new CglibDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }
}
