package com.demo.easyexcel;

import java.io.File;
import java.io.InputStream;

import org.junit.Test;

public class TestFileUtil {

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
        //return TestFileUtil.class.getResource("/").getPath();
        return "C:\\upload\\";
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }
    
    @Test
    public void demo(){
        String path = getPath();
        System.out.println(path);
    }

}
