package org.myspringframework;

import org.junit.Test;
import org.myspringframework.context.support.ClassPathXmlApplicationContext;
import org.myspringframework.event.CustomEvent;

/**
 * @author zhuangzhihao
 * created 2025/1/22
 **/
public class EventAndEventListenerTest {
    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event-and-event-publisher.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext));
        applicationContext.close();
    }
}
