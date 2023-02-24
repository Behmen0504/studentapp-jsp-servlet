package com.example.studentappcustoms.dao.entity;

import com.example.studentappcustoms.model.dto.DepartmentDto;

public class EmployeeEntity {
    private int id;
    private String name;
    private String surname;
    private String dob;
    private DepartmentDto dto;

    public EmployeeEntity(int id, String name, String surname, String dob, DepartmentDto dto) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.dto = dto;
    }
    public EmployeeEntity(String name, String surname, String dob, DepartmentDto dto) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.dto = dto;
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

    public DepartmentDto getDto() {
        return dto;
    }

    public void setDto(DepartmentDto dto) {
        this.dto = dto;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dob='" + dob + '\'' +
                ", dto=" + dto +
                '}';
    }
}
