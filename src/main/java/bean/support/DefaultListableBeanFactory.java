package bean.support;

import bean.config.BeanDefinition;
import bean.config.BeanDefinitionRegistry;
import bean.exception.BeanException;

import java.util.HashMap;

/**
 * BeanFactory 实现类对象，注册BeanDefinition，维护单例SingletonBeanMap，采用Lazy加载模式
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    HashMap<String, BeanDefinition> beanDefinitionHashMap = new HashMap<>();

    public void registerBeanDefinition(String name, Class cls) {
        BeanDefinition beanDefinition = new BeanDefinition(cls);
        beanDefinitionHashMap.put(name, beanDefinition);

    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionHashMap.put(name, beanDefinition);

    }



    @Override
    protected BeanDefinition getBeanDefinition(String name) throws BeanException {
        BeanDefinition beanDefinition = beanDefinitionHashMap.get(name);
        if (beanDefinition == null) {
            throw new BeanException("no bean named " + name + " is defined!");
        }
        return beanDefinition;

    }
}
