package org.myspringframework.context;


import org.myspringframework.beans.factory.HierarchicalBeanFactory;
import org.myspringframework.beans.factory.ListableBeanFactory;
import org.myspringframework.core.io.ResourceLoader;

/**
 * ApplictionContext抽象公共接口
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader {
}
