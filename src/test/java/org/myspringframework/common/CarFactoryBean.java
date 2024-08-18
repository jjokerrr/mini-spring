package org.myspringframework.common;

import org.myspringframework.beans.Car;
import org.myspringframework.beans.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean<Car> {
    private String brand;


    @Override
    public Car getObject() throws Exception {
        return new Car(brand);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
