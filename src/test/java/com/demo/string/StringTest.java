package com.demo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class StringTest {

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: isEmpty()
     *
     */
    @Test
    public void demo1() {
        String str = "zhangsan";
        boolean b = str.isEmpty();
        System.out.println(b);
    }

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: subSequence()
     *
     */
    @Test
    public void demo2() {
        String str = "zhangsan";
        CharSequence sequence = str.subSequence(0, 3);
        System.out.println(sequence.toString());
        System.out.println(str);
    }

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: substring()
     *
     */
    @Test
    public void demo3() {
        String str = "zhangsan";
        String substring = str.substring(0, 3);
        System.out.println(substring);
        System.out.println(str);
    }

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: equals(); strim()
     *
     */
    @Test
    public void demo4() {
        String str = "zhangsan ";
        System.out.println("zhangsan".equals(str));
        System.out.println("zhangsan".equals(str.trim()));
    }

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: replace();
     * 
     * @throws Exception
     */
    @Test
    public void demo5() throws Exception {
        String str = "test/demo////sss/";
        /*
         * System.out.println(str.contains("/")); String[] split =
         * str.split("/"); System.out.println(split.length);
         */
        System.out.println("============================");
        /*
         * for (int i = 0; i < split.length; i++) {
         * System.out.println(split[i]); }
         */
        String replace = str.replace("/", "");
        System.out.println(replace);
    }

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: charAt();
     *
     */
    @Test
    public void demo6() {
        Integer a = 12;
        a.longValue();
        String str = "str";
        char c = str.charAt(1);
        System.out.println(c);
    }

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: reverse();
     *
     */
    @Test
    public void demo7() {
        String str = "hello world";
        str = new StringBuilder(str).reverse().toString();
        System.out.println(str);

        int a = 2;
        int b = a << 2;
        System.out.println(b);
    }

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: StringUtils.join(arr, char);
     *
     */
    @Test
    public void demo8() {
        String str = "[1,2,3]";
        JSONArray array = JSON.parseArray(str);
        String s = StringUtils.join(array, ",");
        System.out.println(s);
        String ss = Arrays.toString(s.split(","));
        System.out.println(ss);
    }

    /**
     * 创建日期: 2019年5月24日 创建人: zhb 说明: ArrayUtils.contains(arr, value)
     *
     */
    @Test
    public void demo9() {
        String str = "0507002update,0507003del,0507005attendCircle,0507004nameList,0507003validatedInfo,0507,0507001add,1001004del,1005003del,1001003update,1005002update";
        String[] ss = str.split(",");
        boolean b = ArrayUtils.contains(ss, "1001003update");
        System.out.println(b);
    }

    /**
     * 创建日期: 2019年7月9日 创建人: zhb 说明: String.join(char, arr)
     *
     */
    @Test
    public void demo10(){
        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("mysql");
        list.add("javascript");
        String str = String.join(",", list);
        System.out.println(str);
    }
    
    /**
     * 日期：2019年10月22日
     * 作者：zhb
     * 说明：StringUtils.isBlank(char)
     * 
     */
    @Test
    public void demo11(){
        String str = " abc ";
        //System.out.println(StringUtils.isEmpty(str));
        System.out.println(StringUtils.isBlank(str));
    }
    
    
}
