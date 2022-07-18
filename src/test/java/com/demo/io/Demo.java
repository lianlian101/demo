package com.demo.io;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Test;

import net.coobird.thumbnailator.Thumbnails;

public class Demo {

    /**
     * 创建日期: 2019年5月30日 创建人: zhb 说明: BufferedReader 缓冲流
     */
    public void demo() {
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(new File("D:/read.txt")));
            String s;
            StringBuilder sb = new StringBuilder();
            while((s = br.readLine()) != null){
                sb.append(s);
            }
            br.close();
            String str = sb.toString();
            System.out.println(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * BufferedImage格式图片下载到本地
     * 
     * @date 2021-04-01
     * @author zhb
     */
    @Test
    public void downloadImgByBufferedImage() {
        try {
            String url = "https://fastgw-ali.ys7.com/1/capture/2021/2/25/36bzks6i7gweuo7sxst9ijt34.jpg?"
                    + "Expires=1614324380&OSSAccessKeyId=LTAIzI38nEHqg64n&Signature=BaZJmr"
                    + "%2BW%2B6rtYk%2FX0A4X3Bpc6qE%3D&bucket=ezviz-fastdfs-gateway";
            BufferedImage bufferedImage = Thumbnails.of(new URL(url)).size(1920, 1080).asBufferedImage();

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", os);
            InputStream input = new ByteArrayInputStream(os.toByteArray());

            FileOutputStream fos = new FileOutputStream(new File("D://ttt.jpg"));
            byte[] bs = new byte[1024];
            while (input.read(bs) != -1) {
                fos.write(bs);
            }
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
