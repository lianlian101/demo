package com.mvn.test.util;

import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 创建时间: 2019年3月19日
 * 创建人 zhb
 * 说明: 格式校验
 */
public final class FormatCheck {

    /**
     * 手机号
     */
    public static final String PHONE = "^1(3|4|5|6|7|8)\\d{9}$";
    /**
     * 邮箱
     */
    public static final String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /**
     * 手机/邮箱 验证码
     */
    public static final String CODE = "^\\d{6}$";
    /**
     * 汉字/字母/数字
     */
    public static final String HANZIA0 = "^[\u4E00-\u9FA5A-Za-z0-9]+$";
    /**
     * 纯数字
     */
    public static final String NUMBER = "^[0-9]+$";
    /**
     * 性别
     */
    public static final String SEX = "^(男|女){1}$";
    
    public static boolean isPhone(String phone){
        return Pattern.matches(PHONE, phone);
    }
    
    public static boolean isEmail(String email){
        return Pattern.matches(EMAIL, email);
    }
    
    public static boolean isCode(String code){
        return Pattern.matches(CODE, code);
    }
    
    public static boolean isHanziA0(String str){
        return Pattern.matches(HANZIA0, str);
    }
    
    public static boolean isNumber(String number){
        return Pattern.matches(NUMBER, number);
    }
    
    public static boolean isSex(String sex){
        return Pattern.matches(SEX, sex);
    }
    
    @Test
    public void demo(){
        String string = "女";
        System.out.println(isSex(string));
    }
    
}
