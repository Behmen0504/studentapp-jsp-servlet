<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.studentappcustoms.model.dto.EmployeeDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee list</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="panel">
    <h1>Employee table</h1>
    <table border="1">
        <thead>
        <tr>
            <th style="display: none;">ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Date of birthday</th>
            <th>Department name</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <%ArrayList<EmployeeDto> list = (ArrayList<EmployeeDto>) request.getAttribute("employeeList");
            //System.out.println(list);
        %>
        <c:forEach items="${employeeList}" var="el">
        <tr>
            <td style="display: none;"> ${el.id} </td>
            <td> ${el.name} </td>
            <td> ${el.surname} </td>
            <td> ${el.dob} </td>
            <td> ${el.department != null ? el.department.name : null} </td>
            <td>
                <a class="btn-crud" href="employee?edit=${el.id}">Edit</a>
            </td>
            <td>
                <a class="btn-crud" href="employee?delete=${el.id}">delete</a>
            </td>
            </c:forEach>
    </table>
    <br/>
    <form action="employee" method="get">
        <input type="submit" class="btn" name="buttonadd" value="Add employee">
        <a href="index.jsp" style="text-decoration: none;" class="btn">Home</a>
    </form>
</div>
</body>
</html>
