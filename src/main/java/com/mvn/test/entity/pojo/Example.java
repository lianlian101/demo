package com.mvn.test.entity.pojo;

import java.io.Serializable;

/**
 * @author zhb
 *
 */
public class Example implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String name;
    
    private Boolean type;

    public Example() {
        super();
    }

    public Example(Integer id, String name, Boolean type) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Example [id=" + id + ", name=" + name + ", type=" + type + "]";
    }
    
    
}
