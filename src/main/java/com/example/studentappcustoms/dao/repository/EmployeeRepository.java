package com.example.studentappcustoms.dao.repository;

import com.example.studentappcustoms.Helper.DatabaseConnention;
import com.example.studentappcustoms.dao.entity.DepartmentEntity;
import com.example.studentappcustoms.dao.entity.EmployeeEntity;
import com.example.studentappcustoms.mapper.EmployeeMapper;
import com.example.studentappcustoms.model.dto.DepartmentDto;
import com.example.studentappcustoms.model.dto.EmployeeDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    public List<EmployeeDto> getAllEmployees(Connection c) throws Exception {
        PreparedStatement statement = c.prepareStatement("select e.*,d.id department_id,d.name department_name from employees e left join departments d on e.dep_id=d.id");
        ResultSet rs = statement.executeQuery();
        ArrayList<EmployeeEntity> employeeEntityList = new ArrayList<>();
        while (rs.next()) {
            DepartmentEntity departmentEntity = new DepartmentEntity(rs.getInt("department_id"), rs.getString("department_name"));
            EmployeeEntity employeeEntity = new EmployeeEntity(
                    rs.getInt("id"),//column names
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("dob"),
                    departmentEntity);
            employeeEntityList.add(employeeEntity);
        }
        return EmployeeMapper.mapEntitiesToDtos(employeeEntityList);
    }

    public EmployeeDto getElmployeeById(Integer id) throws Exception {
        EmployeeEntity employeeEntity = null;
        EmployeeDto employeeDto = null;
        Connection c = null;
        DepartmentRepository departmentRepository = new DepartmentRepository();
        c = DatabaseConnention.connectToDatabase();
        PreparedStatement statement = c.prepareStatement("select * from employees where id = " + id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            DepartmentDto departmentDto = departmentRepository.getDepartmentById(
                    rs.getObject("dep_id") != null && !rs.wasNull() ? (Integer) rs.getObject("dep_id") : null);
            employeeEntity = new EmployeeEntity(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("dob"),
                    departmentDto ==null ? new DepartmentEntity() : new DepartmentEntity(departmentDto.getId(), departmentDto.getName()));
            employeeDto = EmployeeMapper.mapEntityToDto(employeeEntity);
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
        PreparedStatement statement = null;
        Connection c = null;
        c = DatabaseConnention.connectToDatabase();
        statement = c.prepareStatement(" delete from employees where id=?");
        statement.setInt(1, id);
        statement.execute();
    }
}
