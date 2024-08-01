package org.myspringframework;


import org.junit.Test;
import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.DemoClass;
import org.myspringframework.beans.factory.support.DefaultListableBeanFactory;

public class BeanDefinitionRegistryAndBeanFactoryTest {

    @Test
    public void testBeanFactory() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("hello", DemoClass.class);

        DemoClass bean =(DemoClass) beanFactory.getBean("hello");
        System.out.println(bean.getMsg());

    }
}
