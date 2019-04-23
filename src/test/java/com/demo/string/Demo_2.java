package com.demo.string;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mvn.test.util.FormatCheck;

public class Demo_2 {

    @Test
    public void demo1() throws ParseException{
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date));
        Date parse = simpleDateFormat.parse("2019-03-11 19:25:00");
        System.out.println(parse);
    } 
    
    @Test
    public void demo2(){
        HashMap<String,String> map = new HashMap<>();
        //System.out.println(map.isEmpty());
        //map.put("key", "key");
        //map.put("value", "value");
        map.put("key", null);
        map.put("value", null);
        System.out.println(map.get("key")+"==>"+map.get("value"));
    }
    
    @Test
    public void demo3(){
        String phone = "13111111111";
        boolean b = FormatCheck.isPhone(phone);
        System.out.println(b);
    }
    
    @Test
    public void demo4(){
        String email = "";
        boolean b = FormatCheck.isEmail(email);
        System.out.println(b);
    }
    
    @Test
    public void demo5(){
        String code = "222222";
        boolean b = FormatCheck.isCode(code);
        System.out.println(b);
    }
    
    @Test
    public void demo6(){
        String str = "{}";
        JSONObject po = JSON.parseObject(str);
        String email = (String) po.get("email");
        System.out.println(email);
        System.out.println("{}".equals(str));
    }
    
    @Test
    public void demo07() throws Exception{
        String dataStr = "2019-03-20 15:30:29";
        long date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataStr).getTime();
        long time = new Date().getTime();
        System.out.println(time - date);
    }
    
    @Test
    public void demo08() throws Exception{
        String str = "test/demo////sss/";
        /*System.out.println(str.contains("/"));
        String[] split = str.split("/");
        System.out.println(split.length);*/
        System.out.println("============================");
        /*for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }*/
        String replace = str.replace("/", "");
        System.out.println(replace);
    }
    
    @Test
    public void demo09() throws Exception{
        String str = "123 ";
        
        System.out.println(FormatCheck.isNumber(str));
        
    }
    
    @Test
    public void demo10(){
        Integer a = 12;
        a.longValue();
        String str = "str";
        char c = str.charAt(1);
        System.out.println(c);
    }
    
    @Test
    public void demo11(){
        String str = "hello world";
        str = new StringBuilder(str).reverse().toString();
        System.out.println(str);
        
        int a = 2;
        int b = a << 2;
        System.out.println(b);
    }
    
}
