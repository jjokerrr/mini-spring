package bean.support;

import bean.config.BeanDefinition;
import bean.config.BeanReference;
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
        applyPropertyValues(name, bean, beanDefinition);
        addSingleton(name, bean);
        return bean;
    }

    /**
     * 填充Bean对象属性，对于属性值是一个Bean对象，则会递归的实例化Bean
     */
    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeanException {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        try {
            for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
                String fieldName = propertyValue.getKey();
                Object fieldValue = propertyValue.getValue();
                if (fieldValue instanceof BeanReference) {
                    fieldValue = getBean(((BeanReference) fieldValue).getBeanName());
                }
                BeanUtil.setFieldValue(bean, fieldName, fieldValue);
            }
        } catch (BeanException e) {
            throw new BeanException("Error setting property values for bean: " + beanName, e);
        }
    }

}
