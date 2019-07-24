package com.mvn.test.dao;

import java.util.List;

import com.mvn.test.entity.Department;

public interface DepartmentDao {

    // 获取所有的部门及其子部门
    List<Department> listDepartment();
    
}
