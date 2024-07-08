package bean.config;

/**
 * 定义Bean信息的类，其中包含了Bean的class，构造方法，属性等信息，每一个bean对象都对应了一个BeanDefination实例
 */
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
