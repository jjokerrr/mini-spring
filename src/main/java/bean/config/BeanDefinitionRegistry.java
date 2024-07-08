package bean.config;


/**
 * 用于BeanDefination的注册接口
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册BeanDefinition
     */
    void registerBeanDefinition(String name,BeanDefinition beanDefinition);

}
