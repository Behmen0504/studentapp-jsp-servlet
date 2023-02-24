package com.example.studentappcustoms.mapper;

import com.example.studentappcustoms.model.dto.EmployeeDto;

public class EmployeeMapper {

    public static EmployeeDto mapEntityToDto(EmployeeDto entity) {
        EmployeeDto employeeDto = new EmployeeDto(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getDob(),
                entity.getDepartment()
        );
        return null;
    }
}
