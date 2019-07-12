package com.demo.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class FileDemo2 {

    // 检索范围
    private static List<String> retrieveFiles = new ArrayList<String>(Arrays.asList(
            "welcomePageMenu.xml", "homePageFiveMiniJpg.xml", "nodeMenuCompJpg.xml", "nodeMenuJpg.xml", "flipPageJpg.xml"));
    
    // 项目所在路径
    private static String projectPath = "D:/files/project";
    // 要检索的xml文件所在路径
    private static String searchFilePath = "D:/files/deploy/uiinit";
    // 要记录删除痕迹的文件的路径
    private static String delRecordPath = "D:/files/trail.txt";
    // routemap文件所在路径
    private static String compareFile = "D:/files/routemap.xml";

    /**
     * 测试
     * 
     * @throws Exception
     */
    @Test
    public void demo() throws Exception {
        // 备份
        fileOperation(projectPath);
        // 删除不匹配的文件
        delFile(searchFilePath, delRecordPath);
        // 获取document对象
        Document document = getDocument(compareFile);
        Element rootElement = document.getRootElement();
        // 删除不符合条件的node节点
        delNode(rootElement);
        writeBack(document, "D:/files/routemap2.xml");
    }

    /**
     * 文件相关的操作
     * 
     * @param path
     *            要操作的文件夹地址
     * @throws Exception
     */
    public static void fileOperation(String path) throws Exception {
        // 得到当前路径的父级路径
        String parentPath = path.substring(0, path.lastIndexOf("/"));
        // 创建名为"备份"的文件夹
        String backPath = parentPath + "/备份";
        File backFile = new File(backPath);
        if (!backFile.exists()) {
            backFile.mkdirs();
        }
        // 得到uiinit文件夹
        String uiinitPath = parentPath + "/deploy/uiinit";
        copyFiles(uiinitPath, backPath);
    }

    /**
     * 获取文件路径并复制
     * 
     * @param oldPath
     *            老文件地址
     * @param newPath
     *            新文件地址
     * @throws IOException
     */
    public static void copyFiles(String oldPath, String newPath) throws IOException {
        File file = new File(oldPath);
        if (file != null) {
            // 判断是否是文件夹
            if (file.isDirectory()) {
                // 遍历文件夹中的文件
                for (File f : file.listFiles()) {
                    // 递归
                    copyFiles(f.getAbsolutePath(), newPath + "/" + f.getName());
                }
            } else {
                // 复制
                copyFile(oldPath, newPath);
            }
        }
    }

    /**
     * 复制文件到新的文件夹
     * 
     * @param oldPath
     *            老文件地址
     * @param newPath
     *            新文件地址
     */
    public static void copyFile(String oldPath, String newPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File oldFile = new File(oldPath);
            String path = newPath.substring(0, newPath.lastIndexOf("/"));
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
     * @param path
     *            文件路径
     * @param content
     *            内容
     * @throws IOException
     */
    public static void writeDataForTrail(String path, String content) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            file.mkdirs();
        }
        FileWriter fw = new FileWriter(file, true);
        fw.write(content + "\r\n");
        fw.flush();
        fw.close();
    }

    /**
     * 检索文件，删除不符合条件的文件
     * 
     * @param path
     *            要检索的文件夹地址
     * @param delRecordPath
     *            需要记录的文件的地址
     * @throws IOException
     */
    public static void delFile(String path, String delRecordPath) throws IOException {
        File file = new File(path);
        if (file != null) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    delFile(f.getAbsolutePath(), delRecordPath);
                }
            } else {
                String fileName = file.getName();
                if (!retrieveFiles.contains(fileName)) {
                    file.delete();
                    writeDataForTrail(delRecordPath, file.getName() + " --> 删除");
                }
            }
        }
    }

    /**
     * 获取document对象
     * 
     * @param path
     *            文件路径
     * @return
     * @throws DocumentException
     */
    public static Document getDocument(String path) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));
        return document;
    }

    /**
     * 回写
     * 
     * @param document
     * @param path
     * @throws Exception
     */
    public static void writeBack(Document document, String path) throws Exception {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter xmlWriter = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path)), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    /**
     *  删除节点
     * 
     * @param element
     * @throws IOException
     */
    public static void delNode(Element element) throws IOException {
        if(element != null){
            @SuppressWarnings("unchecked")
            List<Element> elements = element.elements();
            // 判断是否有子节点
            if (elements != null && !elements.isEmpty()) {
                for (Element el : elements) {
                    delNode(el);
                }
            }
            if(!element.isRootElement()){
              String url = element.attribute("url").getStringValue();
              if (url != null && !"".equals(url)) {
                  url = url.substring(url.indexOf("@") + 1, url.lastIndexOf(".")) + ".xml";
                  if (!retrieveFiles.contains(url)) {
                      element.getParent().remove(element);
                  }
              }
            }
        }
    }

    
}
