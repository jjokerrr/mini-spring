package org.myspringframework.common;


import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.PropertyValue;
import org.myspringframework.beans.factory.ConfigurableListableBeanFactory;
import org.myspringframework.beans.factory.config.BeanDefinition;
import org.myspringframework.beans.factory.config.BeanFactoryPostProcessor;

public class CustomBeanFactoryProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        BeanDefinition person = beanFactory.getBeanDefinition("person");
        PropertyValue propertyValue = new PropertyValue("name", "ivy");
        person.getPropertyValues().addPropertyValue(propertyValue);
    }
}
