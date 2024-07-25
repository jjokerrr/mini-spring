package org.myspringframework;


import org.junit.Test;
import org.myspringframework.bean.DemoClass;
import org.myspringframework.bean.exception.BeanException;
import org.myspringframework.bean.support.DefaultListableBeanFactory;

public class BeanDefinitionRegistryAndBeanFactoryTest {

    @Test
    public void testBeanFactory() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("hello", DemoClass.class);

        DemoClass bean =(DemoClass) beanFactory.getBean("hello");
        System.out.println(bean.getMsg());

    }
}
