# Simple-Bean-Factory

创建一个简单的`BeanFactory`

# Bean Definition Registry and Bean Factory

![层次结构](./assets/Bean-Factory-with-Bean-Registry.png)

`Bean Definition` 和 `Bean Singleton`注册

- `BeanDefinition` 保存bean的基本信息，这里简化为保存bean的class类型，用于反射构建类实例
- `BeanDefinitionRegistry` 提供注册BeanDefinition的注册接口
- `SingletonBeanRegistry` 提供Bean对象的单例接口
- `DefaultSingletonBeanRegistry` 实现`SingletonBeanRegistry`接口，提供单例map，获取单例Bean的时候Lazy创建单例对象
- `AbstractAutowireCapableBeanFactory` `AbstractBeanFactory`抽象类接口，提供lazy创建单例的具体实现
- `DefaultListableBeanFactory` 实现详细的Bean注册和获取流程

# Populate Bean with Property

![Bean填充策略](./assets/Populate-Bean-Strategy.png)

Populate bean with property values,using simple instantiation strategy

- 更新`BeanDefinition`，添加`PropertyValues`属性，用于填充Bean对象的成员属性
- 实现简单Bean填充策略`SimpleInstantiationStrategy`
- Bean初始化流程---使用默认构造方法创建Bean实例---使用默认Simple填充策略给Bean对象填充`PropertyValues`中的全部属性

# Populate Bean with Bean

- 新增Bean引用关系对象`BeanReference`，用于在Properties中说明Bean之间的引用关系
- 修改Bean的属性注入方法，如果是引用BeanB，则会关联创建BeanB的对象然后属性注入到BeanA中
- 暂时不支持循环依赖

# Resource and ResourceLoader

![ResourceLoader](./assets/DefaultResourceClassLoader.png)
![Resource](./assets/Resource.png)

- 新增Resource接口以及Resource的实现类`ClassPathResource`、`FileSystemResource`、`UrlResource`
- 新增ResourceLoader接口以及默认实现类`DefaultResourceClassLoader`，按照类路径，文件和url的方式进行

# Xml Bean Definition

使用xml格式进行Bean对象的声明和属性注入，对于BeanFactory进行进一步的抽象，具体参考继承图
![DefaultListableBeanFactory](./assets/xml-define-bean-DefaultListableBeanFactory.png)
![XmlBeanDefinitionLoader](./assets/xml-bean-difinition-XmlBeanDefinitionReader.png)

- 将`BeanFactory`系列的接口和抽象类进行进一步的抽象处理，抽象层次参考继承图，会在后面具体描述每一个接口的作用
- 实现类Spring形式的xml文件定义Bean和进行Bean的属性注入，使用xml格式支持对Bean的String属性和bean属性进行注入，目前不支持对于其他基本数据格式的注入
- `BeanDefinitionReader`是读取Bean信息的抽象接口，其中包含两个属性分别是`BeanDefinitionRegistry`是Bean定义信息的注册中心和`ResourceLoader`是定义文件的加载器。
- `BeanDefinitionReader`中的`loadBeanDefinition`是重要方法，用于从资源文件中解析Bean定义

# BeanFactoryPostProcessorAndBeanPostProcessor
![BeanLife](./assets/BeanFactoryPostProcessorAndBeanPostProcessor.png)

`BeanFactoryPostProcessor`接口实现了对BeanDefinition进行修改，允许我们在Bean对象实例化之前修改BeanDefinition中的属性信息
而`BeanPostProcessor`接口在Bean实例化和属性填充之后，在Bean的初始化阶段之前执行`postProcessBeforeInitialization`方法，在初始化阶段执行之后执行`postProcessBeforeInitialization`。
`BeanPostProcessor`允许在Bean实例化之后修改Bean对象或者替换Bean对象

- `创建BeanFactoryPostProcessor`，其实现类`postProcessBeanFactory`通过修改`ConfigurableListableBeanFactory`中的`BeanDefinitionMap`来修改`BeanDefinition`
- 创建`BeanPostProcessor`接口，修改getBean方法的实现逻辑，在实例化Bean之后围绕Bean初始化阶段来增强Bean

# ApplicationContext
![ApplicationContext](./assets/ClassPathXmlApplicationContext.png)
![ApplicationContext#refresh](./assets/ApplicationContext.png)

`ApplicationContext`是在BeanFactory基础之上的进一步封装，支持BeanFactory的全部功能，在此基础之上，新增对`BeanFactoryPostProcessor`和`BeanPostProcessor`的自动识别、
bean声明配置的资源加载，后续还会扩充关于容器事件和监听器、单例bean的自动初始化等
BeanFactory是spring的基础设置，而Application是对BeanFactory的封装，面向Spring的使用者

- 创建`ApplicationContext`接口以及其系列实现类，具体实现流程可参考上面的继承关系。
- `ApplicationContext`中的重要方法`refresh`用于重建`BeanFactory`并自动加载`BeanDefinition`，提前初始化全部Bean对象，具体的方法逻辑可以参考上图
- 重构包名

# Init-and-Destroy-Method
![Init-And-Destroy-Method](./assets/InitAndDestroyBeanMethod.png)
在Spring中，Bean在初始化阶段和销毁阶段可以执行不同的方法，具体可以通过以下方式来进行定义
1. 在配置文件（xml）中定义`init-method`和`destroy-method`
2. 类定义中分别继承InitializingBean和DisposableBean，其中的afterPropertySet方法和destroy分别在初始化阶段和销毁阶段进行执行
3. 通过在类定义方法中添加`@PostConstruct`和`@PreDestroy`注解

上述三种不同的实现方式，在本小节中实现了前两种。
- 为了实现自定义初始化方法和自定义销毁方法，重构了`BeanDefinition`的成员属性和加载xml的过程，使其能够加载自定义初始化和销毁方法
- 初始化方法在`AbstractAutowireCapableBeanFactory#invokeInitMethods`执行。
- 创建适配器类`DisposableBeanAdapter`，用于将实现自定义实现的销毁方法进行封装
，拥有销毁方法的bean在`AbstractAutowireCapableBeanFactory#registerDisposableBeanIfNecessary`中注册到`disposableBeans`中。
- 为了确保销毁方法在虚拟机关闭之前执行，向虚拟机中注册一个钩子方法，查看`AbstractApplicationContext#registerShutdownHook`（非web应用需要手动调用该方法）。
- 当然也可以手动调用`ConfigurableApplicationContext#close`方法关闭容器。

# Aware-Interface
![AwareInterface](./assets/AwareInterface.png)
`Aware` 接口使Bean对象拥有感知容器的能力，`Aware` 接口的两个实现类，`BeanFactoryAware` 和 `ApplicationContextAware` 分别实现了对`BeanFacoty`和`ApplicationContext` 的感知
在实现BeanFactoryWare功能中，主要在Bean的初始化阶段进行操作，具体可以见 `org.myspringframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean`
在实现ApplicationContextWare功能中，使用了BeanPostProcessor的功能，由于Bean对象的管理是通过ApplicationContext中的BeanFactory属性实现。具体实现可以参考`org.myspringframework.context.support.ApplicationContextAwareProcessor.postProcessBeforeInitialization` 
![BeanFactoryAwareAndApplicatinoContextAware](./assets/BeanFactoryAwareAndApplicatinoContextAware.png)

- 容器实现接口`Aware` 和子接口 `BeanFactoryAware` `ApplicationContextAware`
- `ApplicationContextAware` 的实现支持 `ApplicationContextAwareProcessor`

# Scope-Type
增加Bean的范围模型，支持`proto`和`singleton`两种类型，proto为原型模式，每一次获取Bean对象都会生成一个新的Bean实例，并且proto类型的Bean不会触发销毁方法，singleton为单例模式只会创建一个单例对象存放在singletonMap中

- `BeanDefinition`支持scope配置
- 创建Bean对象时会根据对象scope类型判断是否存放在singletonMap中

# Factory-Bean
FactoryBean是一种特殊的bean，当向容器获取该bean时，容器不是返回其本身，而是返回其FactoryBean#getObject方法的返回值，可通过编码方式定义复杂的bean。

在`getBean`方法中，当返回Bean对象实例时，会检查Bean对象的类型，如果是`FactoryBean`类型,会执行`getObject`方法并会根据是否单例写入缓存，具体参考`org.myspringframework.beans.factory.support.AbstractBeanFactory#getObjectForBeanInstance`

- 创建`FactoryBean`接口
- 修改`getBean`方法
- 新增`factoryBeanCache`缓存

# Event-and-EventListener
实现Spring的事件机制，实现Spring的事件发布和事件监听机制。具体使用方法通过Spring的ApplicationContext容器发布事件，容器将事件广播给全部的ApplicationListener进行对应的消费

基础接口
- ApplicationEvent 容器事件
- ApplicationEventPubliser 容器事件发布接口
- ApplicationListener 容器事件监听接口

event包下
- ApplicationEventMulticaster  Spring广播事件接口
- ApplicationContextEvent Spring Context事件，能够感知ApplicationContext容器
- ContextRefreshedEvent 容器刷新事件
- ContextClosedEvent 容器关闭事件
- SimpleApplicationMulticaster 广播接口简单实现类

在Context中，将ApplicationEventMulticaster 作为成员属性写入ApplicationContext，新增发布事件接口，实现接口广播逻辑

# PointcutExpression
实现切入点表达式，支持execution开头切点表达式。利用aspectj包下的切点表达式解析能力。提供ClassFilter接口用于匹配类，提供MethodMatcher接口用于方法匹配

PointCut需要同时匹配类和方法，包含ClassFilter和MethodMatcher，AspectJExpressionPointcut是支持AspectJ切点表达式的PointCut实现，简单实现仅支持execution函数。

# Jdk-Dynamic-Proxy-Aop
复习一下：

基于jdk的动态代理，主要有几个关键点

1. 基于接口来增强
2. 动态代理类实现InvocationHandler
3. 通过Proxy.newInstace来构建代理对象

基于Jdk的动态代理机制，结合切面表达式实现基于接口的AOP增强。重要对象
- TargetSource 基础对象的封装，对象接口
- MethodInterceptor 增强逻辑
- AdviceSupport 切面重要属性的集合
  - TargetSource 记录需要增强的对象
  - methodMatcher 匹配joinpoint
  - methodInterceptor 方法增强逻辑
- JdkDynamicAopProxy JDK动态代理实现逻辑，包括InvocationHandler接口和Proxy代理对象的获取

# Proxy-Factory
代理工厂，主要生产Cglib和Jdk两种模式，两种模式均实现AopProxy接口，主要方法getProxy获取动态代理对象

代理工厂生产的类型由AdvisedSupport中的proxyTargetClass布尔型属性决定，具体含义表示为是否使用jdk动态代理，默认使用cglib动态代理

- ProxyFactory 生产代理工厂
- AdvisedSupport 增加是否使用cglib代理字段

# Common-Advice
增强Spring AOP的切面，常用的有BeforeAdvice/AfterAdvice/AfterReturningAdvice/ThrowsAdvice

![common-advice.png](./assets/common-advice.png)

GenericInterceptor基本方法增强类型用于MethodInterceptor

# Pointcut-Advisor
将切面、切面表达式和连接点组合到一起。Pointcut用于捕获JointPoint，Advice决定在JointPoint上增强的操作

增加接口

+ Advisor getAdvice
+ PointcutAdvisor getPointcut方法
+ 实现类 AspectJExpressionPointcutAdvisor

# Auto-Proxy
将代理融入spring的生命周期中，实际作用是在bean的创建过程中，会检查是否存在该bean的aop切面，匹配成功会创建一个新的代理对象。原始对象创建只作为代理对象的targetSource

具体的，实现方式通过BeanPostProcessor接口实现，引入BeanPostProcessor相同功能的接口InstantiationAwareBeanPostProcessor（本质作用是一样的，该接口会超前执行，只作用于代理对象的生成）。在创建bean的createBean方法中，会首先执行该接口的实现类DefaultAdvisorAutoProxyCreator，提前实例化全部的AspectJExpressionPointcutAdvisor对象，改对象中存在全部需要代理的aop信息。

坑点：由于在InstantiationAwareBeanPostProcessor处理阶段返回代理对象，会导致短路，不会继续走原来的创建bean的流程。如果全部的都检查代理对象，会导致死循环发生，因此要对aop相关的类进行提前放行

+ AbstractAutowireCapableBeanFactory  修改bean的创建逻辑，首先检查是否需要创建代理对象，具体在createBean方法中
+ DefaultAdvisorAutoProxyCreator  实现代理的重要实现类，用于创建代理对象，InstantiationAwareBeanPostProcessor的实现类
+ InstantiationAwareBeanPostProcessor 功能与BeanPostProcessor接口相同，用于创建bean代理对象



结合aop创建后，现在bean的生命周期如下


![aop-proxy](./assets/auto-proxy.png)