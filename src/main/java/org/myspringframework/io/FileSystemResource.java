package org.myspringframework.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/**
 * filesystem的resource
 */
public class FileSystemResource implements Resource {
    private final String path;

    public FileSystemResource(String path) {
        this.path = path;
    }

    /**
     * 使用NIO进行文件输入流的读取操作，具有更高的性能
     */
    @Override
    public InputStream getInputStream() throws IOException {
       try{
           Path path = new File(this.path).toPath();
           return Files.newInputStream(path);
       } catch (NoSuchFileException e) {
           throw new FileNotFoundException(this.path+"cannot be opened because it does not exist");
       }
    }
}
