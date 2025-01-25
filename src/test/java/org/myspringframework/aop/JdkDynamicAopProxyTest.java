package org.myspringframework.aop;

import org.junit.Test;
import org.myspringframework.aop.aspectj.AspectJExpressionPointcut;
import org.myspringframework.aop.springframework.JdkDynamicAopProxy;
import org.myspringframework.common.WorldServiceInterceptor;
import org.myspringframework.service.WorldService;
import org.myspringframework.service.WorldServiceImpl;

/**
 * @author zhuangzhihao
 * created 2025/1/25
 **/
public class JdkDynamicAopProxyTest {
    @Test
    public void testJdkDynamicAopProxy() {
        AdviceSupport adviceSupport = new AdviceSupport();
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut("execution(* org.myspringframework.service.WorldService.*(..))");
        WorldService worldService = new WorldServiceImpl();
        WorldServiceInterceptor worldServiceInterceptor = new WorldServiceInterceptor();
        adviceSupport.setMethodMatcher(aspectJExpressionPointcut);
        adviceSupport.setTargetSource(new TargetSource(worldService));
        adviceSupport.setMethodInterceptor(worldServiceInterceptor);
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(adviceSupport).getProxy();
        proxy.explode();

    }
}
