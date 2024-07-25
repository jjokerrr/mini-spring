package org.myspringframework.bean.support;


import org.myspringframework.bean.config.BeanDefinitionRegistry;
import org.myspringframework.io.Resource;
import org.myspringframework.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getBeanDefinitionRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinition(String path);

    void loadBeanDefinition(Resource resource);

    void loadBeanDefinition(String[] paths);
}
