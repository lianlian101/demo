package com.demo.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class FileDemo_back_1 {

    /**
     * 获取系统路径分隔符
     */
    private static final String SEPA = File.separator;

    /**
     * 测试
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 项目所在的路径
        // String property = System.getProperty("user.dir");
        // System.out.println(property);
        
        // 需要备份的文件夹的路径
        String projectPath = "D:\\files\\project";// 测试路径
        fileOperation(projectPath);
    }

    /**
     *  文件相关的操作
     * 
     * @param path 要操作的文件夹地址
     * @throws Exception
     */
    public static void fileOperation(String path) throws Exception {
        // 得到当前路径的父级路径
        String parentPath = path.substring(0, path.lastIndexOf(SEPA));
        // 创建名为“备份”的文件夹
        String backPath = parentPath + SEPA + "备份";
        File backFile = new File(backPath);
        if (!backFile.exists()) {
            backFile.mkdirs();
        }
        // 得到uiinit文件夹
        String uiinitPath = parentPath + SEPA + "deploy" + SEPA + "uiinit";
        getFilePath(uiinitPath, backPath);
    }

    /**
     * 获取文件路径
     * 
     * @param oldPath 老文件地址
     * @param newPath 新文件地址
     * @throws IOException
     */
    public static void getFilePath(String oldPath, String newPath) throws IOException {
        File file = new File(oldPath);
        if (file != null) {
            // 判断是否是文件夹
            if (file.isDirectory()) {
                // 遍历文件夹中的文件
                for (File f : file.listFiles()) {
                    // 判断文件夹中的文件是否是文件夹
                    if (f.isDirectory()) {
                        // 递归
                        getFilePath(f.getAbsolutePath(), newPath + SEPA + f.getName());
                    } else {
                        // 复制
                        copyFile(oldPath + SEPA + f.getName(), newPath + SEPA + f.getName());
                    }
                }
            } else {
                // 复制
                copyFile(oldPath + SEPA + file.getName(), newPath + SEPA + file.getName());
            }
        }
    }

    /**
     *  复制文件到新的文件夹
     * 
     * @param oldPath 老文件地址
     * @param newPath 新文件地址
     */
    public static void copyFile(String oldPath, String newPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File oldFile = new File(oldPath);
            String path = newPath.substring(0, newPath.lastIndexOf(SEPA));
            File newFileDir = new File(path);
            if (!newFileDir.exists()) {
                newFileDir.mkdirs();
            }
            File newFile = new File(newPath);
            fis = new FileInputStream(oldFile);
            fos = new FileOutputStream(newFile);
            byte[] b = new byte[1024];
            int readLine = 0;
            if ((readLine = fis.read(b)) != -1) {
                fos.write(b, 0, readLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 向文件中写数据，保留之前的数据
     * 
     * @param path 文件路径
     * @param content 内容
     * @throws IOException
     */
    public static void writeData(String path, String content) throws IOException{
        File file = new File(path);
        if (file.exists()) {
            file.mkdirs();
        }
        FileWriter fw = new FileWriter(file, true);
        fw.write(content+"\r\n");
        fw.flush();
        fw.close();
    }
    
    @Test
    public void demo() throws IOException{
        String path = "D:\\files\\trail.txt";
        String content = "哈哈呵呵";
        writeData(path, content);
    }
    
}
