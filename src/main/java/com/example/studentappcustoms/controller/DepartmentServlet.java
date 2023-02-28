package com.example.studentappcustoms.controller;

import com.example.studentappcustoms.Helper.DatabaseConnention;
import com.example.studentappcustoms.Helper.Util;
import com.example.studentappcustoms.dao.entity.DepartmentEntity;
import com.example.studentappcustoms.dao.repository.DepartmentRepository;
import com.example.studentappcustoms.model.dto.DepartmentDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "DepartmentServlet", urlPatterns = "/departments")
public class DepartmentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        DepartmentRepository departmentRepository = new DepartmentRepository();
        DepartmentDto departmentDto = null;
        Connection c = null;
        if(request.getParameter("edit")!=null){
            int id = Integer.parseInt(request.getParameter("edit"));
            try {
                departmentDto = departmentRepository.getDepartmentById(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Util.sendDataAndForward(request,response,"dep","editdepartment.jsp",departmentDto);
        }
        if(request.getParameter("delete")!=null){
            int id = Integer.parseInt(request.getParameter("delete"));
            try {
                departmentRepository.deleteDepartment(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("departments");
            return;
        }
        try {
            c = DatabaseConnention.connectToDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<DepartmentDto> departmentList = null;
        try {
            departmentList = departmentRepository.getAllDepartments(c);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Util.sendDataAndForward(request,response,"departmentList","department_list.jsp",departmentList);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DepartmentRepository departmentRepository = new DepartmentRepository();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        if (request.getParameter("btnedit") != null) {
            DepartmentEntity departmentEntity = new DepartmentEntity(Integer.parseInt(id),name);
            if (request.getParameter("btnedit").equals("Edit department")) {
                try {
                    departmentRepository.updateDepartment(departmentEntity);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                response.sendRedirect("departments");
            }
            return;
        }
        DepartmentEntity departmentEntity = new DepartmentEntity(name);
        try {
            departmentRepository.addDepartment(departmentEntity);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("adddepartment.html");
    }

}
