package bean.support;

import bean.config.BeanDefinition;
import bean.exception.BeanException;

/**
 * 简单实例化方法
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    /**
     * 根据默认构造方法创建对象实例
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeanException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            return beanClass.newInstance();
        } catch (Exception e) {
            throw new BeanException("Fail to instantiate " + beanClass.getName(), e);
        }
    }
}
