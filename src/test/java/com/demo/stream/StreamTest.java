package com.demo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.mvn.test.entity.User;

public class StreamTest {

    /**
     * 创建日期: 2019年4月22日 创建人: zhb 说明: 对集合中的每个元素平方或根据条件过滤
     *
     */
    @Test
    public void demo1() {

        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());

        System.out.println(squareNums);

        // 留下偶数
        Integer[] sixNums = { 1, 2, 3, 4, 5, 6 };
        Integer[] evens = Stream.of(sixNums).filter(n -> n % 2 == 0).toArray(Integer[]::new);

        System.out.println(Arrays.asList(evens));

    }

    /**
     * 创建日期: 2019年4月22日 创建人: zhb 说明: 统计集合中重复的元素并计算出出现的次数，返回map的形式
     *
     */
    @Test
    public void demo2() {
        // 定义一个100元素的集合，包含A-Z
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf((char) ('A' + Math.random() * ('Z' - 'A' + 1))));
        }
        // System.out.println(list);
        // 统计集合重复元素出现次数，并且去重返回hashmap
        Map<String, Long> map = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
        // 由于hashmap无序，所以在排序放入LinkedHashMap里(key升序)
        Map<String, Long> sortMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(e -> sortMap.put(e.getKey(), e.getValue()));
        System.out.println(sortMap);
        // 获取排序后map的key集合
        List<String> keys = new LinkedList<>();
        sortMap.entrySet().stream().forEachOrdered(e -> keys.add(e.getKey()));
        System.out.println(keys);
        // 获取排序后map的value集合
        List<Long> values = new LinkedList<>();
        sortMap.entrySet().stream().forEachOrdered(e -> values.add(e.getValue()));
        System.out.println(values);
    }

    /**
     * 创建日期: 2019年4月22日 创建人: zhb 说明: 去重
     *
     */
    @Test
    public void demo3() {
        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("mysql");
        list.add("mysql");
        list.add("java");
        list.add("js");
        list.add("js");
        list.add("go");
        List<String> list2 = list.stream().distinct().collect(Collectors.toList());
        System.out.println(list2);
    }

    /**
     * 创建日期: 2019年4月22日 创建人: zhb 说明: 将集合中的所有元素变为大写
     *
     */
    @Test
    public void demo4() {
        List<String> collected = new ArrayList<>();
        collected.add("alpha");
        collected.add("beta");
        collected = collected.stream().map(string -> string.toUpperCase()).collect(Collectors.toList());
        System.out.println(collected);

        collected = collected.stream().map(String::toUpperCase).collect(Collectors.toCollection(ArrayList::new));// 注意发生的变化
        System.out.println(collected);
    }

    /**
     * 创建日期: 2019年4月22日 创建人: zhb 说明: 计算重复的元素以及出现的次数，返回map的形式
     *
     */
    @Test
    public void demo5() {
        // 定义一个100元素的集合，包含A-Z
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf((char) ('A' + Math.random() * ('Z' - 'A' + 1))));
        }
        // 统计每个元素出现的个数
        Map<String, Long> map = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);

        // 统计指定元素的重复的个数
        String[] arrayStr = { "java", "java", "mysql", "js", "mysql" };
        long count = Stream.of(arrayStr).filter(str -> "java".equals(str)).count();
        System.out.println(count);
    }

    /**
     * 创建日期: 2019年4月22日 创建人: zhb 说明: 按指定条件筛选，按指定字符合并为字符串
     *
     */
    @Test
    public void demo6() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 去除为空的元素
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选列表: " + filtered);
        // 将集合变为由指定字符拼接后的字符串
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println("合并字符串: " + mergedString);
    }
    
    /**
     * 创建日期: 2019年4月22日 创建人: zhb 说明: 统计
     *
     */
    @Test
    public void demo7(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
         
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
    
    /**
     * 创建日期: 2019年5月22日 创建人: zhb 说明: 根据对象的指定属性去重
     *
     */
    @Test
    public void demo8(){
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "张三", "123", new Date()));
        list.add(new User(2, "李四", "123", new Date()));
        list.add(new User(3, "王五", "123", new Date()));
        list.add(new User(1, "张三", "123", new Date()));
        list.add(new User(1, "张三", "123", new Date()));
        
        // 方式一
        @SuppressWarnings("unchecked")
        ArrayList<User> collect = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                // 利用  TreeSet 的排序去重构造函数来达到去重元素的目的
                () -> new TreeSet<>(Comparator.comparing(User::getId))), ArrayList::new));
        // 方式二
        List<User> filter = list.stream().filter(distinctByKey(b -> b.getId())).collect(Collectors.toList());
        
        System.out.println(collect);
        System.out.println(filter);
      
    }
    
    // 去重方法
    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    } 

    
}
