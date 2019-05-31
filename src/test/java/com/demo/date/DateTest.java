package com.demo.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
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
    public void demo02() throws Exception{
        String dataStr = "2019-03-20 15:30:29";
        long date = sdf.parse(dataStr).getTime();
        long time = new Date().getTime();
        System.out.println(time - date);
    }
    
    //=======================================LocalDateTime=============================================
    
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Test
    public void demo03(){
        LocalDateTime now = LocalDateTime.now();
        String format = dtf.format(now);
        System.out.println(format);
    }
    
    @Test
    public void demo04(){
        LocalDateTime parse = LocalDateTime.parse("2019-01-10 13:13:23", dtf);
        System.out.println(parse);
        String format = dtf.format(parse);
        System.out.println(format);
    }
    
    @Test
    public void demo05(){
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);
        System.out.println(System.currentTimeMillis());
        Instant instant = clock.instant();
        Date date = Date.from(instant);
        System.out.println(date);
    }
    
    
}
