package com.example.studentappcustoms.dao.repository;

import com.example.studentappcustoms.Helper.DatabaseConnention;
import com.example.studentappcustoms.dao.entity.DepartmentEntity;
import com.example.studentappcustoms.model.dto.DepartmentDto;

import java.sql.*;
import java.util.ArrayList;

public class DepartmentRepository {
    public ArrayList<DepartmentDto> getAllDepartments(Connection c) throws SQLException {
        PreparedStatement statement = c.prepareStatement("select * from departments");
        ResultSet rs = statement.executeQuery();
        ArrayList<DepartmentDto> departmentDtoArrayList = new ArrayList<>();
        while (rs.next()) {
            DepartmentEntity entity = new DepartmentEntity(rs.getInt(1), rs.getString(2));
            DepartmentDto departmentDto = new DepartmentDto(entity.getId(), entity.getName());
            departmentDtoArrayList.add(departmentDto);
        }
        return departmentDtoArrayList;
    }

    public int addDepartment(DepartmentEntity department) throws Exception {
        Connection c = DatabaseConnention.connectToDatabase();
        PreparedStatement statement = c.prepareStatement("insert into departments values(default,?)");
        statement.setString(1, department.getName());
        return statement.executeUpdate();
    }

    public int updateDepartment(DepartmentEntity department) throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection c = null;
        c = DatabaseConnention.connectToDatabase();
        statement = c.prepareStatement(" update departments set name= ? where id=?", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, department.getName());
        statement.setInt(2, department.getId());
        statement.execute();
        rs = statement.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public DepartmentDto getDepartmentById(Integer id) throws Exception {
        if (id != null) {
            DepartmentDto dto = null;
            DepartmentEntity entity = null;
            Connection c = null;
            c = DatabaseConnention.connectToDatabase();
            PreparedStatement statement = c.prepareStatement("select * from departments where id = " + id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                entity = new DepartmentEntity(rs.getInt(1), rs.getString(2));
                dto = new DepartmentDto(entity.getId(), entity.getName());
            }
            return dto;
        }
        return null;
    }

    public void deleteDepartment(int id) throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection c = null;
        c = DatabaseConnention.connectToDatabase();
        statement = c.prepareStatement(" delete from departments where id=?");
        statement.setInt(1, id);
        statement.execute();
    }
}
