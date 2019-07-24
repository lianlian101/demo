package com.mvn.test.entity;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String depName;
    
    private Integer parentId;
    
    private Integer companyId;
    
    private List<Department> departments;
    
    private List<User> users;
    
    public Department() {
        super();
    }

    public Department(Integer id, String depName, Integer parentId, Integer companyId, List<Department> departments, List<User> users) {
        super();
        this.id = id;
        this.depName = depName;
        this.parentId = parentId;
        this.companyId = companyId;
        this.departments = departments;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Department [id=" + id + ", depName=" + depName + ", parentId=" + parentId + ", companyId=" + companyId + ", departments=" + departments + ", users=" + users + "]";
    }


}
