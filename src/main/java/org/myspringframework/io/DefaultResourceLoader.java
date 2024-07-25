package org.myspringframework.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 默认资源加载器，按照类路径，文件和url的方式进行
 */
public class DefaultResourceLoader implements ResourceLoader {
    private static final String CLASSPATH_PREFIX = "classpath:";

    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_PREFIX)) {
            // 默认使用classpath加载，加载类资源需要将前缀去掉
            return new ClassPathResource(location.substring(CLASSPATH_PREFIX.length()));

        } else {
            try {
                // 由于url创建时可以进行异常处理，因此可以判断url资源和filesystem资源
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
