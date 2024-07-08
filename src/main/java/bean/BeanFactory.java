package bean;

import java.util.HashMap;

public class BeanFactory {
    private HashMap<String, Object> beanMap = new HashMap<>();

    /**
     * 将Bean对象注册到beanmap
     */
    public void register(String name, Object beanInstance) {
        beanMap.put(name, beanInstance);
    }

    /**
     * 获取Bean对象
     */
    public Object getBean(String name) {
        return beanMap.get(name);
    }
}
