package com.demo.util;

import org.junit.Test;

import com.mvn.test.util.FormatCheck;

public class FormatCheckDemo {

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: 手机验证
     *
     */
    @Test
    public void demo3(){
        String phone = "13111111111";
        boolean b = FormatCheck.isPhone(phone);
        System.out.println(b);
    }
    
    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: 手机号验证
     *
     */
    @Test
    public void demo4(){
        String email = "";
        boolean b = FormatCheck.isEmail(email);
        System.out.println(b);
    }
    
    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: 验证码验证 6位纯数字
     *
     */
    @Test
    public void demo5(){
        String code = "222222";
        boolean b = FormatCheck.isCode(code);
        System.out.println(b);
    }
    
    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: 纯数字验证 9位
     * 
     * @throws Exception
     */
    @Test
    public void demo09() throws Exception{
        String str = "123 ";
        System.out.println(FormatCheck.isNumber(str));
    }
    
    
}
