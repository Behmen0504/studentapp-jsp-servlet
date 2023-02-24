package com.example.studentappcustoms.dao.entity;

import com.example.studentappcustoms.model.dto.DepartmentDto;

public class EmployeeEntity {
    private int id;
    private String name;
    private String surname;
    private String dob;
    private DepartmentEntity entity;

    public EmployeeEntity(int id, String name, String surname, String dob, DepartmentEntity dto) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.entity = dto;
    }
    public EmployeeEntity(String name, String surname, String dob, DepartmentEntity entity) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.entity = entity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public DepartmentEntity getEntity() {
        return entity;
    }

    public void setDto(DepartmentEntity entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dob='" + dob + '\'' +
                ", entity=" + entity +
                '}';
    }
}
