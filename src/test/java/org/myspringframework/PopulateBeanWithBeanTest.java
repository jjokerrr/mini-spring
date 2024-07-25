package org.myspringframework;

import org.myspringframework.bean.Car;
import org.myspringframework.bean.Person;
import org.junit.Test;
import org.myspringframework.bean.config.BeanDefinition;
import org.myspringframework.bean.config.BeanReference;
import org.myspringframework.bean.exception.BeanException;
import org.myspringframework.bean.support.DefaultListableBeanFactory;
import org.myspringframework.bean.support.PropertyValue;
import org.myspringframework.bean.support.PropertyValues;

public class PopulateBeanWithBeanTest {

    @Test
    public void testPopulateBeanWithBean() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // create Car bean
        PropertyValues carProperties = new PropertyValues();
        PropertyValue brandProperty = new PropertyValue("brand", "xiaomi");
        carProperties.addPropertyValue(brandProperty);
        BeanDefinition carBeanDefinition = new BeanDefinition(Car.class, carProperties);
        beanFactory.registerBeanDefinition("car", carBeanDefinition);


        // create Person Bean
        PropertyValues personProperties = new PropertyValues();
        PropertyValue nameProperty = new PropertyValue("name", "zhangsan");
        PropertyValue ageProperty = new PropertyValue("age", 18);
        PropertyValue carProperty = new PropertyValue("car", new BeanReference("car"));
        personProperties.addPropertyValue(nameProperty);
        personProperties.addPropertyValue(ageProperty);
        personProperties.addPropertyValue(carProperty);
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, personProperties);
        beanFactory.registerBeanDefinition("person", beanDefinition);
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }
}
