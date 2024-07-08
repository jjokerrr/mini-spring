# Simple-Bean-Factory
创建一个简单的BeanFactory
# Bean Definition Registry and Bean Factory
![层次结构](https://github.com/DerekYRC/mini-spring/blob/main/assets/bean-definition-and-bean-definition-registry.png)

Bean Definition 和 Bean Singleton注册
- BeanDefinition 保存bean的基本信息，这里简化为保存bean的class类型，用于反射构建类实例
- BeanDefinitionRegistry 提供注册BeanDefinition的注册接口
- SingletonBeanRegistry 提供Bean对象的单例接口
- DefaultSingletonBeanRegistry 实现SingletonBeanRegistry接口，提供单例map，获取单例Bean的时候Lazy创建单例对象
- AbstractAutowireCapableBeanFactory AbstractBeanFactory抽象类接口，提供lazy创建单例的具体实现
- DefaultListableBeanFactory 实现详细的Bean注册和获取流程
