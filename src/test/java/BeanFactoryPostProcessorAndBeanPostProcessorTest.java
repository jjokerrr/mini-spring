import bean.Car;
import bean.Person;
import bean.support.DefaultListableBeanFactory;
import bean.xml.XmlBeanDefinitionReader;
import common.CustomBeanFactoryProcessor;
import common.CustomBeanPostProcessor;
import org.junit.Test;

public class BeanFactoryPostProcessorAndBeanPostProcessorTest {
    @Test
    public void testBeanFactoryPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinition("classpath:spring.xml");

        CustomBeanFactoryProcessor beanFactoryProcessor = new CustomBeanFactoryProcessor();
        beanFactoryProcessor.postProcessBeanFactory(beanFactory);

        Person person = beanFactory.getBean("person", Person.class);
        System.out.println(person);

    }

    @Test
    public void testBeanPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinition("classpath:spring.xml");


        CustomBeanPostProcessor customBeanPostProcessor = new CustomBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customBeanPostProcessor);

        Car car = beanFactory.getBean("car", Car.class);
        System.out.println(car);
    }
}
