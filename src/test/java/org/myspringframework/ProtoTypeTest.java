package org.myspringframework;

import org.junit.Test;
import org.myspringframework.beans.Car;
import org.myspringframework.context.support.ClassPathXmlApplicationContext;

public class ProtoTypeTest {
    @Test
    public void testProtoType() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");
        Car car1 = applicationContext.getBean("car", Car.class);
        Car car2 = applicationContext.getBean("car", Car.class);
        assert car1 != car2;
    }
}
