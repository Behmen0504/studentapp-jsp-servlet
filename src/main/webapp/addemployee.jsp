<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add employee</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="panel">
    <h1>Add employee</h1>
    <form action="employee" method="post">
        <div>
            <label>Name</label>
            <input required type="text" name="name">
            &nbsp;
            <label>Surname</label>
            <input required type="text" name="surname">
        </div>
        <br/>
        <div style="margin-bottom: 50px;">
            <label>Date of birthday</label>
            <input required type="date" name="dob">
            &nbsp;
            <label>Department name</label>
            <select required name="depid" id="">
                <c:forEach items="${departmentList}" var="dl">
                        <option value="${dl.id}">${dl.name}</option>
                </c:forEach>
            </select>
            <input type="submit" class="btn-submit" name="btnadd" value="Add employee">
        </div>

        <a href="employee" style="text-decoration: none;" class="btn">Employee list</a>
        <a href="index.jsp" style="text-decoration: none;" class="btn">Home</a>
    </form>
</div>
</body>
</html>