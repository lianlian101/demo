package com.demo.arr;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.mvn.test.entity.Department;

public class DepartmentTree {
    
    /**
     * 初始化数据
     * 
     * @return
     */
    List<Department> init(){
        List<Department> list = new ArrayList<Department>();
        list.add(new Department(1, "设计", 0, 1));
        list.add(new Department(2, "开发", 0, 1));
        list.add(new Department(3, "后端", 2, 1));
        list.add(new Department(4, "前端", 2, 1));
        list.add(new Department(5, "java", 3, 1));
        list.add(new Department(6, "sql", 3, 1));
        list.add(new Department(7, "美工", 1, 1));
        return list;
    }
    
    /**
     * 查询所有分类，按顺序显示， 显示出树状
     * @return
     */
    public List<Department> getDepartments() {
        // 所有的部门信息
        List<Department> departments = init();
        // 所有的顶级父级部门信息
        List<Department> roots = departments.stream().filter(department -> (department.getParentId() == 0)).collect(Collectors.toList());
        // 升序排序
        roots.sort(new Comparator<Department>() {
            @Override
            public int compare(Department o1, Department o2) {
                return o1.getId() > o2.getId() ? 1 : -1;
            }
        });
        // 所有子类
        List<Department> subs = departments.stream().filter(department -> (department.getParentId() != 0)).collect(Collectors.toList());
        // 递归构建结构化数据
        roots.forEach(root -> buildSubs(root, subs));
        return roots;
    }

    /**
     * 递归构建
     * @param parent 父级
     * @param subs 所有子类
     */
    private void buildSubs(Department parent, List<Department> subs) {
        List<Department> children = subs.stream().filter(sub -> (sub.getParentId() == parent.getId())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(children)) {//有子类的情况
            parent.setDepartments(children);
            children.forEach(child -> buildSubs(child, subs));//再次递归构建
        }
    }
    
    /**
     * 测试
     * 
     * @param args
     */
    public static void main(String[] args) {
        DepartmentTree departmentTree = new DepartmentTree();
        List<Department> departments = departmentTree.getDepartments();
        String js = JSON.toJSONString(departments);
        System.out.println(js);
    }
    
}
