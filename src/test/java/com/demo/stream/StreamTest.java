package com.demo.stream;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.mvn.test.entity.User;
import com.mvn.test.entity.pojo.Example;

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
        
        // 根据条件过滤
        List<Integer> filterNums = new ArrayList<>(Arrays.asList(1,3,5,7,9));
        // 移除符合条件的元素
        filterNums.removeIf(x -> x > 5);
//        filterNums.removeIf(new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer t) {
//                return t > 5;
//            }
//        });
        System.out.println(filterNums);

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
    @SuppressWarnings("unchecked")
    @Test
    public void demo8(){
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "张三", "123", new Date(),1));
        list.add(new User(2, "李四", "123", new Date(),1));
        list.add(new User(3, "王五", "123", new Date(),1));
        list.add(new User(1, "李四", "123", new Date(),1));
        list.add(new User(1, "张三", "123", new Date(),1));
        
        // 方式一
        ArrayList<User> collect = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                // 利用  TreeSet 的排序去重构造函数来达到去重元素的目的
                () -> new TreeSet<>(Comparator.comparing(User::getId))), ArrayList::new));
        // 方式二
        List<User> filter = list.stream().filter(distinctByKey(b -> b.getId())).collect(Collectors.toList());
        
        System.out.println(collect);
        System.out.println(filter);
      
        // 根据多个属性去重
        ArrayList<User> filter2 = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(u -> u.getId() + ";" + u.getUsername()))), ArrayList::new));
        System.out.println(filter2);
        
    }
    
    // 去重方法
    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    } 

    /**
     * 创建日期: 2019年5月22日 创建人: zhb 说明: 交集、差集
     *
     */
    @Test
    public void demo9(){
        ArrayList<Long> a = new ArrayList<>();
        a.add(10L);
        a.add(12L);
        a.add(299L);
        a.add(300L);
        ArrayList<Long> b = new ArrayList<>();
        b.add(12L);
        b.add(299L);
        b.add(400L);
        List<Long> intersection = a.parallelStream().filter(x->b.contains(x)).collect(Collectors.toList());
        System.out.println(intersection);
        List<Long> intersection2 = a.parallelStream().filter(x->!intersection.contains(x)).collect(Collectors.toList());
        System.out.println(intersection2);
    }
    
    /**
     * 日期：2020年1月4日
     * 作者：zhb
     * 说明：对象集合转map集合
     *
     */
    @Test
    public void demo10(){
        List<Example> list = getExamples();
        Map<Integer, String> map = list.parallelStream() // 创建一个并行流
            .filter(e->e != null) // 过滤为空的对象
            //.collect(Collectors.toMap(Example::getId, Example::getName));
            //.collect(Collectors.toMap(e -> e.getId(), e -> e.getName()));
            .collect(Collectors.toMap(e -> {return e.getId();}, e -> {return e.getName();})); // 转换为map集合
        // 遍历集合
        map.forEach((k,v)->{
            System.out.println(k + "->" + v);
        });
    }
  
    /**
     * 日期：2020年1月4日
     * 作者：zhb
     * 说明：if else 条件转换  ofNullable ofElseGet
     * 
     */
    @Test
    public void demo11(){
        List<Example> list = getExamples();
        List<Example> collect = Optional.ofNullable(list) // 判断不是空继续往下执行
                .orElseGet(ArrayList::new) // 否则返回该方法中的值
                .parallelStream() // 创建一个并行流
                .filter(e-> e.getName().equals("张三")) // 过滤
                .collect(Collectors.toList()); // 将结果转换为list
        System.out.println(collect);
    }
    
    /**
     * 日期：2020年1月4日
     * 作者：zhb
     * 说明：将list集合中的数据按照指定的字符拼接为一个字符串
     * 
     */
    @Test
    public void demo12() {
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("mysql");
        list.add("js");
        String collect = list.parallelStream().collect(Collectors.joining(","));
        System.out.println(collect);
    }
    
    /**
     * 日期：2020年1月4日
     * 作者：zhb
     * 说明：返回特定结果集
     * 
     */
    @Test
    public void demo13(){
        List<String> list = new ArrayList<>(Arrays.asList("0","1","2","3","4","5","6","7","8","9"));
        List<String> collect = list.parallelStream() // 创建一个并行流
            .skip(3) // 扔掉前面三个元素
            .limit(2) // 返回扔掉元素后的结果中的前两个元素
            .collect(Collectors.toList()); // 转换为集合
        
        System.out.println(collect);
    }
    
    /**
     * 日期：2020年1月4日
     * 作者：zhb
     * 说明：对int类型集合元素进行排序
     * 
     */
    @Test
    public void demo14(){
        List<Integer> list = new ArrayList<>(Arrays.asList(3,2,7,5,9,1));
        List<Integer> collect = list.parallelStream().sorted().collect(Collectors.toList()); // 默认升序
        List<Integer> collect2 = list.parallelStream().sorted((v1,v2)->v2-v1).collect(Collectors.toList());  // 降序
        System.out.println(collect);
        System.out.println(collect2);
    }
    
    /**
     * 日期：2020年1月4日
     * 作者：zhb
     * 说明：匹配规则
     * 
     */
    @Test
    public void demo15(){
        List<Integer> list = new ArrayList<>(Arrays.asList(3,2,7,5,9,1));
        boolean b = list.parallelStream().allMatch(c -> c == 1); // 集合中所有元素都符合条件返回true
        System.out.println(b);
        boolean b2 = list.parallelStream().anyMatch(c -> c == 1); // 集合中只要有一个元素符合条件返回true
        System.out.println(b2);
        boolean b3 = list.parallelStream().noneMatch(c -> c == 0); // 集合中所有元素都不符合条件返回true
        System.out.println(b3);
    }
    
    /**
     * 日期：2020年1月4日
     * 作者：zhb
     * 说明：对象集合按条件进行分组
     * 
     */
    @Test
    public void demo16(){
        List<Example> list = getExamples();
        Map<Integer, List<Example>> collect = list.parallelStream()
            .collect(Collectors.groupingBy(Example::getId));
        
        System.out.println(collect);
    }
    
    /**
     * 日期：2020年1月4日
     * 作者：zhb
     * 说明：字符串转字符
     * 
     */
    @Test
    public void demo17() {
        // 写法一
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "zhangsan"));
        ArrayList<Character> cs = new ArrayList<>();
        list.parallelStream().map(String::toCharArray)
            .forEach(s -> {
                for (Character c : s) {
                    cs.add(c);
                }
            });

        System.out.println(cs);

        // 写法二
        List<Character> cs2 = list.stream().map(String::toCharArray)
                // 对转换的char[]数组整合
                .flatMapToInt(chars -> CharBuffer.wrap(chars).chars())
                // 转换为char类型
                .mapToObj(e -> (char) e)
                // 转为集合存储
                .collect(Collectors.toList());
        
        System.out.println(cs2);
    }
    
    /**
     * 日期：2020年1月4日
     * 作者：zhb
     * 说明：summaryStatistics配合Stream的使用
     * 
     */
    @Test
    public void demo18(){
        List<Example> list = getExamples();
        IntSummaryStatistics ss = list.parallelStream().mapToInt(Example::getId).summaryStatistics();
        // 最小值
        System.out.println("最小值: " + ss.getMin());
        // 最大值
        System.out.println("最大值: " + ss.getMax());
        // 求和
        System.out.println("和: " + ss.getSum());
        // 计数
        System.out.println("计数: " + ss.getCount());
        // 平均值
        System.out.println("平均值: " + ss.getAverage());
    }
    
    /**
     * 日期：2020年1月4日
     * 作者：zhb
     * 说明：归约
     * 
     */
    @Test
    public void demo19(){
        List<Example> list = getExamples();
        // 求和
        Integer sum = list.parallelStream().map(Example::getId).reduce(Integer::sum).get();
        System.out.println("求和: " + sum);
        // 最小值
        Integer min = list.parallelStream().map(Example::getId).reduce(Integer::min).get();
        System.out.println("最小值: " + min);
        // 最大值
        Integer max = list.parallelStream().map(Example::getId).reduce(Integer::max).get();
        System.out.println("最大值: " + max);
        // 计数
        long count = list.parallelStream().map(Example::getId).count();
        System.out.println("计数: " + count);
        // 平均值
        double average = list.parallelStream().mapToInt(Example::getId).average().getAsDouble();
        System.out.println("平均值: " + average);
    }
    
    /**
     * 日期：2020年1月4日
     * 作者：zhb
     * 说明：Integer类型集合的最大值最小值
     * 
     */
    @Test
    public void demo20() {
        List<Integer> list = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9)); //Stream.of(0,1,2,3,4,5,6,7,8,9).collect(Collectors.toList())
        Integer max = list.parallelStream().max(Comparator.comparing(i -> i)).get();
        System.out.println("最大值: " + max);
        Integer min = list.parallelStream().min(Comparator.comparing(i -> i)).get();
        System.out.println("最小值: " + min);
        
        List<String> strs = new ArrayList<>(Arrays.asList("java","mysql","html","js","css"));
        int asMax = strs.parallelStream().mapToInt(s -> s.length()).max().getAsInt();
        System.out.println("集合中长度最长的元素的长度: " + asMax);
        
        List<String> strs2 = new ArrayList<>(Arrays.asList("java","mysql","html","js","css"));
        int asMin = strs2.parallelStream().mapToInt(s -> s.length()).min().getAsInt();
        System.out.println("集合中长度最短的元素的长度: " + asMin);
        
        List<String> collect = strs.parallelStream().filter(s -> s.length() == asMax).collect(Collectors.toList());
        System.out.println("集合中等于最长元素长度的元素: " + collect);
    }
    
    // 获取Example对象集合
    private List<Example> getExamples(){
        List<Example> list = new ArrayList<>();
        Example example = new Example(1, "张三", false);
        Example example2 = new Example(2, "李四", false);
        Example example3 = new Example(3, "王五", false);
        list.add(example);
        list.add(example2);
        list.add(example3);
        return list;
    }
    
    
}
