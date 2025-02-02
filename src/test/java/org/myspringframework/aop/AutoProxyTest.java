package org.myspringframework.aop;

import org.junit.Test;
import org.myspringframework.context.support.ClassPathXmlApplicationContext;
import org.myspringframework.service.WorldService;

/**
 * @author zhuangzhihao
 * created 2025/2/2
 **/
public class AutoProxyTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");
        WorldService worldService = context.getBean("worldService", WorldService.class);
        worldService.explode();
    }
}
