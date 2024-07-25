package org.myspringframework.common;


import org.myspringframework.bean.ConfigurableListableBeanFactory;
import org.myspringframework.bean.config.BeanDefinition;
import org.myspringframework.bean.config.BeanFactoryPostProcessor;
import org.myspringframework.bean.exception.BeanException;
import org.myspringframework.bean.support.PropertyValue;

public class CustomBeanFactoryProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        BeanDefinition person = beanFactory.getBeanDefinition("person");
        PropertyValue propertyValue = new PropertyValue("name", "ivy");
        person.getPropertyValues().addPropertyValue(propertyValue);
    }
}
