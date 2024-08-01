package org.myspringframework;


import org.junit.Test;
import org.myspringframework.beans.Car;
import org.myspringframework.beans.Person;
import org.myspringframework.beans.factory.support.DefaultListableBeanFactory;
import org.myspringframework.beans.factory.xml.XmlBeanDefinitionReader;

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
