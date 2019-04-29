package com.demo.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import com.mvn.test.util.Excel;
import com.mvn.test.util.FileUtil;

public class FileUtilTest {

    /**
     * 创建日期: 2019年3月26日
     * 创建人: zhb
     * 说明: excel校验
     * @throws IOException
     */
    @Test
    public void demo1() throws Exception {
        File file = new File("C:\\Users\\admin\\Desktop\\test.xlsx");
        MultipartFile multipartFile = FileUtil.fileToMultipartFile(file);
        Map<String, Object> map = Excel.verify(multipartFile);
        if(null != map && !map.isEmpty()){
            Set<Entry<String,Object>> set = map.entrySet();
            Iterator<Entry<String, Object>> iterator = set.iterator();
            while(iterator.hasNext()){
                Entry<String, Object> entry = iterator.next();
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println("    " + key + " == " + value);
            }
        }else{
            System.out.println("数据格式都正确");
        }
    }
    
}
