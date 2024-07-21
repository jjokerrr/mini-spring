package bean.xml;

import bean.config.BeanDefinition;
import bean.config.BeanDefinitionRegistry;
import bean.config.BeanReference;
import bean.exception.BeanException;
import bean.support.AbstractBeanDefinitionReader;
import bean.support.PropertyValue;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import core.io.Resource;
import core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }


    @Override
    public void loadBeanDefinition(String path) {
        Resource resource = getResourceLoader().getResource(path);
        loadBeanDefinition(resource);
    }

    @Override
    public void loadBeanDefinition(Resource resource) {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanDefinitions(inputStream);
        } catch (IOException e) {
            throw new BeanException("IOException parsing XML document from " + resource, e);
        }
    }

    /**
     * 解析xml并注册BeanDefinition
     */
    private void doLoadBeanDefinitions(InputStream inputStream) {
        // 解析xml
        Document document = XmlUtil.readXML(inputStream);
        // 获取根对象
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            // 检查全部的bean标签
            Node item = childNodes.item(i);
            if (!(item instanceof Element)) continue;
            if (!BEAN_ELEMENT.equals(item.getNodeName())) continue;

            // 通过class、name、id创建实例化bean
            Element bean = (Element) item;
            String id = bean.getAttribute(ID_ATTRIBUTE);
            String name = bean.getAttribute(NAME_ATTRIBUTE);
            String className = bean.getAttribute(CLASS_ATTRIBUTE);

            // 加载class
            Class<?> cls = null;
            try {
                cls = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new BeanException(className + " can not be found");
            }

            String beanName = StrUtil.isBlank(id) ? name : id;
            if (StrUtil.isBlank(beanName)) {
                // 小驼峰命名beanName
                beanName = StrUtil.lowerFirst(cls.getSimpleName());
            }
            BeanDefinition beanDefinition = new BeanDefinition(cls);


            // 遍历bean标签下的所有property对象
            NodeList propertyNode = bean.getChildNodes();
            for (int j = 0; j < propertyNode.getLength(); j++) {
                Node propertyItem = propertyNode.item(j);
                if (!(propertyItem instanceof Element)) continue;
                if (!PROPERTY_ELEMENT.equals(propertyItem.getNodeName())) continue;
                Element property = (Element) propertyItem;
                String nameAttr = property.getAttribute(NAME_ATTRIBUTE);
                String valueAttr = property.getAttribute(VALUE_ATTRIBUTE);
                String refAttr = property.getAttribute(REF_ATTRIBUTE);

                // 对string类型注入value，对ref类型注入BeanReference，value属性注入基本数据类型
                if (StrUtil.isBlank(nameAttr)) {
                    throw new BeanException("the property name can not be null");
                }

                Object value = valueAttr;
                if (StrUtil.isNotBlank(refAttr)) {
                    value = new BeanReference(refAttr);
                }
                PropertyValue propertyValue = new PropertyValue(nameAttr, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);

            }

            // 将BeanDefinition进行Registry
            if (getBeanDefinitionRegistry().containsBeanDefinition(beanName)) {
                throw new BeanException(beanName + " has already exist");
            }

            getBeanDefinitionRegistry().registerBeanDefinition(beanName, beanDefinition);

        }


    }

}
