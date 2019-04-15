package com.mvn.test.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUtil {

    /**
     * 创建日期: 2019年3月26日 创建人: zhb 说明: file 转 multipartfile
     * 
     * @return
     */
    public static MultipartFile fileToMultipartFile(File file) throws Exception {
        FileItem fileItem = new DiskFileItem(file.getName(), Files.probeContentType(file.toPath()), false,
                file.getName(), (int) file.length(), file.getParentFile());
        byte[] buffer = new byte[1024];
        int n;
        try (InputStream inputStream = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
            while ((n = inputStream.read(buffer, 0, 1024)) != -1) {
                os.write(buffer, 0, n);
            }
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            return multipartFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建日期: 2019年4月8日 创建人: zhb 说明: 文件下载
     * @throws Exception 
     *
     */
    public static void fileDownload(String fileName, String realPath, HttpServletResponse response) throws Exception{
        if (fileName != null) {
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/octet-stream");// 
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
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
        }
    }
    
}
