import bean.config.BeanDefinition;
import bean.exception.BeanException;
import bean.support.DefaultListableBeanFactory;
import bean.support.PropertyValue;
import bean.support.PropertyValues;
import bean.Person;
import org.junit.Test;

public class PopulateBeanWithPropertiesTest {
    @Test
    public void testCreateBean() throws BeanException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        PropertyValue nameProperty = new PropertyValue("name", "zhangsan");
        PropertyValue ageProperty = new PropertyValue("age", 18);
        propertyValues.addPropertyValue(nameProperty);
        propertyValues.addPropertyValue(ageProperty);
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);
        beanFactory.registerBeanDefinition("person", beanDefinition);
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);

    }
}
