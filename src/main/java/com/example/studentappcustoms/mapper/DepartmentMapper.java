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
}
