package com.demo.file;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Random;

import org.apache.ibatis.javassist.compiler.ast.NewExpr;
import org.apache.tomcat.jni.User;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.PublicImpl;
import org.junit.Test;

public class FileTest {

    /*
     * public static String GetImageStr(String imgFile) { InputStream in = null;
     * byte[] data = null; // 读取图片字节数组 try { in = new FileInputStream(imgFile);
     * data = new byte[in.available()]; in.read(data); in.close(); } catch
     * (IOException e) { e.printStackTrace(); } // 对字节数组Base64编码 BASE64Encoder
     * encoder = new BASE64Encoder(); // 返回Base64编码过的字节数组字符串 return
     * encoder.encode(data); }
     * 
     * public static boolean GenerateImage(String base64str, String savepath) {
     * // 对字节数组字符串进行Base64解码并生成图片 if (base64str == null) // 图像数据为空 return false;
     * BASE64Decoder decoder = new BASE64Decoder(); try { byte[] b =
     * decoder.decodeBuffer(base64str); for (int i = 0; i < b.length; ++i) { if
     * (b[i] < 0) {// 调整异常数据 b[i] += 256; } } OutputStream out = new
     * FileOutputStream(savepath); out.write(b); out.flush(); out.close();
     * return true; } catch (Exception e) { return false; } }
     */

    /// 对图片进行base64解码
    public static boolean outputfile(String data) {
        String base64Data = data.split(",")[1];
        Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(base64Data);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("D:\\picture\\a.jpg");
            fos.write(bytes);
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
        }
        return true;
    }

    // 对图片进行base64编码
    public static String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data);
    }

    @Test
    public void file() {
        File file = new File("D:\\picture");
        File[] files = file.listFiles();
        for (File f : files) {
            boolean b1 = f.isDirectory();
            boolean b2 = f.isFile();
            System.out.println("目录：" + b1 + "，文件：" + b2);
            System.out.println(f.getName());
            System.out.println("----------------------");
        }
    }

    //-------------------------------------------------------------------------------
    
    // 保存符合条件的文件的名字
    private static List<String> list = new ArrayList<String>();

    /**
     * @param path 文件路径
     * @param name 后缀名；如：.xml, .jpg, .txt, ...
     */
    public static void getFileName(String path, String name) {
        File file = new File(path);
        if (file != null) {
            // 判断是否是文件夹
            if (file.isDirectory()) {
                // 遍历文件夹中的文件
                for (File f : file.listFiles()) {
                    // 判断文件夹中的文件是否是文件夹
                    if (f.isDirectory()) {
                        // 递归
                        getFileName(f.getAbsolutePath(), name);
                    } else {
                        // 判断文件的后缀是否符合条件，符合条件则将文件的名字放入集合
                        if (f.getName().endsWith(name)) {
                            list.add(f.getName());
                        }
                    }
                }
            } else {
                // 判断文件的后缀是否符合条件，符合条件则将文件的名字放入集合
                if (file.getName().endsWith(name)) {
                    list.add(file.getName());
                }
            }
        }
    }

    public static void main(String[] args) {
        // 调用方法
        getFileName("D:/files", ".xml");
        // 打印集合中的所有元素
        System.out.println(list); 
        // 打印集合的长度
        System.out.println(list.size());
    }

    //--------------------------------------------------------------------------------
    
}
