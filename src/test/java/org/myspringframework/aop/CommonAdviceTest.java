package org.myspringframework.aop;

import org.junit.Before;
import org.junit.Test;
import org.myspringframework.aop.aspectj.AspectJExpressionPointcut;
import org.myspringframework.aop.springframework.ProxyFactory;
import org.myspringframework.service.WorldService;
import org.myspringframework.service.WorldServiceImpl;
import org.myspringframework.service.aop.WorkServiceAfterReturningService;
import org.myspringframework.service.aop.WorkServiceAfterService;
import org.myspringframework.service.aop.WorkServiceBeforeService;

/**
 * @author zhuangzhihao
 * created 2025/1/28
 **/
public class CommonAdviceTest {

    public static AdvisedSupport advisedSupport;

    @Before
    public void setup() {
        WorldService worldService = new WorldServiceImpl();

        advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.myspringframework.service.WorldService.explode(..))").getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodMatcher(methodMatcher);
        advisedSupport.setProxyTargetClass(true);
    }

    @Test
    public void testBeforeAdvice() {
        WorkServiceBeforeService workServiceBeforeService = new WorkServiceBeforeService();
        WorkServiceAfterService workServiceAfterService = new WorkServiceAfterService();
        WorkServiceAfterReturningService workServiceAfterReturningService = new WorkServiceAfterReturningService();
        GenericInterceptor genericInterceptor = new GenericInterceptor();
        genericInterceptor.setBeforeAdvice(workServiceBeforeService);
        genericInterceptor.setAfterAdvice(workServiceAfterService);
        genericInterceptor.setAfterReturningAdvice(workServiceAfterReturningService);
        advisedSupport.setMethodInterceptor(genericInterceptor);
        ProxyFactory proxyFactory = new ProxyFactory(advisedSupport);
        WorldService proxy = (WorldService) proxyFactory.getProxy();
        proxy.explode();

    }
}
