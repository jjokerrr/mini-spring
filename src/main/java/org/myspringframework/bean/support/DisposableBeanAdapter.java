package org.myspringframework.bean.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.myspringframework.bean.DisposableBean;
import org.myspringframework.bean.exception.BeanException;

import java.lang.reflect.Method;

/**
 * DisposableBean的适配器模式，将全部实现了destroy-method的类适配成DisposableBean
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String name;

    private final String destroyMethodName;


    public DisposableBeanAdapter(Object bean, String name, String destroyMethodName) {
        this.bean = bean;
        this.name = name;
        this.destroyMethodName = destroyMethodName;
    }


    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        if (StrUtil.isNotBlank(destroyMethodName) || !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))) {
            Method destroyMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
            if (destroyMethod == null) {
                throw new BeanException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + name + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
