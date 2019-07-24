package com.mvn.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvn.test.entity.Department;
import com.mvn.test.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    
    @RequestMapping("/listDepartment")
    @ResponseBody
    public List<Department> listDepartment(){
        return departmentService.listDepartment();
    }
    
}
