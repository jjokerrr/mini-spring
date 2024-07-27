package org.myspringframework.context;


import org.myspringframework.bean.HierarchicalBeanFactory;
import org.myspringframework.bean.ListableBeanFactory;
import org.myspringframework.core.io.ResourceLoader;

/**
 * ApplictionContext抽象公共接口
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader {
}
