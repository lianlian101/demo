package com.mvn.test.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mvn.test.util.Captcha;
import com.mvn.test.util.Excel;
import com.mvn.test.util.FileUtil;

@Controller
@RequestMapping("/file")
public class FileController {

    /**
     * 创建日期: 2019年3月25日 创建人: zhb 说明:
     * 
     * @param file
     * @param request
     */
    @RequestMapping("/readExcel")
    @ResponseBody
    public void readExcel(MultipartFile file) {

        try {
            List<?> list = Excel.getExcelContent(file);
            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/verify")
    @ResponseBody
    public void verify(MultipartFile file){
        try {
            Map<String, Object> map = Excel.verify(file);
            if(null != map && map.isEmpty()){
                Set<Entry<String,Object>> set = map.entrySet();
                Iterator<Entry<String, Object>> iterator = set.iterator();
                if(iterator.hasNext()){
                    Entry<String, Object> entry = iterator.next();
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    System.out.println("错误的数据为: " + key + " == " + value);
                }
            }else{
                System.out.println("数据格式都正确");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 创建日期: 2019年4月2日 创建人: zhb 说明: 返回图片
     * 
     * @return
     * @throws IOException 
     */
    @RequestMapping("/getImage")
    @ResponseBody
    public String getImage(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception{
        /*response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);*/
        Captcha randomValidateCode = new Captcha();
        try {
            BufferedImage image = randomValidateCode.getRandcode(request, response,"qi32u");// 输出图片方法
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            Encoder encoder = Base64.getEncoder();
            byte[] encode = encoder.encode(outputStream.toByteArray());
            String str = new String(encode);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * 创建日期: 2019年4月8日 创建人: zhb 说明: 文件下载
     *
     */
    @RequestMapping("fileDownload")
    public void fileDownload(HttpServletRequest request, HttpServletResponse response){
        String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/excel/");
        try {
            FileUtil.fileDownload("Gatebim员工导入模板.xlsx", realPath, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
