package org.myspringframework.context.event;

import org.myspringframework.beans.BeanException;
import org.myspringframework.beans.factory.BeanFactory;
import org.myspringframework.context.ApplicationEvent;
import org.myspringframework.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author zhuangzhihao
 * created 2025/1/22
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
            if (supportEvent(applicationListener, event)) {
                applicationListener.onApplicationEvent(event);
            }
        }
    }

    // todo: 重新看一下这块的代码
    private boolean supportEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Type type = applicationListener.getClass().getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeanException("wrong event class name: " + className);
        }
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
