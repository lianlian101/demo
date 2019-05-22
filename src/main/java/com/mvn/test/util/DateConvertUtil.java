package com.mvn.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertUtil {

    private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME);
    
    /**
     * 创建日期: 2019年5月22日 创建人: zhb 说明: 时间转字符串
     * 
     * @param date 时间
     * 
     * @return string  时间的字符串形式（yyyy-MM-dd HH:mm:ss）
     */
    public static String dateToStr(Date date){
        String format = sdf.format(date);
        return format;
    }
    
    /**
     * 创建日期: 2019年5月22日 创建人: zhb 说明: 字符串转时间
     * 
     * @param str 时间的字符串形式（yyyy-MM-dd HH:mm:ss）
     * 
     * @return date
     * @throws ParseException 
     */
    public static Date strToDate(String str) throws ParseException{
        Date date = sdf.parse(str);
        return date;
    }
    
    /**
     * 创建日期: 2019年5月22日 创建人: zhb 说明: 获取当前时间的毫秒值
     * 
     * @return
     */
    public static long getCurrentTimeMillis(){
        return System.currentTimeMillis();
    }
    
}
