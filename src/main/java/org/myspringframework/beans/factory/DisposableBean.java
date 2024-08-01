package org.myspringframework.beans.factory;

public interface DisposableBean {
    void destroy() throws Exception;
}
