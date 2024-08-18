package org.myspringframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.PropertyValue;
import org.myspringframework.beans.PropertyValues;
import org.myspringframework.beans.factory.BeanFactoryAware;
import org.myspringframework.beans.factory.DisposableBean;
import org.myspringframework.beans.factory.InitializingBean;
import org.myspringframework.beans.factory.config.AutowireCapableBeanFactory;
import org.myspringframework.beans.factory.config.BeanDefinition;
import org.myspringframework.beans.factory.config.BeanPostProcessor;
import org.myspringframework.beans.factory.config.BeanReference;

import java.lang.reflect.Method;


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

        // 注册注销方法
        registerDisposableBeanIfNecessary(name, bean, beanDefinition);
        // 添加实例化列表
        addSingleton(name, bean);
        return bean;
    }

    /**
     * 注册disposeBean
     */
    private void registerDisposableBeanIfNecessary(String name, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotBlank(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(name, new DisposableBeanAdapter(bean, name, beanDefinition.getDestroyMethodName()));
        }
    }

    private Object initializeBean(Object bean, String name, BeanDefinition beanDefinition) {
        if(bean instanceof BeanFactoryAware){
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }


        // 初始化前
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, name);
        // 初始化
        try {
            invokeInitMethods(name, wrappedBean, beanDefinition);
        } catch (Exception ex) {
            throw new BeanException("Invocation of init method of bean[" + name + "] failed", ex);
        }
        // 初始化后
        return applyBeanPostProcessorsAfterInitialization(wrappedBean, name);
    }

    /**
     * bean对象的初始化方法，执行初始化方法存在三种形式
     * 1. Bean继承InitializingBean对象，初始化阶段执行其中的afterPropertiesSet方法进行初始化
     * 2. bean对象在声明过程中直接指明初始化方法，在初始化时执行
     * 3. 在Bean的类中对应方法添加注解（后续实现）
     * 同理，Bean的销毁方法也是这三个流程
     */
    private void invokeInitMethods(String name, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 检查InitializingBean
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
        // 检查是否注册init方法
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotBlank(initMethodName) && !(bean instanceof InitializingBean && "afterPropertiesSet".equals(initMethodName))) {
            Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethodName);
            // 需要检查initMethod是否存在
            if (initMethod == null) {
                throw new BeanException("could not find the init-method named " + initMethodName + " on the bean with name " + name);
            }
            initMethod.invoke(bean);
        }
    }


    /**
     * Bean对象初始化之前执行
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object bean, String name) {
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

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object wrappedBean, String name) {
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
