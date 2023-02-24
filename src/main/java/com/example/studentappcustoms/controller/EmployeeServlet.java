package com.example.studentappcustoms.controller;

import com.example.studentappcustoms.DatabaseConnention;
import com.example.studentappcustoms.Util;
import com.example.studentappcustoms.dao.entity.EmployeeEntity;
import com.example.studentappcustoms.dao.repository.DepartmentDao;
import com.example.studentappcustoms.dao.repository.EmployeeDao;
import com.example.studentappcustoms.model.dto.DepartmentDto;
import com.example.studentappcustoms.model.dto.EmployeeDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "EmployeeServlet", urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        EmployeeDao employeeDao = new EmployeeDao();
        DepartmentDao departmentDao = new DepartmentDao();
        ArrayList<DepartmentDto> department_list = null;
        ArrayList<EmployeeDto> employee_list = null;
        EmployeeDto e = null;
        Connection c = null;

        if (request.getParameter("buttonadd") != null) {
            if (request.getParameter("buttonadd").equals("Add employee")) {
                try {
                    c = DatabaseConnention.connectToDatabase();
                    department_list = departmentDao.getAllDepartments(c);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                Util.sendDataAndForward(request, response, "departmentList", "addemployee.jsp", department_list);
            }
            return;
        }

        if (request.getParameter("edit") != null) {
            int id = Integer.parseInt(request.getParameter("edit"));
            try {
                c = DatabaseConnention.connectToDatabase();
                department_list = departmentDao.getAllDepartments(c);
                e = employeeDao.getElmployeeById(id);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            Util.sendDataAndForward2(request, response, "myData", "editemployee.jsp", e, department_list);
        }
        if (request.getParameter("delete") != null) {
            int id = Integer.parseInt(request.getParameter("delete"));
            try {
                c = DatabaseConnention.connectToDatabase();
                employeeDao.deleteEmployee(id);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            response.sendRedirect("employee");
            return;
        }


        try {
            c = DatabaseConnention.connectToDatabase();
            employee_list = employeeDao.getAllEmployees(c);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Util.sendDataAndForward(request, response, "employeeList", "employee-list.jsp", employee_list);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        EmployeeDao employeeDao = new EmployeeDao();
        DepartmentDao departmentDao = new DepartmentDao();
        ArrayList<DepartmentDto> department_list = null;
        ArrayList<EmployeeDto> employee_list = null;
        DepartmentDto department = null;
        EmployeeDto e = null;
        Connection c = null;

        departmentDao = new DepartmentDao();
        employeeDao = new EmployeeDao();
        c = null;
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String dt = request.getParameter("dob");
        String dep_id = request.getParameter("depid");
        try{
            department = departmentDao.getDepartmentById(Integer.parseInt(dep_id));
            if (request.getParameter("btnedit") != null) {
                EmployeeEntity entity = new EmployeeEntity(Integer.parseInt(id), name, surname, dt, department);
                if (request.getParameter("btnedit").equals("Edit employee")) {
                    try {
                        employeeDao.updateEmployee(entity);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    response.sendRedirect("employee");
                }
                return;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }


        EmployeeEntity entity = new EmployeeEntity(name, surname, dt, department);
        try {
            employeeDao.addEmployee(entity);
            response.sendRedirect("employee?buttonadd=Add+employee");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}