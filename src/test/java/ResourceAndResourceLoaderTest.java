import cn.hutool.core.io.IoUtil;
import core.io.DefaultResourceClassLoader;
import core.io.Resource;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ResourceAndResourceLoaderTest {
    @Test
    public void testFileSystemResource() {
        String filePath = "F:/Code/Java/spring/src/test/resources/hello.txt";
        DefaultResourceClassLoader resourceClassLoader = new DefaultResourceClassLoader();
        Resource resource = resourceClassLoader.getResource(filePath);
        try {
            InputStream inputStream = resource.getInputStream();
            String content = IoUtil.readUtf8(inputStream);
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testClassPathResource() {
        String filePath = "classpath:SimpleBeanContainerTest.class";
        DefaultResourceClassLoader resourceClassLoader = new DefaultResourceClassLoader();
        Resource resource = resourceClassLoader.getResource(filePath);
        try {
            InputStream inputStream = resource.getInputStream();
            String content = IoUtil.readUtf8(inputStream);
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUrlResource() {
        String url = "https://www.baidu.com";
        DefaultResourceClassLoader resourceClassLoader = new DefaultResourceClassLoader();
        Resource resource = resourceClassLoader.getResource(url);
        try {
            InputStream inputStream = resource.getInputStream();
            String content = IoUtil.readUtf8(inputStream);
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getClassPath(){
        String property = System.getProperty("java.class.path");
        for(String ss:property.split(";")){
            System.out.println(ss);
        }
    }
}
