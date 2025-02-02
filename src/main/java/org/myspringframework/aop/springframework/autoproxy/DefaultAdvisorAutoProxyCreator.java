package org.myspringframework.aop.springframework.autoproxy;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.myspringframework.aop.AdvisedSupport;
import org.myspringframework.aop.Advisor;
import org.myspringframework.aop.Pointcut;
import org.myspringframework.aop.TargetSource;
import org.myspringframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.myspringframework.aop.springframework.ProxyFactory;
import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.factory.BeanFactory;
import org.myspringframework.beans.factory.BeanFactoryAware;
import org.myspringframework.beans.factory.config.BeanDefinition;
import org.myspringframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.myspringframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.Map;

/**
 * @author zhuangzhihao
 * created 2025/2/2
 **/
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }


    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException {
        // 对于普通的bean对象，在创建bean的时候会检查是否进入代理，进入创建Advisor的环节。如果advisor创建死循环的情况会导致全部的bean都死循环
        // 为了避免这个问题，AspectJExpressionPointcutAdvisor在postProcessBeforeInstantiation应该直接返回null，使其进入普通的bean创建流程中
        if (isInfrastructureClass(beanClass)) {
            // 返回null 会进入正常的bean创建流程中
            return null;
        }

        // 检查代理Bean，创建对应的代理bean对象

        Map<String, AspectJExpressionPointcutAdvisor> proxyBeans = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor : proxyBeans.values()) {
            // 创建代理对象
            if (!advisor.getPointcut().getClassFilter().matches(beanClass)) {
                continue;
            }
            // 构建ProxyFactory
            AdvisedSupport advisedSupport = new AdvisedSupport();
            // 创建对象的beansource,注意不要将生成的bean对象放到缓存中
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            Object bean = beanFactory.getInstantiationStrategy().instantiate(beanDefinition);
            TargetSource targetSource = new TargetSource(bean);
            advisedSupport.setTargetSource(targetSource);
            // MethodInterceptor 是 Advice的子类
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advisor.class.isAssignableFrom(beanClass)
                || Advice.class.isAssignableFrom(beanClass)
                || Pointcut.class.isAssignableFrom(beanClass);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
