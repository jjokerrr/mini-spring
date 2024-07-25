package org.myspringframework;


import org.junit.Test;
import org.myspringframework.bean.Car;
import org.myspringframework.bean.Person;
import org.myspringframework.bean.support.DefaultListableBeanFactory;
import org.myspringframework.bean.xml.XmlBeanDefinitionReader;

public class XmlDefineBeanTest {
    @Test
    public void testXmlDefineBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinition("classpath:spring.xml");

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);


        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
    }
}
