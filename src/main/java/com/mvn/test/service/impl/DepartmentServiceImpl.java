package com.mvn.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvn.test.dao.DepartmentDao;
import com.mvn.test.entity.Department;
import com.mvn.test.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;
    
    @Override
    public List<Department> listDepartment() {
        return departmentDao.listDepartment();
    }

}
