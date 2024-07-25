package org.myspringframework.bean.support;

/**
 * 属性键值对对象
 */
public class PropertyValue {
    private String key;
    private Object value;


    public PropertyValue() {

    }

    public PropertyValue(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public void setValue(Object value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

}
