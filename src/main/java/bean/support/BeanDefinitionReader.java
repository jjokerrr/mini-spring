package bean.support;


import bean.config.BeanDefinitionRegistry;
import core.io.Resource;
import core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getBeanDefinitionRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinition(String path);

    void loadBeanDefinition(Resource resource);

    void loadBeanDefinition(String[] paths);
}
