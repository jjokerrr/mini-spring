package org.myspringframework;


import org.junit.Test;
import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.Person;
import org.myspringframework.beans.PropertyValue;
import org.myspringframework.beans.PropertyValues;
import org.myspringframework.beans.factory.config.BeanDefinition;
import org.myspringframework.beans.factory.support.DefaultListableBeanFactory;

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
