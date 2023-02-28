package com.example.studentappcustoms.model.dto;


import com.example.studentappcustoms.dao.entity.DepartmentEntity;

public class EmployeeDto {
    private int id;
    private String name;
    private String surname;
    private String dob;
    private DepartmentDto department;

    public EmployeeDto(int id, String name, String surname, String dob, DepartmentDto department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.department = department;
    }
    public EmployeeDto() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDob() {
        return dob;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dob='" + dob + '\'' +
                ", department=" + department +
                '}';
    }
}
