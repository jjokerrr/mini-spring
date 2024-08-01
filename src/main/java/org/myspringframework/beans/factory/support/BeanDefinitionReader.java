package org.myspringframework.beans.factory.support;


import org.myspringframework.core.io.Resource;
import org.myspringframework.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getBeanDefinitionRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinition(String path);

    void loadBeanDefinition(Resource resource);

    void loadBeanDefinition(String[] paths);
}
