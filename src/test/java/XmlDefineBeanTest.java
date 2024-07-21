import bean.support.DefaultListableBeanFactory;
import bean.xml.XmlBeanDefinitionReader;
import bean.Car;
import bean.Person;
import org.junit.Test;

public class XmlDefineBeanTest {
    @Test
    public void testXmlDefineBean(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinition("classpath:spring.xml");

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);


        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
    }
}
