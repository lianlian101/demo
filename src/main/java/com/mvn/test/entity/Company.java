package com.mvn.test.entity;

import java.io.Serializable;
import java.util.List;

public class Company implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String companyName;
    
    private List<Department> departments;
    
    public Company() {
        super();
    }

    public Company(Integer id, String companyName, List<Department> departments) {
        super();
        this.id = id;
        this.companyName = companyName;
        this.departments = departments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Company [id=" + id + ", companyName=" + companyName + ", departments=" + departments + "]";
    }
    

}
