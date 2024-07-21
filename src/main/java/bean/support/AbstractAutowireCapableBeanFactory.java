package bean.support;

import bean.config.AutowireCapableBeanFactory;
import bean.config.BeanDefinition;
import bean.config.BeanPostProcessor;
import bean.config.BeanReference;
import bean.exception.BeanException;
import cn.hutool.core.bean.BeanUtil;


public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition) throws BeanException {
        return doCreateBean(name, beanDefinition);

    }

    /**
     * 创建Bean对象，填充Bean对象属性
     */
    private Object doCreateBean(String name, BeanDefinition beanDefinition) throws BeanException {
        // 实例化对象
        Object bean = createBeanInstance(beanDefinition);

        // 属性填充
        applyPropertyValues(name, bean, beanDefinition);

        // 初始化
        bean = initializeBean(bean, name, beanDefinition);
        // 添加实例化列表
        addSingleton(name, bean);
        return bean;
    }

    private Object initializeBean(Object bean, String name, BeanDefinition beanDefinition) {
        // 初始化前
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, name);
        // 初始化
        //TODO 后面会在此处执行bean的初始化方法
        invokeInitMethods(name, wrappedBean, beanDefinition);
        // 初始化后
        return applyBeanPostProcessorsAfterInitialization(wrappedBean, name);
    }

    /**
     * bean对象的初始化方法
     */
    private void invokeInitMethods(String name, Object wrappedBean, BeanDefinition beanDefinition) {
        System.out.println("execute the " + name + " init method ");
    }


    /**
     * Bean对象初始化之前执行
     */
    private Object applyBeanPostProcessorsBeforeInitialization(Object bean, String name) {
        // 赋值result的原因保证每一个处理链都能链式执行
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            result = beanPostProcessor.postProcessBeforeInitialization(result, name);
            if (result == null) {
                // 如果任何一个处理链出现异常，直接返回原对象
                return bean;
            }
        }
        return result;
    }

    private Object applyBeanPostProcessorsAfterInitialization(Object wrappedBean, String name) {
        // 赋值result的原因保证每一个处理链都能链式执行
        Object result = wrappedBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            result = beanPostProcessor.postProcessAfterInitialization(result, name);
            if (result == null) {
                // 如果任何一个处理链出现异常，直接返回原对象
                return wrappedBean;
            }
        }
        return result;
    }

    /**
     * 实例化Bean对象
     */
    private Object createBeanInstance(BeanDefinition beanDefinition) throws BeanException {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    /**
     * 注入Bean对象属性
     * 对于属性值是一个Bean对象，则会递归的实例化Bean
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

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}
