package com.example.studentappcustoms.controller;


import com.example.studentappcustoms.DatabaseConnention;
import com.example.studentappcustoms.Util;
import com.example.studentappcustoms.dao.entity.DepartmentEntity;
import com.example.studentappcustoms.dao.repository.DepartmentDao;
import com.example.studentappcustoms.dao.repository.EmployeeDao;
import com.example.studentappcustoms.model.dto.DepartmentDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "DepartmentServlet", urlPatterns = "/department")
public class DepartmentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        EmployeeDao employeeDao = new EmployeeDao();
        DepartmentDao departmentDao = new DepartmentDao();
        DepartmentDto d = null;

        Connection c = null;
        if(request.getParameter("edit")!=null){
            int id = Integer.parseInt(request.getParameter("edit"));
            try {
                c = DatabaseConnention.connectToDatabase();
                d = departmentDao.getDepartmentById(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            request.setAttribute("dep", d);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("editdepartment.jsp");
            requestDispatcher.forward(request, response);
        }
        if(request.getParameter("delete")!=null){
            int id = Integer.parseInt(request.getParameter("delete"));
            try {
                c = DatabaseConnention.connectToDatabase();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                departmentDao.deleteDepartment(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("department");
            return;
        }
        try {
            c = DatabaseConnention.connectToDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<DepartmentDto> department_list = null;
        try {
            department_list = departmentDao.getAllDepartments(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Util.sendDataAndForward(request,response,"departmentList","department_list.jsp",department_list);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeDao employeeDao = new EmployeeDao();
        DepartmentDao departmentDao = new DepartmentDao();
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        if (request.getParameter("btnedit") != null) {
            DepartmentEntity d = new DepartmentEntity(Integer.parseInt(id),name);
            if (request.getParameter("btnedit").equals("Edit department")) {
                try {
                    departmentDao.updateDepartment(d);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                response.sendRedirect("department");
            }
            return;
        }
        DepartmentEntity d = new DepartmentEntity(name);
        try {
            departmentDao.addDepartment(d);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("adddepartment.html");
    }

}
