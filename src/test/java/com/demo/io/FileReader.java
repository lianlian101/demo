package com.demo.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReader {

    /**
     * 创建日期: 2019年5月30日 创建人: zhb 说明: BufferedReader 缓冲流
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.FileReader(new File("D:/read.txt")));
        String s;
        StringBuilder sb = new StringBuilder();
        while((s = br.readLine()) != null){
            sb.append(s);
        }
        br.close();
        String str = sb.toString();
        System.out.println(str);
    }
    
}
