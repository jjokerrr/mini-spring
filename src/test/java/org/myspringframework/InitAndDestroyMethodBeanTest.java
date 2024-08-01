package org.myspringframework;

import org.junit.Test;
import org.myspringframework.beans.Person;
import org.myspringframework.context.support.ClassPathXmlApplicationContext;

public class InitAndDestroyMethodBeanTest {
    @Test
    public void testInitAndDestroyByClose() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
        applicationContext.close();
    }

    @Test
    public void testInitAndDestroyByHook() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        applicationContext.registerShutdownHook();
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
//        applicationContext.close();
    }
}
