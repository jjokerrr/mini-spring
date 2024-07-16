package bean.config;

import bean.HierarchicalBeanFactory;

/**
 * 提供了对BeanFactory的配置能力,进行设置和管理Bean定义、作用域、后处理器等
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {
}
