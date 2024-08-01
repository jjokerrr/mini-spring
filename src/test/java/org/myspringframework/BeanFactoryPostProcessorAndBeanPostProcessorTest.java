package org.myspringframework;


import org.junit.Test;
import org.myspringframework.beans.Car;
import org.myspringframework.beans.Person;
import org.myspringframework.beans.factory.support.DefaultListableBeanFactory;
import org.myspringframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.myspringframework.common.CustomBeanFactoryProcessor;
import org.myspringframework.common.CustomBeanPostProcessor;

public class BeanFactoryPostProcessorAndBeanPostProcessorTest {
    @Test
    public void testBeanFactoryPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinition("classpath:spring.xml");

        CustomBeanFactoryProcessor beanFactoryProcessor = new CustomBeanFactoryProcessor();
        beanFactoryProcessor.postProcessBeanFactory(beanFactory);

        Person person = beanFactory.getBean("person", Person.class);
        System.out.println(person);

    }

    @Test
    public void testBeanPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinition("classpath:spring.xml");


        CustomBeanPostProcessor customBeanPostProcessor = new CustomBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customBeanPostProcessor);

        Car car = beanFactory.getBean("car", Car.class);
        System.out.println(car);
    }
}
