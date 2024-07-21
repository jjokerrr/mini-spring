import bean.exception.BeanException;
import bean.support.DefaultListableBeanFactory;
import bean.DemoClass;
import org.junit.Test;

public class BeanDefinitionRegistryAndBeanFactoryTest {

    @Test
    public void testBeanFactory() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("hello", DemoClass.class);

        DemoClass bean =(DemoClass) beanFactory.getBean("hello");
        System.out.println(bean.getMsg());

    }
}
