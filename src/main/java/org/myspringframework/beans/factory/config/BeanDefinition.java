package org.myspringframework.beans.factory.config;


import org.myspringframework.beans.PropertyValues;

/**
 * 定义Bean信息的类，其中包含了Bean的class，构造方法，属性等信息，每一个bean对象都对应了一个BeanDefination实例
 */
public class BeanDefinition {
    // 单例
    public static String SCOPE_SINGLETON = "singleton";

    // 原型
    public static String SCOPE_PROTOTYPE = "prototype";

    /**
     * 基础类对象，用于构建类示例
     */
    private Class beanClass;

    /**
     * 类属性列表，包含若干类属性值
     */
    private PropertyValues propertyValues;

    /**
     * 类初始化方法
     */
    private String initMethodName;

    /**
     * 类销毁方法
     */
    private String destroyMethodName;

    /**
     * Bean作用域
     */
    private String scope;

    private boolean singleton = true;

    private boolean prototype = false;


    public BeanDefinition(Class beanClass) {
        this(beanClass, null);
    }


    public BeanDefinition(Class beanClass, PropertyValues pv) {
        this.beanClass = beanClass;
        this.propertyValues = pv != null ? pv : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public boolean isSingleton() {
        return this.singleton;
    }

    public boolean isPrototype() {
        return this.prototype;
    }
}
