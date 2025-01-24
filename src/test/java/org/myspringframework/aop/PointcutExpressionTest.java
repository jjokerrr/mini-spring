package org.myspringframework.aop;

import org.junit.Test;
import org.myspringframework.aop.aspectj.AspectJExpressionPointcut;
import org.myspringframework.service.HelloService;

import java.lang.reflect.Method;

/**
 * @author zhuangzhihao
 * created 2025/1/24
 **/
public class PointcutExpressionTest {
    @Test
    public void testPointcutExpression() {
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut("execution(* org.myspringframework.service.HelloService.*(..))");
        Class<HelloService> cls = HelloService.class;
        assert aspectJExpressionPointcut.matches(cls);
    }

    @Test
    public void testPointcutMethodExpression() throws NoSuchMethodException {
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut("execution(* org.myspringframework.service.HelloService.*(..))");
        Class<HelloService> cls = HelloService.class;
        Method sayHello = cls.getDeclaredMethod("sayHello");
        assert aspectJExpressionPointcut.matches(sayHello, cls);
    }
}
