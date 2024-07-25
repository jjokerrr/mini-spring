package org.myspringframework.bean.support;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化Bean对象需要的全部属性列表
 */
public class PropertyValues {
    private List<PropertyValue> propertyValueList = new ArrayList<>();


    public void addPropertyValue(PropertyValue propertyValue) {
        // 检查是否存在key
        for (PropertyValue pv : propertyValueList) {
            if (pv.getKey().equals(propertyValue.getKey())) {
                pv.setValue(propertyValue.getValue());
                return;
            }
        }
        propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
