package com.example.studentappcustoms.dao.entity;

public class DepartmentEntity {
    private Integer id;
    private String name;

    public DepartmentEntity(Integer id,String name) {
        this.id = id;
        this.name = name;
    }
    public DepartmentEntity(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
