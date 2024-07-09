package bean.support;

import bean.config.BeanDefinition;
import bean.exception.BeanException;
import cn.hutool.core.bean.BeanUtil;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) throws BeanException {
        return doCreateBean(name, beanDefinition);

    }

    /**
     * 创建Bean对象，填充Bean对象属性
     */
    private Object doCreateBean(String name, BeanDefinition beanDefinition) throws BeanException {
        Object bean = instantiationStrategy.instantiate(beanDefinition);
        applyPropertyValues(bean, beanDefinition);
        addSingleton(name, bean);
        return bean;
    }

    /**
     * 填充Bean对象属性
     */
    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
            String fieldName = propertyValue.getKey();
            Object fieldValue = propertyValue.getValue();
            BeanUtil.setFieldValue(bean, fieldName, fieldValue);
        }
    }

}
