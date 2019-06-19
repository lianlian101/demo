package com.demo.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class FileDemo {

    /**
     ** 存放文件下及其子文件夹的所有文件名
     */
    private static final Set<String> fileSet = new HashSet<String>();

    /**
     * 保存所有url对应的值
     */
    private static final Set<String> urlOfValues = new HashSet<>();

    /**
     * 主函数
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 项目所在路径
        String projectPath = "D:/files/project";
        // 复制老文件夹中的内容到新文件夹里
        fileOperation(projectPath);
        // 要检索的xml文件所在路径
        String path = "D:/files/deploy/uiinit";
        // 要记录删除痕迹的文件的路径
        String delRecordPath = "D:/files/trail.txt";
        // 删除不匹配的文件
        searchFile(path, ".xml", delRecordPath);
        // 获取document对象
        Document document = getDocument("D:/files/routemap.xml");
        Element rootElement = getRootElement(document);
        getElementValue(rootElement);
        // 获取指定文件夹下的所有文件
        getFileNames(path);
        // 删除不匹配routemap.xml中url对应的文件,并记录删除痕迹
        delInexistenceWithXml(path, delRecordPath);
        // 记录routemap.xml中url在uiinit中不存在文件
        String recordPath = "D:/files/wrong.txt";
        recordInexistenceWithFile(rootElement, recordPath);
    }

    // =========================================================
    // ======================文件操作=============================
    // =========================================================

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
        // 创建名为“备份”的文件夹
        String backPath = parentPath + "/备份";
        File backFile = new File(backPath);
        if (!backFile.exists()) {
            backFile.mkdirs();
        }
        // 得到uiinit文件夹
        String uiinitPath = parentPath + "/deploy/uiinit";
        getFilePath(uiinitPath, backPath);
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
    public static void getFilePath(String oldPath, String newPath) throws IOException {
        File file = new File(oldPath);
        if (file != null) {
            // 判断是否是文件夹
            if (file.isDirectory()) {
                // 遍历文件夹中的文件
                for (File f : file.listFiles()) {
                    // 递归
                    getFilePath(f.getAbsolutePath(), newPath + "/" + f.getName());
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
     * @param name
     *            要检索的文件的后缀名
     * @param delRecordPath
     *            需要记录的文件的地址
     * @throws IOException
     */
    public static void searchFile(String path, String name, String delRecordPath) throws IOException {
        File file = new File(path);
        if (file != null) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    searchFile(f.getAbsolutePath(), name, delRecordPath);
                }
            } else {
                String suffixName = file.getName().substring(file.getName().lastIndexOf("."));
                if (!name.equals(suffixName)) {
                    file.delete();
                    writeDataForTrail(delRecordPath, file.getName() + "删除");
                }
            }
        }
    }

    /**
     * 获取文件夹下的所有文件名称
     * 
     * @param path
     *            检索的文件夹地址
     *
     */
    public static void getFileNames(String path) {
        File file = new File(path);
        if (file != null) {
            if (file.isDirectory()) {
                String[] fileNames = file.list();
                List<String> list = Arrays.asList(fileNames);
                fileSet.addAll(list);
                File[] files = file.listFiles();
                for (File f : files) {
                    getFileNames(f.getAbsolutePath());
                }
            }
        }
    }

    // =========================================================
    // =====================xml文件内容操作========================
    // =========================================================

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
     * 获取父节点元素对象
     * 
     * @param document
     * @return
     */
    public static Element getRootElement(Document document) {
        return document.getRootElement();
    }

    /**
     * 获取所有的url的值
     * 
     * @param element
     */
    @SuppressWarnings("unchecked")
    public static void getElementValue(Element element) {
        // 保存module节点中url属性的值
        if (!element.isRootElement()) {
            String url = element.attribute("url").getStringValue();
            if (url != null && !"".equals(url)) {
                url = url.substring(url.indexOf("@") + 1, url.lastIndexOf("."));
                url += ".xml";
                urlOfValues.add(url);
            }
        }
        // 获取子节点
        List<Element> list = element.elements();
        // 判断是否有子节点
        if (list != null && !list.isEmpty()) {
            list.forEach(el -> {
                // 递归
                getElementValue(el);
            });
        }
    }

    /**
     * 删除在routemap.xml中不存在的文件
     * 
     * @param path
     *            要检索的文件夹路径
     * @param delRecordPath
     *            要记录的文件的路径
     * 
     * @throws IOException
     *
     */
    public static void delInexistenceWithXml(String path, String delRecordPath) throws IOException {
        File file = new File(path);
        if (file != null) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files.length < 1) {
                    if (!file.getName().equals("uiinit")) {
                        file.delete();
                    }
                } else {
                    for (File f : files) {
                        delInexistenceWithXml(f.getAbsolutePath(), delRecordPath);
                    }
                }
            } else {
                String fileName = file.getName();
                File parentFile = file.getParentFile();
                String parentFileName = parentFile.getName();
                if (!urlOfValues.contains(fileName)) {
                    if (!fileName.equals("routemap.xml")) {
                        file.delete();
                        writeDataForTrail(delRecordPath, fileName + "删除");
                        if (parentFile.listFiles().length < 1) {
                            parentFile.delete();
                            writeDataForTrail(delRecordPath, parentFileName + "删除");
                        }
                    }
                }
            }
        }
    }

    /**
     * 记录在文件夹中不存在的url对应的id值
     * 
     * @param element
     * @param recordPath
     *            要记录的文件的路径
     * @throws IOException
     */
    public static void recordInexistenceWithFile(Element element, String recordPath) throws IOException {
        // 保存module节点中url属性的值
        if (!element.isRootElement()) {
            String id = element.attribute("id").getStringValue();
            String url = element.attribute("url").getStringValue();
            if (url != null && !"".equals(url)) {
                url = url.substring(url.indexOf("@") + 1, url.lastIndexOf("."));
                url += ".xml";
                if (!fileSet.contains(url)) {
                    writeDataForTrail(recordPath, "id=" + id + "的节点找不到对应的文件");
                }
            }
        }
        // 获取子节点
        @SuppressWarnings("unchecked")
        List<Element> list = element.elements();
        // 判断是否有子节点
        if (list != null && !list.isEmpty()) {
            list.forEach(el -> {
                // 递归
                try {
                    recordInexistenceWithFile(el, recordPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
