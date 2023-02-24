package com.example.studentappcustoms.dao.repository;

import com.example.studentappcustoms.Helper.DatabaseConnention;
import com.example.studentappcustoms.dao.entity.DepartmentEntity;
import com.example.studentappcustoms.dao.entity.EmployeeEntity;
import com.example.studentappcustoms.model.dto.DepartmentDto;
import com.example.studentappcustoms.model.dto.EmployeeDto;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDaoRepository {
    public ArrayList<EmployeeDto> getAllEmployees(Connection c) throws Exception {
        PreparedStatement statement = c.prepareStatement("select * from employees");
        ResultSet rs = statement.executeQuery();

        ArrayList<EmployeeDto> arr = new ArrayList<>();

        DepartmentRepository departmentRepository = new DepartmentRepository();

        while (rs.next()) {
            DepartmentDto departmentDto = departmentRepository.getDepartmentById(
                    rs.getObject(5) != null && !rs.wasNull() ? (Integer) rs.getObject(5) : null);

            DepartmentEntity departmentEntity = new DepartmentEntity(departmentDto.getId(),departmentDto.getName());
            EmployeeEntity entity = new EmployeeEntity(
                    rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4),
                    departmentEntity);

            EmployeeDto employeeDto = new EmployeeDto(
                    entity.getId(),
                    entity.getName(),
                    entity.getSurname(),
                    entity.getDob(),
                    departmentDto);
            arr.add(employeeDto);
        }

        return arr;
    }

    public EmployeeDto getElmployeeById(Integer id) throws Exception {
        EmployeeEntity entity = null;
        EmployeeDto employeeDto = null;
        Connection c = null;
        DepartmentRepository departmentRepository = new DepartmentRepository();

        c = DatabaseConnention.connectToDatabase();
        PreparedStatement statement = c.prepareStatement("select * from employees where id = " + id);
        ResultSet rs = statement.executeQuery();


        while (rs.next()) {
            DepartmentDto departmentDto = departmentRepository.getDepartmentById(
                    rs.getObject(5) != null && !rs.wasNull() ? (Integer) rs.getObject(5) : null);
            entity = new EmployeeEntity(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    new DepartmentEntity(departmentDto.getId(),departmentDto.getName()));
            employeeDto = new EmployeeDto(
                    entity.getId(),
                    entity.getName(),
                    entity.getSurname(),
                    entity.getDob(),
                    departmentDto);
        }

        return employeeDto;
    }

    public int addEmployee(EmployeeEntity entity) throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection c = null;
        c = DatabaseConnention.connectToDatabase();
        statement = c.prepareStatement("insert  into employees values(default,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setString(3, entity.getDob());
        statement.setInt(4, entity.getEntity().getId());
        statement.executeUpdate();
        rs = statement.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public int updateEmployee(EmployeeEntity entity) throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection c = null;
        c = DatabaseConnention.connectToDatabase();
        statement = c.prepareStatement(" update employees set name=?,surname=?,dob=?,dep_id=? where id=?", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setString(3, entity.getDob());
        statement.setInt(4, entity.getEntity().getId());
        statement.setInt(5, entity.getId());
        statement.execute();
        rs = statement.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public void deleteEmployee(int id) throws Exception {
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection c = null;

        c = DatabaseConnention.connectToDatabase();
        statement = c.prepareStatement(" delete from employees where id=?");
        statement.setInt(1, id);
        statement.execute();

    }
}
