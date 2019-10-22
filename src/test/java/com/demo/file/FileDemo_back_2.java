package com.demo.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class FileDemo_back_2 {

    /**
     * 获取系统路径分隔符
     */
    private static final String SEPA = File.separator;

    /**
     * 要检索的文件夹的路径
     */
    private static final String PATH = "D:/files/project";

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
        fileOperation(PATH);
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
     * @param path
     *            文件路径
     * @param content
     *            内容
     * @throws IOException
     */
    public static void writeData(String path, String content) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            file.mkdirs();
        }
        FileWriter fw = new FileWriter(file, true);
        fw.write(content + "\r\n");
        fw.flush();
        fw.close();
    }

    @Test
    public void demo() throws IOException {
        String path = "D:\\files\\trail.txt";
        String content = "哈哈呵呵";
        writeData(path, content);
    }

    @Test
    public void demo2() throws URISyntaxException, IOException {
        String property = System.getProperty("user.dir");
        // String path =
        // FileDemo.class.getClassLoader().getResource("src/main/java").toURI().getPath();
        System.out.println(property);
        File file = new File(property + "/src/main/java/com/d/demo/demo.txt");
        if (!file.exists()) {
            file.createNewFile();
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
     * 获取父节点元素对象
     * 
     * @return
     */
    public static Element getRootElement(Document document){
        return document.getRootElement();
    }
    
    /**
     * 对元素操作
     * 
     * @param document
     * @param path
     *            文件路径
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static void elementOperation(Document document, String path) throws IOException {
        // 父节点
        Element root = document.getRootElement();
        // 父节点下的所有子节点
        List<Element> elements = root.elements();
        // elements.forEach(el -> {
        // System.out.println(el.getName());
        // });
        // System.out.println("-------------------");
        // 父节点下的所有节点为id的节点
        List<Element> list = elements.parallelStream().filter(el -> {
            return el.getName().equals("id");
        }).collect(Collectors.toList());

        // 模拟存在的数据
        List<Object> routmap = new ArrayList<>();
        String[] ss = { "001", "002" };
        routmap.addAll(Arrays.asList(ss));
        // 删除不匹配的id
        Iterator<Element> it = list.iterator();
        while (it.hasNext()) {
            Element el = it.next();
            Object value = el.attribute("id").getData();
            if (!routmap.contains(value)) {
                elements.remove(el);
            }
        }
        // 回写
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }
    
    private static Set<String> set = new HashSet<String>();
    
    /**
     * 获取所有的url的值 
     * 
     * @param element
     */
    @SuppressWarnings("unchecked")
    public static void getElement(Element element){
        // 保存module节点中url属性的值
        String value = element.attribute("url").getStringValue();
        set.add(value);
        // 获取子节点
        List<Element> list = element.elements();
        // 判断是否有子节点
        if(list != null && !list.isEmpty()){
            list.forEach(el->{
                // 递归
                getElement(el);
            });
        }
    }
    
    @Test
    public void demo3() throws DocumentException{
        String path = "D:/files/routmap.xml";
        Document document = getDocument(path);
        Element root = getRootElement(document);
        getElement(root);
        System.out.println(set);
    }

}
