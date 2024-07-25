package org.myspringframework.context;

import org.myspringframework.bean.Car;
import org.myspringframework.bean.Person;
import org.junit.Test;
import org.myspringframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextTest {

    @Test
    public void testApplicationContext() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Car car = applicationContext.getBean("car", Car.class);
        assert car.getBrand().equals("lamborghini");
        System.out.println(car);
        Person person = applicationContext.getBean("person", Person.class);
        assert person.getName().equals("ivy");
        System.out.println(person);
    }
}
