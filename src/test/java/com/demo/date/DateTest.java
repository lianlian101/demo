package com.demo.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateTest {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Test
    public void demo1() throws ParseException{
        Date date = new Date();
        System.out.println(date);
        System.out.println(sdf.format(date));
        Date parse = sdf.parse("2019-03-11 19:25:00");
        System.out.println(parse);
    }
    
    @Test
    public void demo07() throws Exception{
        String dataStr = "2019-03-20 15:30:29";
        long date = sdf.parse(dataStr).getTime();
        long time = new Date().getTime();
        System.out.println(time - date);
    }
    
    
}
