package com.demo.arr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

public class ArrayTest {

    /**
     * 数组的扩容
     */
    @Test
    public void demo1(){
        //原数组
        int[] arr = {2,1,5};
        System.out.println("原数组: "+ Arrays.toString(arr));
        System.out.println("\n-----------------------\n");
        //扩容为原来的2倍
        //方式一
        int[] arr2 = new int[arr.length * 2];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        System.out.println("新数组: " + Arrays.toString(arr2));
        System.out.println("\n-----------------------\n");
        //方式二
        int[] arr3 = Arrays.copyOf(arr, arr.length*2);
        System.out.println("新数组: " + Arrays.toString(arr3));
    }
    
    /**
     * 排序
     */
    @Test
    public void demo2(){
        int[] arr = {2,6,1,7,3};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
 
    
    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: Arrays.asList(list); list.toArray(str);
     *
     */
    @Test
    public void demo3() {
        String[] arrayStr = { "java", "sql", "html", "" };
        List<String> list = Arrays.asList(arrayStr);
        System.out.println(list);
        arrayStr[3] = "javascript";

        System.out.println(list);

        List<String> list2 = new ArrayList<>(list);
        list2.add("js");
        System.out.println(list2);

        String[] str = new String[list.size()];
        String[] array = list.toArray(str);
        System.out.println(Arrays.asList(array));

    }
    
    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: ArrayUtils.reverse(str);
     *
     */
    @Test
    public void demo4(){
        String[] str = {"java","mysql","javascript"};
        ArrayUtils.reverse(str);
        System.out.println(Arrays.asList(str));
    }
    
    /**
     * 创建日期: 2019年5月5日 创建人: zhb 说明: ArrayUtils.isEmpty(str);
     *
     */
    @Test
    public void demo5(){
        String[] arr = {};
        System.out.println(Arrays.asList(arr));
        boolean b = ArrayUtils.isEmpty(arr);
        System.out.println(b);
    }
    
    /**
     * 创建日期: 2019年6月24日 创建人: zhb 说明: 求交集
     *
     */
    @Test
    public void demo6(){
        Long[] a = {12L, 100L, 466L};
        Long[] b = {100L, 466L, 10L};
        Object[] array = Arrays.stream(a).distinct().filter(x -> Arrays.stream(b).anyMatch(y -> y.equals(x))).toArray();
        System.out.println(Arrays.asList(array));
    }
    
    /**
     * 创建日期: 2019年6月24日 创建人: zhb 说明: 空数组和数组有一个元素且为空字符串区别
     *
     */
    @Test
    public void demo7(){
        String[] x = {};
        String[] y = {""};
        System.out.println("x = " + Arrays.asList(x) + ", x.length = " + x.length);
        System.out.println("y = " + Arrays.asList(y) + ", y.length = " + y.length);
    }
    
    /**
     * 日期：2020年1月6日
     * 作者：zhb
     * 说明：判断指定元素是否存在于数组中
     * 
     */
    @Test
    public void demo8(){
        Integer[] x = {1,2,3,4,5,6};
        // 并集
        System.out.println(ArrayUtils.contains(x, 1));
    }
    
    /**
     * 日期：2020年1月6日
     * 作者：zhb
     * 说明：NumberUtils
     * 
     */
    @Test
    public void demo9(){
        // 判断字符串是否为整数
        System.out.println(NumberUtils.isDigits("0123"));
        //判断字符串是否为数字
        System.out.println(NumberUtils.isNumber("123.4"));
        // 求数组的最大值最小值
        int[] ints = {1,2,3};
        System.out.println(NumberUtils.max(ints));
        System.out.println(NumberUtils.min(ints));
    }
    
    /**
     * 日期：2020年1月6日
     * 作者：zhb
     * 说明：RandomUtils
     * 
     */
    @Test
    public void demo10(){
        // 生成一个int类型的随机整数
        System.out.println(RandomUtils.nextInt(0,100));
    }
    
    /**
     * 日期：2020年1月6日
     * 作者：zhb
     * 说明：StringUtils
     * 
     */
    @Test
    public void demo11(){
        String str = "aabbccaa";
        // 去除两端aa
        System.out.println(StringUtils.strip(str, "aa"));
        
        // 返回a在字符串中第三次出现的位置
        System.out.println(StringUtils.ordinalIndexOf(str, "a", 3));
        
        // 返回以aa开始aa结束的中间的字符串
        System.out.println(StringUtils.substringBetween(str, "aa", "aa"));
        
        // 重复拼接字符串，使用指定字符链接
        System.out.println(StringUtils.repeat(str, "-", 2));
        
        // 返回指定字符在字符串中出现的次数
        System.out.println(StringUtils.countMatches(str, "a"));
        
        // 判断字符串是否全大写
        System.out.println(StringUtils.isAllUpperCase(str));
        
        System.out.println(StringUtils.isAlpha(str)); // 全部由字母组成返回true
        System.out.println(StringUtils.isNumeric(str)); // 全部由数字组成返回true
        System.out.println(StringUtils.isAlphanumeric(str)); // 全部由字母或数字组成返回true
        System.out.println(StringUtils.isAlphaSpace(str)); // 全部由字母或空格组成返回true
        System.out.println(StringUtils.isWhitespace(str)); // 全部由空格组成返回true
        
        // 首字母大写或小写
        System.out.println(StringUtils.capitalize(str));
        System.out.println(StringUtils.uncapitalize(str));
        
        // 将数组或集合按照指定的字符拼接为字符串返回
        String[] strs = {"java","mysql","go"};
        System.out.println(StringUtils.join(strs, ","));
        List<String> strList = new ArrayList<>(Arrays.asList("java","mysql","go"));
        System.out.println(StringUtils.join(strList, ","));
        
        // 转换为html格式的代码
        System.out.println(StringEscapeUtils.escapeHtml4("Hello"));
    }
    
    /**
     * 日期：2020年1月7日
     * 作者：zhb
     * 说明：Objects.equals 和 Objects.deepEquals
     *      equals: 比较地址
     *      deepEquals: 比较内容
     * 
     */
    @Test
    public void demo12(){
        String[] a = {"java","sql","go"};
        String[] b = {"java","sql","go"};
        String[] c = b;
        System.out.println(Objects.equals(a, b));
        System.out.println(Objects.deepEquals(a, b));
        
        System.out.println(Objects.equals(b, c));
        System.out.println(Objects.equals(a, c));
    }
    
    /**
     * 日期：2020年1月7日
     * 作者：zhb
     * 说明：Arrays
     * 
     */
    @Test
    public void demo13(){
        String[] a = {"java", "sql", "go", "java"};
        // 判断元素是否存在于数组中，存在返回元素第一次在数组中出现的位置；不存在返回-1
        System.out.println(Arrays.binarySearch(a, "java"));
        
        String[] b = {"java", "sql", "go", "java"};
        // 判断连个数组的内容是否相同
        System.out.println(Arrays.equals(a, b));
        
//        Arrays.fill(b, "m");
//        System.out.println(Arrays.asList(b));
        
        // 排序
        Integer[] c = {1,0,9,8};
        Arrays.sort(c); // 默认升序
        Arrays.sort(c, (x,y)->y-x); // 给定条件降序
        
        // 返回数组的字符串表达形式
        System.out.println(Arrays.toString(a));
    }
    
    
}
