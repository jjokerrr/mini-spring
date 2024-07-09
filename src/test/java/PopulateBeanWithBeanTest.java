import bean.config.BeanDefinition;
import bean.config.BeanReference;
import bean.exception.BeanException;
import bean.support.DefaultListableBeanFactory;
import bean.support.PropertyValue;
import bean.support.PropertyValues;
import common.Car;
import common.Person;
import org.junit.Test;

public class PopulateBeanWithBeanTest {

    @Test
    public void testPopulateBeanWithBean() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // create Car bean
        PropertyValues carProperties = new PropertyValues();
        PropertyValue brandProperty = new PropertyValue("brand", "xiaomi");
        carProperties.addPropertyValue(brandProperty);
        BeanDefinition carBeanDefinition = new BeanDefinition(Car.class, carProperties);
        beanFactory.registerBeanDefinition("car", carBeanDefinition);


        // create Person Bean
        PropertyValues personProperties = new PropertyValues();
        PropertyValue nameProperty = new PropertyValue("name", "zhangsan");
        PropertyValue ageProperty = new PropertyValue("age", 18);
        PropertyValue carProperty = new PropertyValue("car", new BeanReference("car"));
        personProperties.addPropertyValue(nameProperty);
        personProperties.addPropertyValue(ageProperty);
        personProperties.addPropertyValue(carProperty);
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, personProperties);
        beanFactory.registerBeanDefinition("person", beanDefinition);
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }
}
