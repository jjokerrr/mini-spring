package org.myspringframework;

import org.junit.Test;
import org.myspringframework.beans.Car;
import org.myspringframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanTest {
    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
        Car car = applicationContext.getBean("car", Car.class);
        assert "porsche".equals(car.getBrand());
    }
}
