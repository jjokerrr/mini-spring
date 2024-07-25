package org.myspringframework.bean.config;

/**
 * 表示对bean对象的引用关系
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String name) {
        beanName = name;
    }

    public String getBeanName() {
        return beanName;
    }
}
