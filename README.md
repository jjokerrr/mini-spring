# Simple-Bean-Factory

创建一个简单的BeanFactory

# Bean Definition Registry and Bean Factory

![层次结构](./assets/Bean-Factory-with-Bean-Registry.png)

Bean Definition 和 Bean Singleton注册

- BeanDefinition 保存bean的基本信息，这里简化为保存bean的class类型，用于反射构建类实例
- BeanDefinitionRegistry 提供注册BeanDefinition的注册接口
- SingletonBeanRegistry 提供Bean对象的单例接口
- DefaultSingletonBeanRegistry 实现SingletonBeanRegistry接口，提供单例map，获取单例Bean的时候Lazy创建单例对象
- AbstractAutowireCapableBeanFactory AbstractBeanFactory抽象类接口，提供lazy创建单例的具体实现
- DefaultListableBeanFactory 实现详细的Bean注册和获取流程

# Populate Bean with Property

![Bean填充策略](./assets/Populate-Bean-Strategy.png)

Populate bean with property values,using simple instantiation strategy

- 更新BeanDefinition，添加PropertyValues属性，用于填充Bean对象的成员属性
- 实现简单Bean填充策略SimpleInstantiationStrategy
- Bean初始化流程---使用默认构造方法创建Bean实例---使用默认Simple填充策略给Bean对象填充PropertyValues中的全部属性

# Populate Bean with Bean

- 新增Bean引用关系对象BeanReference，用于在Properties中说明Bean之间的引用关系
- 修改Bean的属性注入方法，如果是引用BeanB，则会关联创建BeanB的对象然后属性注入到BeanA中
- 暂时不支持循环依赖

# Resource and ResourceLoader
![ResourceLoader](./assets/DefaultResourceClassLoader.png)
![Resource](./assets/Resource.png)
- 新增Resource接口以及Resource的实现类ClassPathResource、FileSystemResource、UrlResource
- 新增ResourceLoader接口以及默认实现类DefaultResourceClassLoader，按照类路径，文件和url的方式进行