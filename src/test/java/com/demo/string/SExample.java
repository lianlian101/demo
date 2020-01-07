package com.demo.string;

import java.io.Serializable;

public class SExample implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    Long id;
    
    String name;

    public SExample() {
        super();
    }

    public SExample(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Example [id=" + id + ", name=" + name + "]";
    }
    
    
}
