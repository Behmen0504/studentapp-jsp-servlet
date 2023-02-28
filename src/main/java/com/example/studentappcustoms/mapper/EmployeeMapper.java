package com.example.studentappcustoms.mapper;

import com.example.studentappcustoms.dao.entity.DepartmentEntity;
import com.example.studentappcustoms.dao.entity.EmployeeEntity;
import com.example.studentappcustoms.model.dto.DepartmentDto;
import com.example.studentappcustoms.model.dto.EmployeeDto;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {

    public static List<EmployeeDto> mapEntitiesToDtos(List<EmployeeEntity> employeeEntityList) {
        List<EmployeeDto> employeeDtoList = employeeEntityList.stream().map(employee->mapEntityToDto(employee)).collect(Collectors.toList());
        return employeeDtoList;
    }

    public static EmployeeDto mapEntityToDto(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = new EmployeeDto(
                employeeEntity.getId(),
                employeeEntity.getName(),
                employeeEntity.getSurname(),
                employeeEntity.getDob(),
                DepartmentMapper.mapEntityToDepartmentDto(employeeEntity.getEntity())
        );
        return employeeDto;
    }

}
