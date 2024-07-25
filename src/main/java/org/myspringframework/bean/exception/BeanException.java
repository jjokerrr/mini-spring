package org.myspringframework.bean.exception;

public class BeanException extends RuntimeException {
    public BeanException(String msg) {
        super(msg);
    }

    public BeanException(String instantiationOfBeanFailed, Exception e) {
        super(instantiationOfBeanFailed, e);
    }
}
