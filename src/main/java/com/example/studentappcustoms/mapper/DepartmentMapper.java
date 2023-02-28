package com.example.studentappcustoms.mapper;

import com.example.studentappcustoms.dao.entity.DepartmentEntity;
import com.example.studentappcustoms.model.dto.DepartmentDto;

public class DepartmentMapper {
    public static DepartmentDto mapEntityToDto(DepartmentEntity entity) {
        DepartmentDto departmentDto = new DepartmentDto(
                entity.getId(),
                entity.getName()
        );
        return null;
    }

    public static DepartmentDto mapEntityToDepartmentDto(DepartmentEntity departmentEntity){
        DepartmentDto departmentDto = new DepartmentDto();
        if(departmentEntity!=null){
            departmentDto.setId(departmentEntity.getId());
            departmentDto.setName(departmentEntity.getName());
        }
        return departmentDto;
    }
}
