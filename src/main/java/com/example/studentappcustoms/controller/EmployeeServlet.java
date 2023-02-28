package com.example.studentappcustoms.controller;

import com.example.studentappcustoms.Helper.DatabaseConnention;
import com.example.studentappcustoms.Helper.Util;
import com.example.studentappcustoms.dao.entity.DepartmentEntity;
import com.example.studentappcustoms.dao.entity.EmployeeEntity;
import com.example.studentappcustoms.dao.repository.DepartmentRepository;
import com.example.studentappcustoms.dao.repository.EmployeeRepository;
import com.example.studentappcustoms.model.dto.DepartmentDto;
import com.example.studentappcustoms.model.dto.EmployeeDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/employees")
public class EmployeeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        EmployeeRepository employeeRepository = new EmployeeRepository();
        DepartmentRepository departmentRepository = new DepartmentRepository();
        List<DepartmentDto> departmentList = null;
        List<EmployeeDto> employeeList = null;
        EmployeeDto employeeDto = new EmployeeDto();
        Connection c = null;

        if (request.getParameter("buttonadd") != null) {
            if (request.getParameter("buttonadd").equals("Add employee")) {
                try {
                    c = DatabaseConnention.connectToDatabase();
                    departmentList = departmentRepository.getAllDepartments(c);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                Util.sendDataAndForward(request, response, "departmentList", "addemployee.jsp", departmentList);
            }
            return;
        }

        if (request.getParameter("edit") != null) {
            int id = Integer.parseInt(request.getParameter("edit"));
            try {
                c = DatabaseConnention.connectToDatabase();
                departmentList = departmentRepository.getAllDepartments(c);
                employeeDto = employeeRepository.getElmployeeById(id);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            Util.sendDataAndForward(request, response, "myData", "editemployee.jsp", employeeDto, departmentList);
        }
        if (request.getParameter("delete") != null) {
            int id = Integer.parseInt(request.getParameter("delete"));
            try {
                employeeRepository.deleteEmployee(id);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            response.sendRedirect("employees");
            return;
        }
        try {
            c = DatabaseConnention.connectToDatabase();
            employeeList = employeeRepository.getAllEmployees(c);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Util.sendDataAndForward(request, response, "employeeList", "employee-list.jsp", employeeList);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        EmployeeRepository employeeRepository = new EmployeeRepository();
        DepartmentRepository departmentRepository = new DepartmentRepository();
        DepartmentDto departmentDto = null;

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String dt = request.getParameter("dob");
        String dep_id = request.getParameter("depid");
        try{
            departmentDto = departmentRepository.getDepartmentById(Integer.parseInt(dep_id));
            if (request.getParameter("btnedit") != null) {
                EmployeeEntity employeeEntity = new EmployeeEntity(
                        Integer.parseInt(id), name, surname, dt,
                        new DepartmentEntity(departmentDto.getId(),departmentDto.getName()));
                if (request.getParameter("btnedit").equals("Edit employee")) {
                    try {
                        employeeRepository.updateEmployee(employeeEntity);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    response.sendRedirect("employees");
                }
                return;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        EmployeeEntity employeeEntity = new EmployeeEntity(name, surname, dt, new DepartmentEntity(departmentDto.getId(),departmentDto.getName()));
        try {
            employeeRepository.addEmployee(employeeEntity);
            response.sendRedirect("employees?buttonadd=Add+employee");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
