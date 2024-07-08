package bean;

import bean.exception.BeanException;


public interface BeanFactory {


    /**
     * 获取Bean对象
     */
    Object getBean(String name) throws BeanException;
}
