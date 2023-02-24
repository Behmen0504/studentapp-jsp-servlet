package com.example.studentappcustoms.mapper;

import com.example.studentappcustoms.dao.entity.DepartmentEntity;
import com.example.studentappcustoms.dao.entity.EmployeeEntity;
import com.example.studentappcustoms.model.dto.DepartmentDto;
import com.example.studentappcustoms.model.dto.EmployeeDto;

public class EmployeeMapper {

    public static EmployeeDto mapEntityToDto(EmployeeEntity entity) {
        EmployeeDto employeeDto = new EmployeeDto(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getDob(),
                mapEntityToDepartmentDto(entity.getEntity())
        );
        return null;
    }


    public static DepartmentDto mapEntityToDepartmentDto(DepartmentEntity entity){
        DepartmentDto departmentDto = new DepartmentDto();
        if(entity!=null){
            departmentDto.setId(entity.getId());
            departmentDto.setName(entity.getName());
        }
        return departmentDto;

    }
}
