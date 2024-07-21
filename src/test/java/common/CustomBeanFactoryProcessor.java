package common;

import bean.ConfigurableListableBeanFactory;
import bean.config.BeanDefinition;
import bean.config.BeanFactoryPostProcessor;
import bean.exception.BeanException;
import bean.support.PropertyValue;

public class CustomBeanFactoryProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        BeanDefinition person = beanFactory.getBeanDefinition("person");
        PropertyValue propertyValue = new PropertyValue("name", "ivy");
        person.getPropertyValues().addPropertyValue(propertyValue);
    }
}
