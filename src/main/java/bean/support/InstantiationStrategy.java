package bean.support;

import bean.config.BeanDefinition;
import bean.exception.BeanException;

/**
 * Bean对象实例化策略
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeanException;
}
