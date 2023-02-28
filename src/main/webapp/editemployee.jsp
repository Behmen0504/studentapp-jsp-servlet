<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.studentappcustoms.model.dto.DepartmentDto" %>
<%@ page import="com.example.studentappcustoms.model.dto.EmployeeDto" %>
<%@ page import="java.util.Objects" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit employee</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="panel">
    <h1>Edit employee</h1>
    <form action="employees" method="post">
        <div>
            <input type="hidden" name="id" value="${myData[0].id}">
            <label>Name</label>
            <input required type="text" name="name" value="${myData[0].name}">
            &nbsp;
            <label>Surname</label>
            <input required type="text" name="surname" value="${myData[0].surname}">
        </div>
        <br/>
        <div>
            <label>Date of birthday</label>
            <input required type="date" name="dob" value="${myData[0].dob}">
            &nbsp;
            <%
                Object[] obj = (Object[]) request.getAttribute("myData");
                ArrayList<DepartmentDto> departmentL = (ArrayList<DepartmentDto>) obj[1];
                EmployeeDto e = (EmployeeDto) obj[0];
            %>
            <label>Department name</label>
            <select required name="depid" id="">
                <%for(DepartmentDto d: departmentL) {%>
                <option <% if (Objects.equals(e.getDepartment() == null ? null:e.getDepartment().getId(),d.getId())) { %> selected <%} %> value="<%=d.getId()%>"><%=d.getName()%></option>
                <%}%>
            </select>
        </div>
        <input type="submit" class="btn" name="btnedit" value="Edit employee">
    </form>
</div>
</body>
</html>
