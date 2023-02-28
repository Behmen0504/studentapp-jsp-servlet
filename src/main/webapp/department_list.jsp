<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Department list</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="panel">
    <h1>Department table</h1>
    <table border="1">
        <thead>
        <tr>
            <th style="display: none;">ID</th>
            <th>Department name</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${departmentList[0]}" var="dl">
            <tr>
                <td style="display: none;">${dl.id}</td>
                <td> ${dl.name} </td>
                <td>
                    <a class="btn-crud" href="departments?edit=${dl.id}">Edit</a>
                </td>
                <td>
                    <a class="btn-crud" href="departments?delete=${dl.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br/>
    <a href="adddepartment.html"><input type="submit" class="btn" name="buttonadd" value="Add department"></a>
    <a href="index.jsp" style="text-decoration: none;" class="btn">Home</a>
</div>
</body>
</html>
