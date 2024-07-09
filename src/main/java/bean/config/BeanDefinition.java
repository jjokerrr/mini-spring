package bean.config;

import bean.support.PropertyValues;

/**
 * 定义Bean信息的类，其中包含了Bean的class，构造方法，属性等信息，每一个bean对象都对应了一个BeanDefination实例
 */
public class BeanDefinition {
    private Class beanClass;

    private PropertyValues propertyValues;


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
}
