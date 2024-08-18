package org.myspringframework;

import org.junit.Test;
import org.myspringframework.context.support.ClassPathXmlApplicationContext;
import org.myspringframework.service.HelloService;

public class ApplicationContextAwareAndBeanFactoryAwareTest {
    @Test
    public void testApplicationContextAwareAndBeanFactoryAware(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
        assert helloService.getBeanFactory() != null;
        assert helloService.getApplicationContext() != null;
    }
}
