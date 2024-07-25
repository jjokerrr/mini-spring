package org.myspringframework;


import org.junit.Test;
import org.myspringframework.bean.Person;
import org.myspringframework.bean.config.BeanDefinition;
import org.myspringframework.bean.exception.BeanException;
import org.myspringframework.bean.support.DefaultListableBeanFactory;
import org.myspringframework.bean.support.PropertyValue;
import org.myspringframework.bean.support.PropertyValues;

public class PopulateBeanWithPropertiesTest {
    @Test
    public void testCreateBean() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        PropertyValue nameProperty = new PropertyValue("name", "zhangsan");
        PropertyValue ageProperty = new PropertyValue("age", 18);
        propertyValues.addPropertyValue(nameProperty);
        propertyValues.addPropertyValue(ageProperty);
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);
        beanFactory.registerBeanDefinition("person", beanDefinition);
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);

    }
}
