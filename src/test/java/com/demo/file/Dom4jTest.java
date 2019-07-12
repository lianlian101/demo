package com.demo.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class Dom4jTest {

    /**
     * 创建日期: 2019年6月12日 创建人: zhb 说明: 获取document对象 
     * 
     * @param path
     * @return
     * @throws DocumentException 
     */
    public static Document getDocument(String path) throws DocumentException{
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));
        return document;
    }
    
    /**
     * 创建日期: 2019年6月12日 创建人: zhb 说明: 对元素操作
     * 
     * @param document
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    public static void getElements(Document document, String path) throws Exception{
        // 父节点
        Element root = document.getRootElement();
        // 父节点下的所有子节点
        List<Element> elements = root.elements();
//        elements.forEach(el -> {
//            System.out.println(el.getName());
//        });
//        System.out.println("-------------------");
        // 父节点下的所有节点为id的节点
        List<Element> list = elements.parallelStream().filter(el->{
            return el.getName().equals("id");
        }).collect(Collectors.toList());
        
        // 模拟存在的数据
        List<Object> routmap = new ArrayList<>();
        String[] ss = {"001","002"};
        routmap.addAll(Arrays.asList(ss));
        // 删除不匹配的id
        Iterator<Element> it = list.iterator();
        while (it.hasNext()) {
            Element el = it.next();
            Object value = el.attribute("id").getData();
            if(!routmap.contains(value)){
                elements.remove(el);
                //el.getParent().remove(el);
            }
        }
        // 回写
        writeBack(document, path);
    }
    
    // 回写
    public static void writeBack(Document document, String path) throws Exception{
        OutputFormat format = OutputFormat.createPrettyPrint(); 
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path),format); 
        xmlWriter.write(document); 
        xmlWriter.close();
    }
    
    /**
     * 创建日期: 2019年6月12日 创建人: zhb 说明: 测试
     * 
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        String path = "D:/files/demo.xml";
        Document document = getDocument(path);
        getElements(document,path);
    }
    
    //================================================================================================
    
    private static List<String> list = new ArrayList<>(Arrays.asList("java","sql","js"));
    
    @Test
    public void demo() throws Exception{
        String path = "D:/files/demo.xml";
        Document document = getDocument(path);
        Element rootElement = document.getRootElement();
        delNode(rootElement);
        writeBack(document, path);
    }
    
    // 删除节点
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
                String name = element.attribute("name").getStringValue();
                if (name != null && !"".equals(name)) {
                    if (!list.contains(name)) {
                        element.getParent().remove(element);
                    }
                }
            }
        }
    }
    
}
