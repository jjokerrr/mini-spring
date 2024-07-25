package org.myspringframework.context.support;


import org.myspringframework.bean.exception.BeanException;
import org.myspringframework.bean.support.DefaultListableBeanFactory;
import org.myspringframework.bean.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    /**
     * 从配置文件中加载全部的BeanDefinition
     */
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeanException {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] locations = getConfigLocations();
        if (locations != null) {
            beanDefinitionReader.loadBeanDefinition(locations);
        }
    }

    /**
     * 加载全部可能的配置路径
     */
    protected abstract String[] getConfigLocations();
}
