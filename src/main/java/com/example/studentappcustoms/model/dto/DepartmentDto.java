package com.example.studentappcustoms.model.dto;

public class DepartmentDto {
    private Integer id;
    private String name;

    public DepartmentDto(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public DepartmentDto() {

    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
