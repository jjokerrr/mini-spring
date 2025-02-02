package org.myspringframework.aop;

import org.junit.Test;
import org.myspringframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.myspringframework.aop.springframework.ProxyFactory;
import org.myspringframework.service.WorldService;
import org.myspringframework.service.WorldServiceImpl;
import org.myspringframework.service.aop.WorkServiceBeforeService;

/**
 * @author zhuangzhihao
 * created 2025/1/30
 **/
public class PointCutAdvisorTest {
    @Test
    public void testAdvisor() {
        // 创建pointcut表达式
        String expression = "execution(* org.myspringframework.service.WorldService.*(..))";
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(expression);
        // 创建切面
        BeforeAdvice advice = new WorkServiceBeforeService();
        advisor.setAdvice(advice);

        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(new WorldServiceImpl());
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setProxyTargetClass(true);
        advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
        GenericInterceptor interceptor = new GenericInterceptor();
        interceptor.setBeforeAdvice(advice);
        advisedSupport.setMethodInterceptor(interceptor);
        // 代理对象
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }
}
