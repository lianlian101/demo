package com.demo.arr;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    public static List<Category> cates;
    private static List<Category> tmpCates = new ArrayList<Category>();

    public static void main(String[] args) {

        init();
        List<Category> cList = tree(cates, 0, 0);
        for (Category c : cList) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < c.getLevel(); i++) {
                sb.append("--");
            }
            System.out.println(sb.toString() + c.getName());
        }

    }

    public static List<Category> tree(List<Category> cs, Integer pid, Integer level) {
        for (Category c : cs) {
            if (c.getParentId() == pid) {
                c.setLevel(level);
                tmpCates.add(c);
                tree(cs, c.getId(), level + 1);
            }
        }
        return tmpCates;
    }

    public static void init() {
        cates = new ArrayList<Category>();
        Category c1 = new Category();
        c1.setId(1);
        c1.setName("广东");
        c1.setParentId(0);
        cates.add(c1);

        c1 = new Category();
        c1.setId(2);
        c1.setName("陕西");
        c1.setParentId(0);
        cates.add(c1);

        c1 = new Category();
        c1.setId(3);
        c1.setName("四川");
        c1.setParentId(0);
        cates.add(c1);

        c1 = new Category();
        c1.setId(5);
        c1.setName("深圳");
        c1.setParentId(1);
        cates.add(c1);

        c1 = new Category();
        c1.setId(6);
        c1.setName("陕西");
        c1.setParentId(2);
        cates.add(c1);

        c1 = new Category();
        c1.setId(8);
        c1.setName("成都");
        c1.setParentId(3);
        cates.add(c1);

        c1 = new Category();
        c1.setId(9);
        c1.setName("宝鸡");
        c1.setParentId(2);
        cates.add(c1);
        
        c1 = new Category();
        c1.setId(10);
        c1.setName("哈哈哈");
        c1.setParentId(9);
        cates.add(c1);
        
        c1 = new Category();
        c1.setId(11);
        c1.setName("呵呵呵");
        c1.setParentId(6);
        cates.add(c1);
        
        c1 = new Category();
        c1.setId(12);
        c1.setName("哈");
        c1.setParentId(10);
        cates.add(c1);

    }

}
