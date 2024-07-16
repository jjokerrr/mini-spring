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

# Xml Bean Definition

使用xml格式进行Bean对象的声明和属性注入，对于BeanFactory进行进一步的抽象，具体参考继承图
![DefaultListableBeanFactory](./assets/xml-define-bean-DefaultListableBeanFactory.png)
![XmlBeanDefinitionLoader](./assets/xml-bean-difinition-XmlBeanDefinitionReader.png)

- 将BeanFactory系列的接口和抽象类进行进一步的抽象处理，抽象层次参考继承图，会在后面具体描述每一个接口的作用
- 实现类Spring形式的xml文件定义Bean和进行Bean的属性注入，使用xml格式支持对Bean的String属性和bean属性进行注入，目前不支持对于其他基本数据格式的注入
- BeanDefinitionReader是读取Bean信息的抽象接口，其中包含两个属性分别是BeanDefinitionRegistry是Bean定义信息的注册中心和ResourceLoader是定义文件的加载器。BeanDefinitionReader中的loadBeanDefinition是重要方法，用于从资源文件中解析Bean定义